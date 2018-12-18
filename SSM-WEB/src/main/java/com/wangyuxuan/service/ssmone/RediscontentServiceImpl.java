package com.wangyuxuan.service.ssmone;

import com.github.pagehelper.PageHelper;
import com.wangyuxuan.dao.RediscontentMapper;
import com.wangyuxuan.pojo.PageEntityNew;
import com.wangyuxuan.pojo.Rediscontent;
import com.wangyuxuan.pojo.RediscontentExample;
import com.wangyuxuan.service.RediscontentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/11 13:49
 * @Description:
 */

@Service
public class RediscontentServiceImpl implements RediscontentService {

    @Autowired
    private RediscontentMapper rediscontentMapper;

    @Override
    public Rediscontent selectByPrimaryKey(Integer id) {
        return rediscontentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageEntityNew<Rediscontent> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Rediscontent> rediscontents = rediscontentMapper.selectByExample(new RediscontentExample());
        PageEntityNew<Rediscontent> rediscontentPageEntityNew = new PageEntityNew<>();
        rediscontentPageEntityNew.setList(rediscontents);
        int size = rediscontentMapper.selectByExample(new RediscontentExample()).size();
        rediscontentPageEntityNew.setCount(size);
        return rediscontentPageEntityNew;
    }
}
