package com.soft1851.share.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-14 22:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
    private Boolean succ;
    private String code;
    private String msg;
    private Object data;
    private Long ts;
}
