package com.wangyuxuan.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangyuxuan.pojo.Datasource;
import com.wangyuxuan.service.DataSourceService;
import com.wangyuxuan.util.Constants;
import com.wangyuxuan.util.DataSourceHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/17 15:48
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/*.xml"})
public class DataSourceServiceImplTest {

    @Autowired
    private DataSourceService dataSourceService;

    @Test
    public void selectByPrimaryKey() throws Exception{
        DataSourceHolder.setDataSources(Constants.DATASOURCE_TWO);
        Datasource datasource = dataSourceService.selectByPrimaryKey(7);
        System.out.println(JSON.toJSONString(datasource));
    }

    @Test
    public void selectByPrimaryKey2() throws Exception{
        Datasource datasource = dataSourceService.selectByPrimaryKey(7);
        System.out.println(JSON.toJSONString(datasource));
    }

}
