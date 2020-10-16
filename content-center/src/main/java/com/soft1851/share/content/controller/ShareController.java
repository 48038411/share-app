package com.soft1851.share.content.controller;

import com.soft1851.share.content.auth.CheckLogin;
import com.soft1851.share.content.domain.dto.ExchangeDTO;
import com.soft1851.share.content.domain.dto.ShareDTO;
import com.soft1851.share.content.domain.dto.ShareRequestDTO;
import com.soft1851.share.content.domain.dto.UserDTO;
import com.soft1851.share.content.domain.entity.Share;
import com.soft1851.share.content.service.ShareService;
import com.soft1851.share.content.util.JwtOperator;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-30 7:52
 */
@Slf4j
@RestController
@RequestMapping(value = "/shares")
@Api(tags = "分享接口", value = "提供分享相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;
    private final JwtOperator jwtOperator;

    @GetMapping(value = "/one/{id}")
    @ApiOperation(value = "查询指定id的分享详情", notes = "查询指定id的分享详情")
    public ShareDTO getShareById(@PathVariable Integer id) {
        return shareService.findById(id);
    }

    @GetMapping("/query")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) throws Exception {
        if (pageSize > 100) {
            pageSize = 100;
        }
        Integer userId = null;
        if (StringUtils.isNotBlank(token)) {
            Claims claims = this.jwtOperator.getClaimsFromToken(token);
            log.info(claims.toString());
            userId = (Integer) claims.get("id");
//            return this.shareService.query(title, pageNo, pageSize, userId).getList();
        } else {
            log.info("没有token");
//            return this.shareService.query(title,0,0,userId).getList();
        }
        return this.shareService.query(title, pageNo, pageSize, userId).getList();

    }

    @PostMapping(value = "/contribute")
    @ApiOperation(value = "投稿接口", notes = "投稿接口")
    public int contribute(@RequestBody ShareRequestDTO shareRequestDTO) {
        return this.shareService.insert(shareRequestDTO);
    }
    @PostMapping("/exchange")
    @CheckLogin
    @ApiOperation(value = "兑换接口",notes = "兑换接口")
    public Share exchange(@RequestBody ExchangeDTO exchangeDTO) {
        System.out.println(exchangeDTO + ">>>>>>>>>>>>");
        return this.shareService.exchange(exchangeDTO);
    }
    @PostMapping("/myShare")
    @CheckLogin
    @ApiOperation(value = "查询我的兑换",notes = "查询我的兑换")
    public List<Share> getMy(@RequestBody UserDTO userDTO){
        return this.shareService.queryMy(userDTO);
    }
    @PostMapping("/myContribute")
    @CheckLogin
    @ApiOperation(value = "查询我的投稿",notes = "查询我的投稿")
    public List<Share> myContribue(@RequestBody UserDTO userDTO){
        return this.shareService.myContribute(userDTO);
    }
}
