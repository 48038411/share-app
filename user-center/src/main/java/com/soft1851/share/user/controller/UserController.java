package com.soft1851.share.user.controller;

import com.soft1851.share.user.common.ResponseResult;
import com.soft1851.share.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.share.user.domain.dto.UserDTO;
import com.soft1851.share.user.domain.entity.User;
import com.soft1851.share.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-27 11:51
 */
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/q")
    public UserDTO query(UserDTO user) {
        return user;
    }

    @GetMapping(value = "/{id}")
    public User findUserById(@PathVariable Integer id) {
        log.info("我被请求了...");
        return this.userService.findById(id);
    }

    @PostMapping(value = "/bonus")
    public User updateBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO) {
        return userService.updateBonus(userAddBonusMsgDTO);
    }
}
