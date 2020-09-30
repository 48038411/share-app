package com.soft1851.share.content.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-27 11:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    @Column(name = "content")
    private String content;
    @Column(name = "show_flag")
    private Integer showFlag;
    @Column(name = "create_time")
    private Date createTime;
}
