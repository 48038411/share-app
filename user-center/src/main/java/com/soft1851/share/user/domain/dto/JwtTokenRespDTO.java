package com.soft1851.share.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-13 15:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenRespDTO {
    private String token;
    private Long expirationTime;
    private String role;
}
