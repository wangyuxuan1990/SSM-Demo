package com.wangyuxuan.service;

import com.alibaba.fastjson.JSON;
import com.wangyuxuan.dao.PriceMapper;
import com.wangyuxuan.pojo.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/28 16:18
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/*.xml"})
public class PriceTestTest {

    @Autowired
    private PriceMapper priceMapper;

    @Test
    public void selectByPrimaryKey() throws Exception {
        Price price = priceMapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(price));
    }

    /**
     * 功能描述: 单线程消费
     *
     * @param: []
     * @return: void
     * @auther: wangyuxuan
     * @date: 2018/12/28 16:42
     */
    @Test
    public void singleCounsumerTest1() {
        for (int i = 0; i < 100; i++) {
            Price price = priceMapper.selectByPrimaryKey(1);
            int ron = 10;
            price.setFront(price.getFront().subtract(new BigDecimal(ron)));
            price.setEnd(price.getEnd().add(new BigDecimal(ron)));
            price.setTotal(price.getFront().add(price.getEnd()));
            priceMapper.updateByPrimaryKey(price);

            price.setId(null);
            priceMapper.insertSelective(price);
        }
    }
}
