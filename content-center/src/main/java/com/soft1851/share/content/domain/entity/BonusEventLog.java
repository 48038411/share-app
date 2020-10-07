package com.soft1851.share.content.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-07 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonusEventLog {
    private Integer id;
    private Integer userId;
    private Integer value;
    private String event;
    private Date createTime;
    private String description;
}
