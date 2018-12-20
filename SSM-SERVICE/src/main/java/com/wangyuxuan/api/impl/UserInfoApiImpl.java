package com.wangyuxuan.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangyuxuan.api.UserInfoApi;
import com.wangyuxuan.api.dubbo.pojo.T_user;
import com.wangyuxuan.api.dubbo.service.UserService;
import com.wangyuxuan.api.dubbo.util.CommonUtil;
import com.wangyuxuan.dto.UserInfoRsp;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/20 10:13
 * @Description:
 */

@Slf4j
@Service
public class UserInfoApiImpl implements UserInfoApi {

    @Resource
    private UserService userService;

    /**
     *
     * 功能描述: 获取用户信息
     *
     * @param: [userId]
     * @return: com.wangyuxuan.dto.UserInfoRsp
     * @auther: wangyuxuan
     * @date: 2018/12/20 10:35
     */
    @Override
    public UserInfoRsp getUserInfo(int userId) throws Exception {
        log.info("用户查询ID={}", userId);
        //返回对象
        UserInfoRsp userInfoRsp = new UserInfoRsp();
        T_user t_user = userService.selectByPrimaryKey(userId);
        //构建
        CommonUtil.setLogValueModelToModel(t_user, userInfoRsp);
        return userInfoRsp;
    }
}
