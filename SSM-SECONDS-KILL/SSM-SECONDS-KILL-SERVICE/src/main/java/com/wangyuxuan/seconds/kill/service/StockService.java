package com.wangyuxuan.seconds.kill.service;

import com.wangyuxuan.seconds.kill.pojo.Stock;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 15:59
 * @Description:
 */
public interface StockService {

    /**
     * 根据库存 ID 查询库存信息
     * @param id
     * @return
     */
    Stock getStockById(int id);

    /**
     * 更新库存信息
     * @param stock
     * return
     */
    int updateStockById(Stock stock);

    /**
     * 乐观锁更新库存
     * @param stock
     * return
     */
    int updateStockByOptimistic(Stock stock);
}
