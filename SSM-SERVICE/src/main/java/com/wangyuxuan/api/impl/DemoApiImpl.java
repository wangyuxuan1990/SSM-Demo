package com.wangyuxuan.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangyuxuan.api.DemoApi;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/19 10:27
 * @Description:
 */

@Service
public class DemoApiImpl implements DemoApi {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     * @throws RuntimeException
     */
    @Override
    public String readMsg(int userId) throws RuntimeException {
        return null;
    }
}
