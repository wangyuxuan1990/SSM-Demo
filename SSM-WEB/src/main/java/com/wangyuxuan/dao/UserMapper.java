package com.wangyuxuan.dao;

import com.wangyuxuan.pojo.User;
import com.wangyuxuan.pojo.UserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithBLOBs(UserExample example);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    /**
     *
     * 功能描述: 分页查询
     *
     * @param: [map]
     * @return: java.util.List<com.wangyuxuan.pojo.User>
     * @auther: wangyuxuan
     * @date: 2018/11/28 16:09
     */
    List<User> list(Map<String, Object> map);

    /**
     *
     * 功能描述: 获取全部
     *
     * @param: [map]
     * @return: java.lang.Long
     * @auther: wangyuxuan
     * @date: 2018/11/28 16:09
     */
    Long getTotal(Map<String, Object> map);
}