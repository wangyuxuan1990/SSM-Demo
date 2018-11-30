package com.wangyuxuan.service;

import com.wangyuxuan.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/11/28 15:33
 * @Description:
 */
public interface IUserService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    List<User> list(Map<String, Object> map);

    /**
     * 获取全部
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    /**
     *
     * 功能描述: 通过userId获取User信息
     *
     * @param: [userId]
     * @return: com.wangyuxuan.pojo.User
     * @auther: wangyuxuan
     * @date: 2018/11/30 14:48
     */
    User getUserById(int userId);
}
