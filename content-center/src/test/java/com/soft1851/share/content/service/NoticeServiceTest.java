package com.soft1851.share.content.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceTest {
    @Autowired
    private NoticeService noticeService;

    @Test
    void getLatest() {
        System.out.println(noticeService.getLatest());
    }
}