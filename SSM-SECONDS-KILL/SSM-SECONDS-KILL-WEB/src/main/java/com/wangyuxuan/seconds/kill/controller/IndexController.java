package com.wangyuxuan.seconds.kill.controller;

import com.crossoverjie.distributed.annotation.SpringControllerLimit;
import com.wangyuxuan.seconds.kill.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 13:49
 * @Description:
 */

@Slf4j
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/health")
    @ResponseBody
    public String health() {
        log.info("health");
        return "OK";
    }

    @RequestMapping("/createWrongOrder/{sid}")
    @ResponseBody
    public String createWrongOrder(@PathVariable int sid) {
        log.info("sid = [{}]", sid);
        int id = 0;
        try {
            id = orderService.createWrongOrder(sid);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return String.valueOf(id);
    }

    /**
     * 乐观锁更新库存
     * @param sid
     * @return
     */
    @RequestMapping("/createOptimisticOrder/{sid}")
    @ResponseBody
    public String createOptimisticOrder(@PathVariable int sid) {
        log.info("sid = [{}]", sid);
        int id = 0;
        try {
            id = orderService.createOptimisticOrder(sid);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return String.valueOf(id);
    }

    /**
     * 乐观锁更新库存 限流
     * @param sid
     * @return
     */
    @SpringControllerLimit(errorCode = 200)
    @RequestMapping("/createOptimisticLimitOrder/{sid}")
    @ResponseBody
    public String createOptimisticLimitOrder(@PathVariable int sid) {
        log.info("sid = [{}]", sid);
        int id = 0;
        try {
            id = orderService.createOptimisticOrder(sid);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return String.valueOf(id);
    }

    /**
     * 乐观锁更新库存 限流 库存改为查询 Redis 提高性能
     * @param sid
     * @return
     */
    @SpringControllerLimit(errorCode = 200, errorMsg = "request has limited")
    @RequestMapping("/createOptimisticLimitOrderByRedis/{sid}")
    @ResponseBody
    public String createOptimisticLimitOrderByRedis(@PathVariable int sid) {
        log.info("sid = [{}]", sid);
        int id = 0;
        try {
            id = orderService.createOptimisticOrderUseRedis(sid);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return String.valueOf(id);
    }

    /**
     * 乐观锁更新库存 限流 库存改为查询 Redis 提高性能
     * 异步创建订单 Kafka
     * @param sid
     * @return
     */
    @SpringControllerLimit
    @RequestMapping("/createOptimisticLimitOrderByRedisAndKafka/{sid}")
    @ResponseBody
    public String createOptimisticLimitOrderByRedisAndKafka(@PathVariable int sid) {
        log.info("sid = [{}]", sid);
        int id = 0;
        try {
            orderService.createOptimisticOrderUseRedisAndKafka(sid);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return String.valueOf(id);
    }
}
