package com.soft1851.share.content.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "mid_user_share")
public class MidUserShare {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "share_id")
    private Integer shareId;

    @Column(name = "user_id")
    private Integer userId;
}
