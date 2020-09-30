package com.soft1851.share.content.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-19 23:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

}