package com.wangyuxuan.kafka.orderconsumer.service;


import com.wangyuxuan.kafka.orderconsumer.pojo.Stock;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 15:59
 * @Description:
 */
public interface StockService {

    /**
     * 乐观锁更新库存
     * @param stock
     * return
     */
    int updateStockByOptimistic(Stock stock);
}
