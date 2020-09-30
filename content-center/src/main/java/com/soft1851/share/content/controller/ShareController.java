package com.soft1851.share.content.controller;

import com.soft1851.share.content.domain.dto.ShareDTO;
import com.soft1851.share.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-30 7:52
 */
@RestController
@RequestMapping(value =  "/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;

    @GetMapping(value = "/{id}")
    public ShareDTO findById(@PathVariable Integer id) {
        return this.shareService.findById(id);
    }

    @GetMapping(value = "/hello")
    public String getHello(){
        return this.shareService.getHello();
    }
}
