package com.soft1851.share.user.service.impl;

import com.soft1851.share.user.common.ResponseResult;
import com.soft1851.share.user.dao.BonusEventLogMapper;
import com.soft1851.share.user.dao.UserMapper;
import com.soft1851.share.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.share.user.domain.dto.UserDTO;
import com.soft1851.share.user.domain.entity.BonusEventLog;
import com.soft1851.share.user.domain.entity.User;
import com.soft1851.share.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public User findById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);

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
}
