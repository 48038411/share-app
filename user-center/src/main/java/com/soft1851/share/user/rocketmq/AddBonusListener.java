package com.soft1851.share.user.rocketmq;

import com.soft1851.share.user.dao.BonusEventLogMapper;
import com.soft1851.share.user.dao.UserMapper;
import com.soft1851.share.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.share.user.domain.entity.BonusEventLog;
import com.soft1851.share.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述:
 * 发送给消息队列的
 *
 * @author：Guorc
 * @create 2020-10-07 14:12
 */
@Service
@RocketMQMessageListener(consumerGroup = "consumer",topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;
    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        //1,为用户加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus()+userAddBonusMsgDTO.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);
        //2,写积分日志
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
        .userId(userId)
        .value(userAddBonusMsgDTO.getBonus())
        .event("CONTRIBUTE")
        .createTime(new Date())
        .description("投稿加积分")
        .build()
        );
    }
}
