package com.soft1851.share.content.controller;

import com.soft1851.share.content.common.ResponseResult;
import com.soft1851.share.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-27 11:45
 */
@RestController
@RequestMapping(value = "/notice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = "/list")
    public ResponseResult getAll(){
        return noticeService.getAll();
    }
}
