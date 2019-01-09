package com.wangyuxuan.kafka.orderconsumer.service.impl;


import com.wangyuxuan.kafka.orderconsumer.constant.RedisKeysConstant;
import com.wangyuxuan.kafka.orderconsumer.mapper.StockOrderMapper;
import com.wangyuxuan.kafka.orderconsumer.pojo.Stock;
import com.wangyuxuan.kafka.orderconsumer.pojo.StockOrder;
import com.wangyuxuan.kafka.orderconsumer.service.OrderService;
import com.wangyuxuan.kafka.orderconsumer.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void createOptimisticOrderUseRedisAndKafka(Stock stock) throws Exception {
        //乐观锁更新库存 以及更新 Redis
        saleStockOptimisticByRedis(stock);
        //创建订单
        createOrder(stock);
    }

    private void saleStockOptimisticByRedis(Stock stock) {
        int count = stockService.updateStockByOptimistic(stock);
        if(count == 0){
            throw new RuntimeException("并发更新库存失败");
        }
        //自增
        redisTemplate.opsForValue().increment(RedisKeysConstant.STOCK_SALE + stock.getId(), 1);
        redisTemplate.opsForValue().increment(RedisKeysConstant.STOCK_VERSION + stock.getId(), 1);
    }

    private int createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        int id = orderMapper.insertSelective(order);
        return id;
    }
}
