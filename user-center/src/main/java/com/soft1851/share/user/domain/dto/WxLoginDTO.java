package com.soft1851.share.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-12 18:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxLoginDTO {
    private String openId;
    private String wxCode;
    private String wxNickname;
    private String avatarUrl;
}
