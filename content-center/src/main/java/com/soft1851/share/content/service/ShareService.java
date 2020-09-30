package com.soft1851.share.content.service;

import com.soft1851.share.content.domain.dto.ShareDTO;

public interface ShareService {
    /**
     * 获得分享详情
     * @return  ShareDTO
     */
    ShareDTO findById(Integer id);

    String getHello();
}
