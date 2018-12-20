package com.wangyuxuan.api;

import com.wangyuxuan.dto.UserInfoRsp;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/20 10:06
 * @Description:
 */
public interface UserInfoApi {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    UserInfoRsp getUserInfo(int userId) throws Exception;
}
