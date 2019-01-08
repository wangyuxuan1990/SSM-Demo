package com.wangyuxuan.seconds.kill.service;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 15:29
 * @Description:
 */
public interface OrderService {

    /**
     *
     * 功能描述: 创建订单
     *
     * @param: [sid] 库存ID
     * @return: int 订单ID
     * @auther: wangyuxuan
     * @date: 2019/1/4 15:26
     */
    int createWrongOrder(int sid) throws Exception;

    /**
     * 创建订单 乐观锁
     * @param sid
     * @return
     * @throws Exception
     */
    int createOptimisticOrder(int sid) throws Exception;

    /**
     * 创建订单 乐观锁，库存查 Redis 减小 DB 压力。
     * @param sid
     * @return
     * @throws Exception
     */
    int createOptimisticOrderUseRedis(int sid) throws Exception;

    /**
     * 创建订单 乐观锁，库存查 Redis 减小 DB 压力。
     * 利用 Kafka 异步写订单
     * @param sid
     * @return
     * @throws Exception
     */
    void createOptimisticOrderUseRedisAndKafka(int sid) throws Exception;

}
