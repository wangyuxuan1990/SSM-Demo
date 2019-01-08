package com.wangyuxuan.seconds.kill.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangyuxuan.seconds.kill.api.OrderService;

import javax.annotation.Resource;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 15:28
 * @Description:
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Resource(name = "DBOrderService")
    private com.wangyuxuan.seconds.kill.service.OrderService orderService;

    @Override
    public int createWrongOrder(int sid) throws Exception {
        return orderService.createWrongOrder(sid);
    }

    @Override
    public int createOptimisticOrder(int sid) throws Exception {
        return orderService.createOptimisticOrder(sid);
    }

    @Override
    public int createOptimisticOrderUseRedis(int sid) throws Exception {
        return orderService.createOptimisticOrderUseRedis(sid);
    }

    @Override
    public void createOptimisticOrderUseRedisAndKafka(int sid) throws Exception {
        orderService.createOptimisticOrderUseRedisAndKafka(sid);
    }
}
