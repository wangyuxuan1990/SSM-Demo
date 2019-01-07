package com.wangyuxuan.seconds.kill.service.impl;

import com.wangyuxuan.seconds.kill.dao.StockMapper;
import com.wangyuxuan.seconds.kill.pojo.Stock;
import com.wangyuxuan.seconds.kill.service.StockService;
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
    public Stock getStockById(int id) {
        return stockMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStockById(Stock stock) {
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

    @Override
    public int updateStockByOptimistic(Stock stock) {
        return stockMapper.updateByOptimistic(stock);
    }
}
