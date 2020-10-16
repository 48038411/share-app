package com.soft1851.share.content.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.share.content.domain.dto.*;
import com.soft1851.share.content.domain.entity.Share;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

/**
 * @author Guorc
 */
public interface ShareService {
    /**
     * 获得分享详情
     *
     * @return ShareDTO
     */
    ShareDTO findById(Integer id);

    /**
     * 投稿接口
     *
     * @param shareRequestDTO
     * @return
     */
    int insert(ShareRequestDTO shareRequestDTO);

    /**
     * 根据标题模糊查询某个用户的分享列表数据，title为空则为所有数据，查询结果分页
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return PageInfo<Share>
     */
    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);

    /**
     * 审核投稿
     *
     * @param id
     * @param shareAuditDTO
     * @return
     */
    Share auditById(Integer id, ShareAuditDTO shareAuditDTO);

    /**
     * 积分兑换资源
     *
     * @param exchangeDTO
     * @return Share
     */
    Share exchange(ExchangeDTO exchangeDTO);

    /**
     * 根据用户id查询该用户兑换的资源
     * @param userDTO
     * @return
     */
    List<Share> queryMy(UserDTO userDTO);

    /**
     * 根据用户id查询该用户投稿的资源
     * @param userDTO
     * @return
     */
    List<Share> myContribute(UserDTO userDTO);

    /**
     * 查询未审核的帖子
     * @return
     */
    List<Share> getUnAudit();
}
