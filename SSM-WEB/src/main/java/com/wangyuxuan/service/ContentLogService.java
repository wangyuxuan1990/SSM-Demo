package com.wangyuxuan.service;

import com.wangyuxuan.pojo.ContentLog;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/18 10:50
 * @Description:
 */
public interface ContentLogService {

    /**
     * 根据条件新增
     * @param record
     * @return
     */
    int insertSelective(ContentLog record);
}
