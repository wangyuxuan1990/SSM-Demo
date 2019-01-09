package com.wangyuxuan.kafka.orderconsumer.service;

import com.wangyuxuan.kafka.orderconsumer.pojo.Stock;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 15:29
 * @Description:
 */
public interface OrderService {

    /**
     * 创建订单 乐观锁，库存查 Redis 减小 DB 压力。
     * 利用 Kafka 异步写订单
     * @param stock
     * @return
     * @throws Exception
     */
    void createOptimisticOrderUseRedisAndKafka(Stock stock) throws Exception;

}
