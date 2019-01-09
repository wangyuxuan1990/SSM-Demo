package com.wangyuxuan.kafka.orderconsumer.service.impl;


import com.wangyuxuan.kafka.orderconsumer.mapper.StockMapper;
import com.wangyuxuan.kafka.orderconsumer.pojo.Stock;
import com.wangyuxuan.kafka.orderconsumer.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 16:01
 * @Description:
 */

@Service(value = "DBStockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public int updateStockByOptimistic(Stock stock) {
        return stockMapper.updateByOptimistic(stock);
    }
}
