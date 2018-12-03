package com.wangyuxuan.service;

import com.wangyuxuan.pojo.T_user;

import java.util.Set;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/3 16:02
 * @Description:
 */
public interface UserService {

    /**
     *
     * 功能描述: 根据username查询用户
     *
     * @param: [username]
     * @return: com.wangyuxuan.pojo.T_user
     * @auther: wangyuxuan
     * @date: 2018/12/3 14:49
     */
    T_user findUserByUsername(String username);

    /**
     *
     * 功能描述: 根据username查询该用户的所有角色
     *
     * @param: [username]
     * @return: java.util.Set<java.lang.String>
     * @auther: wangyuxuan
     * @date: 2018/12/3 14:49
     */
    Set<String> findRoles(String username);

    /**
     *
     * 功能描述: 根据username查询他所拥有的权限信息
     *
     * @param: [username]
     * @return: java.util.Set<java.lang.String>
     * @auther: wangyuxuan
     * @date: 2018/12/3 14:50
     */
    Set<String> findPermissions(String username);
}
