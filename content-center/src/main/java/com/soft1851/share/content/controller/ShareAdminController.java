package com.soft1851.share.content.controller;

import com.soft1851.share.content.auth.CheckAuthorization;
import com.soft1851.share.content.domain.dto.ShareAuditDTO;
import com.soft1851.share.content.domain.entity.Share;
import com.soft1851.share.content.service.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.prefs.BackingStoreException;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-07 14:39
 */
@RestController
@RequestMapping(value = "admin/shares")
@Api(tags = "审核接口", value = "提供审核的 API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {
    private final ShareService shareService;

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核接口", notes = "审核接口")
    @CheckAuthorization(value = "admin")
    public Share audit(@PathVariable Integer id, @RequestBody ShareAuditDTO shareAuditDTO) {
        return shareService.auditById(id, shareAuditDTO);
    }
    @PostMapping("/audit/list")
    @ApiOperation(value = "查询未审核的资源",notes = "查询未审核的资源")
    @CheckAuthorization(value = "admin")
    public List<Share> list(){
        return shareService.getUnAudit();
    }
}
