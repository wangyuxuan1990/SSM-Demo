package com.wangyuxuan.service.ssmone;

import com.wangyuxuan.dao.ContentLogMapper;
import com.wangyuxuan.pojo.ContentLog;
import com.wangyuxuan.service.ContentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/18 10:51
 * @Description:
 */

@Service
public class ContentLogServiceImpl implements ContentLogService {

    @Autowired
    private ContentLogMapper contentLogMapper;

    @Override
    public int insertSelective(ContentLog record) {
        return contentLogMapper.insertSelective(record);
    }
}
