package com.soft1851.share.user.service;

import com.soft1851.share.user.domain.dto.UserSignInDTO;
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
    void singIn() {
        System.out.println(userService.signIn(UserSignInDTO.builder().userId(5).build()));
    }
}