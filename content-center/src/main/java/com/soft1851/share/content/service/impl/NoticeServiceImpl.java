package com.soft1851.share.content.service.impl;

import com.soft1851.share.content.common.ResponseResult;
import com.soft1851.share.content.dao.NoticeMapper;
import com.soft1851.share.content.domain.entity.Notice;
import com.soft1851.share.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public Notice getLatest() {
        Example example = new Example(Notice.class);
        //按id降序
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showFlag", 1);
        return noticeMapper.selectByExample(example).get(0);
    }
}
