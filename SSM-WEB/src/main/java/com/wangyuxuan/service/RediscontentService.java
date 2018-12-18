package com.wangyuxuan.service;

import com.wangyuxuan.pojo.PageEntityNew;
import com.wangyuxuan.pojo.Rediscontent;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/11 13:34
 * @Description:
 */
public interface RediscontentService {

    Rediscontent selectByPrimaryKey(Integer id);

    PageEntityNew<Rediscontent> selectByPage(Integer pageNum, Integer pageSize);
}
