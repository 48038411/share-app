package com.soft1851.share.user.domain.dto;

import com.soft1851.share.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-12 13:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResDTO {
    private UserRespDTO user;
    private JwtTokenRespDTO token;
}
