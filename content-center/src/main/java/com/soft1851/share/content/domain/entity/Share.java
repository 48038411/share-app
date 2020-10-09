package com.soft1851.share.content.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @create 2020-09-30 7:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "share")
@ApiModel("分享")
public class Share {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "分享id")
    private Integer id;

    @Column(name = "user_id")
    @ApiModelProperty(name = "id", value = "分享人id")
    private Integer userId;

    @Column(name = "title")
    @ApiModelProperty(name = "title", value = "标题")
    private String title;

    @Column(name = "create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateTime;

    @Column(name = "is_original")
    @ApiModelProperty(name = "isOriginal", value = "是否原创 0：不是 1：是")
    private Boolean isOriginal;

    @Column(name = "author")
    @ApiModelProperty(name = "author", value = "资源作者")
    private String author;

    @Column(name = "cover")
    @ApiModelProperty(name = "cover", value = "封面")
    private String cover;

    @Column(name = "summary")
    @ApiModelProperty(name = "summary", value = "摘要")
    private String summary;

    @Column(name = "price")
    @ApiModelProperty(name = "price", value = "价格")
    private Integer price;

    @Column(name = "download_url")
    @ApiModelProperty(name = "downloadUrl", value = "下载地址")
    private String downloadUrl;

    @Column(name = "buy_count")
    @ApiModelProperty(name = "buyCount", value = "购买数量")
    private Integer buyCount;

    @Column(name = "show_flag")
    @ApiModelProperty(name = "showFlag", value = "是否展示 0：不展示 1：展示 ")
    private Boolean showFlag;

    @Column(name = "audit_status")
    @ApiModelProperty(name = "auditStatus", value = "批阅状态")
    private String auditStatus;

    @Column(name = "reason")
    @ApiModelProperty(name = "reason", value = "不予通过原因")
    private String reason;
}

