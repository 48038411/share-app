package com.soft1851.share.content.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-29 22:45
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    NOT_YET,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECT
}
