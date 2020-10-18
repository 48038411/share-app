package com.soft1851.share.content.controller;

import com.soft1851.share.content.common.ResponseResult;
import com.soft1851.share.content.domain.dto.NoticeDTO;
import com.soft1851.share.content.domain.entity.Notice;
import com.soft1851.share.content.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-27 11:45
 */
@RestController
@RequestMapping(value = "/notice")
@Api(tags = "公告接口", value = "提供公告相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = "/one")
    @ApiOperation(value = "查询最新一条公告", notes = "查询最新一条公告")
    public Notice getTopNotice() {
        return this.noticeService.getLatest();

    }
    @PostMapping(value = "/add")
    @ApiOperation(value = "新增公告",notes = "新增公告")
    public Notice add(@RequestBody NoticeDTO noticeDTO){
        return this.noticeService.addNotice(noticeDTO);
    }
}
