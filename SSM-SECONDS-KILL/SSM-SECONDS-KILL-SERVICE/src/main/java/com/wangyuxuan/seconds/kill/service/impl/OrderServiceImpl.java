package com.wangyuxuan.seconds.kill.service.impl;

import com.wangyuxuan.seconds.kill.dao.StockOrderMapper;
import com.wangyuxuan.seconds.kill.pojo.Stock;
import com.wangyuxuan.seconds.kill.pojo.StockOrder;
import com.wangyuxuan.seconds.kill.service.OrderService;
import com.wangyuxuan.seconds.kill.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 15:31
 * @Description:
 */

@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service(value = "DBOrderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StockOrderMapper orderMapper;

    @Resource(name = "DBStockService")
    private StockService stockService;

    @Override
    public int createWrongOrder(int sid) throws Exception {

        //校验库存
        Stock stock = checkStock(sid);
        //扣库存
        saleStock(stock);
        //创建订单
        int id = createOrder(stock);
        return id;
    }

    private Stock checkStock(int sid) {
        Stock stock = stockService.getStockById(sid);
        if (stock.getSale().equals(stock.getCount())) {
            throw new RuntimeException("库存不足");
        }
        return stock;
    }

    private int saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        return stockService.updateStockById(stock);
    }

    private int createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        int id = orderMapper.insertSelective(order);
        return id;
    }
}
