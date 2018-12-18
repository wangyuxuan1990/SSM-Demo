package com.wangyuxuan.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangyuxuan.pojo.Rediscontent;
import com.wangyuxuan.service.RediscontentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/18 10:03
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/*.xml"})
public class RedisContentServiceImplTest {

    @Autowired
    private RediscontentService rediscontentService;

    @Test
    public void selectByPrimaryKey() throws Exception {
        Rediscontent rediscontent = rediscontentService.selectByPrimaryKey(30);
        System.out.println(JSON.toJSONString(rediscontent));
    }
}
