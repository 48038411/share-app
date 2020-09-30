package com.soft1851.share.user.service;

import com.soft1851.share.user.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserServiceTest {
    private final UserService userService;
    @Test
    void login() {
//        String account = "48038411";
//        String password = "123456";
//        UserDTO userDTO = UserDTO.builder().account(account).password(password).build();
//        System.out.println(userService.login(userDTO));
    }

    @Test
    void getUserInfo() {
    }
}