package com.soft1851.share.content.service;

import com.soft1851.share.content.common.ResponseResult;
import com.soft1851.share.content.domain.dto.NoticeDTO;
import com.soft1851.share.content.domain.entity.Notice;

/**
 * @author Guorc
 */
public interface NoticeService {
    /**
     * 查询最新公告
     *
     * @return
     */
    Notice getLatest();

    /**
     * 新增公告
     * @param noticeDTO
     * @return
     */
    Notice addNotice(NoticeDTO noticeDTO);
}
