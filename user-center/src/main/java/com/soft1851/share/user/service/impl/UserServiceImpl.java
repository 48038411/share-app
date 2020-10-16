package com.soft1851.share.user.service.impl;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.soft1851.share.user.common.ResponseResult;
import com.soft1851.share.user.dao.BonusEventLogMapper;
import com.soft1851.share.user.dao.UserMapper;
import com.soft1851.share.user.domain.dto.*;
import com.soft1851.share.user.domain.entity.BonusEventLog;
import com.soft1851.share.user.domain.entity.User;
import com.soft1851.share.user.service.UserService;
import com.soft1851.share.user.util.DateUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-25 14:41
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public ResponseResult getUserInfo(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return new ResponseResult(200, "查询成功", user);
    }

    @Override
    public ResponseDTO findById(Integer id) {
//        return this.userMapper.selectByPrimaryKey(id);
        User user = this.userMapper.selectByPrimaryKey(id);
        return new ResponseDTO(true,"200","查询成功",user,1l);

    }

    @Override
    public User updateBonus(UserAddBonusMsgDTO addBonusMsgDTO) {
        User user = this.userMapper.selectByPrimaryKey(addBonusMsgDTO.getUserId());
        user.setBonus(user.getBonus() + addBonusMsgDTO.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);
        //写积分日志
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(addBonusMsgDTO.getUserId())
                .value(addBonusMsgDTO.getBonus())
                .event("CONTRIBUTE")
                .createTime(new Date())
                .description("投稿加积分")
                .build()
        );
        return user;
    }

    @Override
    public User login(LoginDTO loginDTO,String openId) {
        //先根据wxId查找用户
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("wxId",loginDTO.getWxId());
        List<User> users = this.userMapper.selectByExample(example);
        //没找到，是新用户，直接注册
        if (users.size() == 0){
            User saveUser = User.builder()
                    .wxId(loginDTO.getWxId())
                    .avatarUrl(loginDTO.getAvatar())
                    .wxNickname(loginDTO.getWxNickName())
                    .roles("user")
                    .bonus(100)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.userMapper.insertSelective(saveUser);
            return saveUser;
        }
        return users.get(0);

    }

    @Override
    public User reduceBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        User user = this.userMapper.selectByPrimaryKey(userAddBonusMsgDTO.getUserId());
        user.setBonus(user.getBonus() + userAddBonusMsgDTO.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);
        //写积分日志
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userAddBonusMsgDTO.getUserId())
                .value(userAddBonusMsgDTO.getBonus())
                .event("CONTRIBUTE")
                .createTime(new Date())
                .description("兑换扣积分")
                .build()
        );
        return user;
    }

    @Override
    public ResponseDTO signIn(UserSignInDTO signInDTO) {
        User user = this.userMapper.selectByPrimaryKey(signInDTO.getUserId());
        if (user == null){
            throw new IllegalArgumentException("该用户不存在!");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId",signInDTO.getUserId());
        criteria.andEqualTo("event","SIGN_IN");
        List<BonusEventLog> bonusEventLog = this.bonusEventLogMapper.selectByExample(example);
        //判断日志表有没有记录，如果没有直接插入数据并提示签到成功
        if (bonusEventLog.size() == 0){
            this.bonusEventLogMapper.insert(BonusEventLog.builder()
                    .userId(signInDTO.getUserId())
                    .event("SIGN_IN")
                    .value(20)
                    .description("签到加积分")
                    .createTime(new Date())
                    .build());
            user.setBonus(user.getBonus()+20);
            this.userMapper.updateByPrimaryKeySelective(user);
            return new ResponseDTO(true,"200","签到成功",user,1l);
        }else {
            BonusEventLog bonusEventLog1 = bonusEventLog.get(0);
            Date date = bonusEventLog1.getCreateTime();
            try {
                if (DateUtil.checkAllotSigin(date) == 0){
                    this.bonusEventLogMapper.insert(BonusEventLog.builder()
                            .userId(signInDTO.getUserId())
                            .event("SIGN_IN")
                            .value(20)
                            .description("签到加积分")
                            .createTime(new Date())
                            .build());
                    user.setBonus(user.getBonus()+20);
                    this.userMapper.updateByPrimaryKeySelective(user);
                    return new ResponseDTO(true,"200","签到成功",user,1l);
                }
                else if (DateUtil.checkAllotSigin(date) == 1){
                    return new ResponseDTO(false,"201","签到失败",user.getWxNickname()+"今天已经签到过了",1l);
                }
                else if (DateUtil.checkAllotSigin(date) == 2){
                    return new ResponseDTO(false,"202","签到失败",user.getWxNickname()+"用户，今天数据错乱了",1l);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseDTO(true,"200","签到成功",user.getWxNickname()+"签到成功",1l);
        }
    }
    @Override
    public  ResponseDTO checkIsSign(UserSignInDTO signInDTO){
        User user = this.userMapper.selectByPrimaryKey(signInDTO.getUserId());
        if (user == null){
            throw new IllegalArgumentException("该用户不存在!");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId",signInDTO.getUserId());
        criteria.andEqualTo("event","SIGN_IN");
        List<BonusEventLog> bonusEventLog = this.bonusEventLogMapper.selectByExample(example);
        if (bonusEventLog.size() == 0){
            return new ResponseDTO(true,"200","该用户还没有签到","可以签到",1l);
        }else {
            BonusEventLog bonusEventLog1 = bonusEventLog.get(0);
            Date date = bonusEventLog1.getCreateTime();
            try {
                if (DateUtil.checkAllotSigin(date) == 0){
                    return new ResponseDTO(true,"200","该用户还没有签到","可以签到",1l);
                }
                else if (DateUtil.checkAllotSigin(date) == 1){
                    return new ResponseDTO(false,"201","已经签到了","不可以签到",1l);
                }
                else if (DateUtil.checkAllotSigin(date) == 2){
                    return new ResponseDTO(false,"202","数据出错了","不可以签到",1l);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseDTO(true,"200","该用户还没有签到","可以签到",1l);
        }
    }

    @Override
    public ResponseDTO getLog(UserDTO userDTO) {
        Example example = new Example(BonusEventLog.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userDTO.getId());
        List<BonusEventLog> bonusEventLogList = this.bonusEventLogMapper.selectByExample(example);
        return new ResponseDTO(true,"200","查询成功",bonusEventLogList,1l);
    }
}
