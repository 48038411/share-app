package com.soft1851.share.user.service.impl;

import com.soft1851.share.user.common.ResponseResult;
import com.soft1851.share.user.dao.BonusEventLogMapper;
import com.soft1851.share.user.dao.UserMapper;
import com.soft1851.share.user.domain.dto.LoginDTO;
import com.soft1851.share.user.domain.dto.ResponseDTO;
import com.soft1851.share.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.share.user.domain.entity.BonusEventLog;
import com.soft1851.share.user.domain.entity.User;
import com.soft1851.share.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-25 14:41
 */
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
        System.out.println(userAddBonusMsgDTO.getBonus());
        user.setBonus(user.getBonus() + userAddBonusMsgDTO.getBonus());
        System.out.println(user.getBonus());
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
}
