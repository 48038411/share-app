package com.soft1851.share.content.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ShareServiceTest {
    @Autowired
    private ShareService shareService;
    @Test
    void query() {
        System.out.println(shareService.query(null,1,10,null));
    }
}