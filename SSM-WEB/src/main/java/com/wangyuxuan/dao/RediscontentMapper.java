package com.wangyuxuan.dao;

import com.wangyuxuan.pojo.Rediscontent;
import com.wangyuxuan.pojo.RediscontentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RediscontentMapper {
    int countByExample(RediscontentExample example);

    int deleteByExample(RediscontentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rediscontent record);

    int insertSelective(Rediscontent record);

    List<Rediscontent> selectByExample(RediscontentExample example);

    Rediscontent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rediscontent record, @Param("example") RediscontentExample example);

    int updateByExample(@Param("record") Rediscontent record, @Param("example") RediscontentExample example);

    int updateByPrimaryKeySelective(Rediscontent record);

    int updateByPrimaryKey(Rediscontent record);
}