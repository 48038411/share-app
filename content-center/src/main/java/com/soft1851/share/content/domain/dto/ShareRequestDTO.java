package com.soft1851.share.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-07 9:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShareRequestDTO {
    private Integer userId;
    private String title;
    private Boolean isOriginal;
    private String author;
    private String cover;
    private String summary;
    private Integer price;
    private String downloadUrl;
}
