package com.soft1851.share.content.feignclient;

import com.soft1851.share.content.configuration.UserCenterFeignConfiguration;
import com.soft1851.share.content.domain.dto.ResponseDTO;
import com.soft1851.share.content.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.share.content.domain.dto.UserDTO;
import com.soft1851.share.content.domain.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-29 22:41
 */
@FeignClient(name = "user-center",configuration = UserCenterFeignConfiguration.class)
public interface UserCenterFeignClient {
    /**
     * http://user-center/users/{id}
     *
     * @param id
     * @return UserDTO
     */
    @GetMapping("/users/{id}")
    ResponseDTO findUserById(@PathVariable Integer id);


    /**
     * 修改积分日志
     *
     * @param userAddBonusMsgDTO
     * @return
     */
    @PostMapping("/users/bonus")
    User updateBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO);

    /**
     * 兑换扣积分
     * @param userAddBonusMsgDTO
     * @return
     */
    @PostMapping("/users/reduceBonus")
    User reduceBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO);
}
