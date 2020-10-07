package com.soft1851.share.content.domain.dto;

import com.soft1851.share.content.domain.enums.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-07 14:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareAuditDTO {
    private AuditStatusEnum auditStatusEnum;
    private String reason;
}
