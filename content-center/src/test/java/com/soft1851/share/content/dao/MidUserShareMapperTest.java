package com.soft1851.share.content.dao;

import com.soft1851.share.content.domain.entity.MidUserShare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MidUserShareMapperTest {
    @Autowired
    private MidUserShareMapper midUserShareMapper;

    @Test
    void select() {
        System.out.println(midUserShareMapper.selectOne(
                MidUserShare.builder().userId(1).shareId(1).build()
        ));
    }
}