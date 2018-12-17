package com.wangyuxuan.service;

import com.wangyuxuan.pojo.Datasource;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/17 15:17
 * @Description:
 */
public interface DataSourceService {

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Datasource selectByPrimaryKey(Integer id);
}
