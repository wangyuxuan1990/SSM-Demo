package com.wangyuxuan.api;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/18 16:13
 * @Description:
 */
public interface DemoApi {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     * @throws RuntimeException
     */
    String readMsg(int userId) throws RuntimeException;
}
