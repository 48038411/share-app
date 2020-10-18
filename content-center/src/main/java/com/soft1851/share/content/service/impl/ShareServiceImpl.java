package com.soft1851.share.content.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.share.content.dao.MidUserShareMapper;
import com.soft1851.share.content.dao.ShareMapper;
import com.soft1851.share.content.domain.dto.*;
import com.soft1851.share.content.domain.entity.BonusEventLog;
import com.soft1851.share.content.domain.entity.MidUserShare;
import com.soft1851.share.content.domain.entity.Share;
import com.soft1851.share.content.domain.enums.AuditStatusEnum;
import com.soft1851.share.content.feignclient.UserCenterFeignClient;
import com.soft1851.share.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-30 7:51
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;
    private final MidUserShareMapper midUserShareMapper;
    private final RocketMQTemplate rocketMQTemplate;
    private final AsyncRestTemplate asyncRestTemplate;

    @Override
    public ShareDTO findById(Integer id) {
        // 获取分享实体
        Share share = this.shareMapper.selectByPrimaryKey(id);
        // 获得发布人id
        Integer userId = share.getUserId();

        // 1. 代码不可读
        // 2. 复杂的url难以维护：https://user-center/s?ie={ie}&f={f}&rsv_bp=1&rsv_idx=1&tn=baidu&wd=a&rsv_pq=c86459bd002cfbaa&rsv_t=edb19hb%2BvO%2BTySu8dtmbl%2F9dCK%2FIgdyUX%2BxuFYuE0G08aHH5FkeP3n3BXxw&rqlang=cn&rsv_enter=1&rsv_sug3=1&rsv_sug2=0&inputT=611&rsv_sug4=611
        // 3. 难以相应需求的变化，变化很没有幸福感
        // 4. 编程体验不统一
        ResponseDTO responseDTO = this.userCenterFeignClient.findUserById(userId);
        UserDTO userDTO = convert(responseDTO);

        ShareDTO shareDTO = new ShareDTO();
//        shareDTO.setShare(share);
        // 属性的装配
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;
    }

    @Override
    public int insert(ShareRequestDTO shareRequestDTO) {
        Share share = Share.builder().
                userId(shareRequestDTO.getUserId())
                .title(shareRequestDTO.getTitle())
                .isOriginal(shareRequestDTO.getIsOriginal())
                .author(shareRequestDTO.getAuthor())
                .summary(shareRequestDTO.getSummary())
                .price(shareRequestDTO.getPrice())
                .downloadUrl(shareRequestDTO.getDownloadUrl())
                .auditStatus("NOT_YET")
                .buyCount(0)
                .cover("https://profile.csdnimg.cn/2/6/8/2_weixin_43250266")
                .createTime(new Date())
                .updateTime(new Date())
                .showFlag(false)
                .build();
        return shareMapper.insert(share);
    }


    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {
        //启动分页
        PageHelper.startPage(pageNo, pageSize);
        //构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        //如标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if (StringUtil.isNotEmpty(title)) {
            criteria.andLike("title", "%" + title + "%");
        }
        //显示的才显示,审核通过的才显示
        criteria.andEqualTo("showFlag",1);
        criteria.andEqualTo("auditStatus","PASS");
        //执行按条件查询
        List<Share> shares = this.shareMapper.selectByExample(example);
        //处理后的Share数据列表
        List<Share> shareDeal;
        // 1.如果用户未登录，那么downloadUrl全部设为null
        if (userId == null) {
            shareDeal = shares.stream()
                    .peek(share -> {
                        share.setDownloadUrl(null);
                    })
                    .collect(Collectors.toList());
        }
        // 2.如果用户登录了，那么查询一下mid_user_share，如果没有数据，那么这条share的downloadUrl也设为null
        //只有自己分享的资源才能看到下载链接，否则显示兑换
        else {
            shareDeal = shares.stream()
                    .peek(share -> {
                        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                                MidUserShare.builder()
                                        .userId(userId)
                                        .shareId(share.getId())
                                        .build()
                        );
                        if (midUserShare == null) {
                            share.setDownloadUrl(null);
                        }
                    })
                    .collect(Collectors.toList());
        }
        return new PageInfo<>(shareDeal);
    }

    @Async
    public void update(Share share) {
        UserAddBonusMsgDTO userAddBonusMsgDTO = UserAddBonusMsgDTO.builder()
                .userId(share.getUserId())
                .bonus(50)
                .build();
        this.userCenterFeignClient.updateBonus(userAddBonusMsgDTO);
    }

    @Override
    public Share auditById(Integer id, ShareAuditDTO shareAuditDTO) {
        //1,查询share是否存在，不存在或者当前的audit_status！=NOT_YET，那么抛出异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过");
        }
        //2,审核流程，将状态改为PASS或REJECT
        //这个app主要流程是审核，所以不需要等更新积分的结果返回，可以将增加积分改为异步
        share.setAuditStatus(shareAuditDTO.getAuditStatusEnum().toString());
        this.shareMapper.updateByPrimaryKey(share);
        //3,如果是PASS,那么发送消息给rocketmq，让用户中心去消费，并为发布人添加积分
        if (AuditStatusEnum.PASS.equals(shareAuditDTO.getAuditStatusEnum())) {
            share.setShowFlag(true);
            this.shareMapper.updateByPrimaryKey(share);
            //this.midUserShareMapper.insertSelective(MidUserShare.builder().shareId(share.getId()).userId(share.getUserId()).build());
            long nowTime = System.currentTimeMillis();
            //1，rocketmq异步实现加积分
            this.rocketMQTemplate.convertAndSend(
                    "add-bonus",
                    UserAddBonusMsgDTO.builder()
                            .userId(share.getUserId())
                            .bonus(50)
                            .build()
            );
            long nowTime1 = System.currentTimeMillis();
            long finalTime = nowTime1 - nowTime;
            log.info("使用异步rocketmq操作修改用户积分花费了时间,{}", finalTime);
//            //2，同步调用Feignclient修改用户积分
//            long nowTime = System.currentTimeMillis();
//            this.userCenterFeignClient.updateBonus(UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build());
//            long nowTime1 = System.currentTimeMillis();
//            long finalTime = nowTime1-nowTime;
//            log.info("使用openFeign同步操作修改用户日志花费了时间,{}",finalTime);
//            //3,使用asyncTemplate实现异步
//            String url = "http://user-center/users/bonus";
//            UserAddBonusMsgDTO userAddBonusMsgDTO = UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build();
//            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//            headers.add("Content-Type", "application/json;charset=UTF-8");
//            HttpEntity<Object> httpEntity = new HttpEntity<>(userAddBonusMsgDTO,headers);
//            long nowTime = System.currentTimeMillis();
//            ListenableFuture<ResponseEntity<User>> entity = asyncRestTemplate.postForEntity(url,httpEntity,User.class);
//            entity.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
//                @Override
//                public void onFailure(Throwable ex) {
//                    log.error("调用失败"+ex);
//                }
//
//                @Override
//                public void onSuccess(ResponseEntity<User> result) {
//                    log.info("调用成功");
//                }
//            });
//            long nowTime1 = System.currentTimeMillis();
//            log.info(String.valueOf(entity));
//            long finalTime = nowTime1-nowTime;
//            log.info("使用asyncTemplate异步操作修改用户积分花费了时间,{}",finalTime);
//            //4,Async请求
//            long nowTime = System.currentTimeMillis();
//            this.update(share);
//            long nowTime1 = System.currentTimeMillis();
//            long finalTime = nowTime1-nowTime;
//            log.info("使用@Async注解实现异步请求所用的时间为{}",finalTime);

        }
        return share;
    }

    @Override
    public Share exchange(ExchangeDTO exchangeDTO) {
        int userId = exchangeDTO.getUserId();
        int shareId = exchangeDTO.getShareId();
        // 1. 根据id查询share，校验是否存在
        Share share = this.shareMapper.selectByPrimaryKey(shareId);
        if (share == null) {
            throw new IllegalArgumentException("该分享不存在！");
        }
        Integer price = share.getPrice();

        // 2. 如果当前用户已经兑换过该分享，则直接返回
        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                MidUserShare.builder()
                        .shareId(shareId)
                        .userId(userId)
                        .build()
        );
        if (midUserShare != null) {
            return share;
        }

         //3. 根据当前登录的用户id，查询积分是否够
        ResponseDTO responseDTO = this.userCenterFeignClient.findUserById(userId);
        UserDTO userDTO = convert(responseDTO);
        System.out.println(userDTO);
        if (price > userDTO.getBonus()) {
            throw new IllegalArgumentException("用户积分不够！");
        }



         //4. 扣积分
        this.userCenterFeignClient.reduceBonus(
                UserAddBonusMsgDTO.builder()
                        .userId(userId)
                        .bonus(price * -1)
                        .build()
        );
        //5. 向mid_user_share表里插入一条数据
        this.midUserShareMapper.insert(
                MidUserShare.builder()
                        .userId(userId)
                        .shareId(shareId)
                        .build()
        );
        return share;
        }

    @Override
    public List<Share> queryMy(UserDTO userDTO) {
        Example example = new Example(MidUserShare.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userDTO.getId());
        List<MidUserShare> midUserShares =  midUserShareMapper.selectByExample(example);
        List<Share> shareList = new ArrayList<>();
        midUserShares.stream().forEach(
                midUserShare -> {
                    Share share = shareMapper.selectByPrimaryKey(Share.builder().id(midUserShare.getShareId()).build());
                    shareList.add(share);
                }
        );
        return shareList;
    }

    @Override
    public List<Share> myContribute(UserDTO userDTO) {
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userDTO.getId());
        List<Share> shareList = this.shareMapper.selectByExample(example);
        return shareList;
    }

    @Override
    public List<Share> getUnAudit() {
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("auditStatus","NOT_YET");
        List<Share> shareList = this.shareMapper.selectByExample(example);
        if (shareList.size() == 0){
            throw new IllegalArgumentException("没有待审核数据");
        }else {
            return shareList;
        }
    }

    /**
     * 将统一的返回响应结果转换为UserDTO类型
     * @param responseDTO
     * @return
     */
    private UserDTO convert(ResponseDTO responseDTO) {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = null;
        try {
            String json = mapper.writeValueAsString(responseDTO.getData());
            userDTO = mapper.readValue(json, UserDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userDTO;
    }

}
