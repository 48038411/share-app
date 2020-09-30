package com.soft1851.share.content.service.impl;

import com.soft1851.share.content.common.ResponseResult;
import com.soft1851.share.content.dao.NoticeMapper;
import com.soft1851.share.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-27 11:44
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;
    @Override
    public ResponseResult getAll() {
        return new ResponseResult(200,"查询成功",noticeMapper.selectAll());
    }
}
