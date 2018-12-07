package com.wangyuxuan.service.ssmone;

import com.wangyuxuan.dao.ContentMapper;
import com.wangyuxuan.pojo.Content;
import com.wangyuxuan.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/7 10:37
 * @Description:
 */

@Service("contentService")
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public int insertSelective(Content content) {
        return contentMapper.insertSelective(content);
    }

    @Override
    public List<Content> findContentList() {
        return contentMapper.findContentList();
    }
}
