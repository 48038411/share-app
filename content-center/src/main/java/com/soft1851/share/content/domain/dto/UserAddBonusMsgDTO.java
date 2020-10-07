package com.soft1851.share.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-07 14:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusMsgDTO {
    private Integer userId;
    private Integer bonus;
}
