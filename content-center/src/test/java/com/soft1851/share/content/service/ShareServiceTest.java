//package com.soft1851.share.content.service;
//
//import com.soft1851.share.content.domain.dto.ShareRequestDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class ShareServiceTest {
//    @Autowired
//    private ShareService shareService;
//    @Test
//    void query() {
//        System.out.println(shareService.query(null,1,10,null));
//    }
//
//    @Test
//    void insert() {
//        ShareRequestDTO shareRequestDTO = ShareRequestDTO.builder()
//                .userId(1)
//                .author("郭瑞昌")
//                .title("Java")
//                .cover("wu")
//                .downloadUrl("http://www.baidu.com")
//                .isOriginal(true)
//                .price(68)
//                .summary("没")
//                .build();
//        System.out.println(shareService.insert(shareRequestDTO));
//    }
//}