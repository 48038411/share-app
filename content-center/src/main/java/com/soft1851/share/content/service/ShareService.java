package com.soft1851.share.content.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.share.content.domain.dto.ShareDTO;
import com.soft1851.share.content.domain.entity.Share;

public interface ShareService {
    /**
     * 获得分享详情
     * @return  ShareDTO
     */
    ShareDTO findById(Integer id);

    String getHello();

    /**
     * 根据标题模糊查询某个用户的分享列表数据，title为空则为所有数据，查询结果分页
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return PageInfo<Share>
     */
    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);
}
