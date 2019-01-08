package com.wangyuxuan.seconds.kill.service.impl;

import com.wangyuxuan.seconds.kill.api.constant.RedisKeysConstant;
import com.wangyuxuan.seconds.kill.dao.StockOrderMapper;
import com.wangyuxuan.seconds.kill.pojo.Stock;
import com.wangyuxuan.seconds.kill.pojo.StockOrder;
import com.wangyuxuan.seconds.kill.service.OrderService;
import com.wangyuxuan.seconds.kill.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private KafkaProducer kafkaProducer;

    @Value("${kafka.topic}")
    private String kafkaTopic;

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

    @Override
    public int createOptimisticOrder(int sid) throws Exception {
        //校验库存
        Stock stock = checkStock(sid);
        //乐观锁更新库存
        saleStockOptimistic(stock);
        //创建订单
        int id = createOrder(stock);
        return id;
    }

    @Override
    public int createOptimisticOrderUseRedis(int sid) throws Exception {
        //校验库存，从 Redis 获取
        Stock stock = checkStockByRedis(sid);
        //乐观锁更新库存 以及更新 Redis
        saleStockOptimisticByRedis(stock);
        //创建订单
        int id = createOrder(stock);
        return id;
    }

    @Override
    public void createOptimisticOrderUseRedisAndKafka(int sid) throws Exception {
        //校验库存，从 Redis 获取
        Stock stock = checkStockByRedis(sid);

        //利用 Kafka 创建订单
        kafkaProducer.send(new ProducerRecord(kafkaTopic, stock));
        log.info("send Kafka success");
    }

    private Stock checkStock(int sid) {
        Stock stock = stockService.getStockById(sid);
        if (stock.getSale().equals(stock.getCount())) {
            throw new RuntimeException("库存不足");
        }
        return stock;
    }

    private Stock checkStockByRedis(int sid) {
        Integer count = Integer.parseInt(redisTemplate.opsForValue().get(RedisKeysConstant.STOCK_COUNT + sid));
        Integer sale = Integer.parseInt(redisTemplate.opsForValue().get(RedisKeysConstant.STOCK_SALE + sid));
        if (count.equals(sale)) {
            throw new RuntimeException("库存不足 Redis currentCount=" + sale);
        }
        Integer version = Integer.parseInt(redisTemplate.opsForValue().get(RedisKeysConstant.STOCK_VERSION + sid));
        String name = redisTemplate.opsForValue().get(RedisKeysConstant.STOCK_NAME + sid);
        Stock stock = new Stock();
        stock.setId(sid);
        stock.setCount(count);
        stock.setSale(sale);
        stock.setVersion(version);
        stock.setName(name);
        return stock;
    }

    private int saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        return stockService.updateStockById(stock);
    }

    private void saleStockOptimistic(Stock stock) {
        int count = stockService.updateStockByOptimistic(stock);
        if(count == 0){
            throw new RuntimeException("并发更新库存失败");
        }
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
