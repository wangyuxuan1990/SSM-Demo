package com.wangyuxuan.kafka.orderconsumer.kafka;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.wangyuxuan.kafka.orderconsumer.pojo.Stock;
import com.wangyuxuan.kafka.orderconsumer.service.OrderService;
import com.wangyuxuan.kafka.orderconsumer.util.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.RejectedExecutionException;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/9 10:34
 * @Description:
 */

@Slf4j
public class ConsumerTask implements Runnable {

    /**
     * 每个线程维护KafkaConsumer实例
     */
    private final KafkaConsumer<String, String> consumer;

    private OrderService orderService;

    private Gson gson;

    public ConsumerTask(String brokerList, String groupId, String topic) {
        this.orderService = SpringBeanFactory.getBean(OrderService.class);
        this.gson = SpringBeanFactory.getBean(Gson.class);

        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokerList);
        properties.put("group.id", groupId);
        //自动提交位移
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            // 使用200ms作为获取超时时间
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));

            for (ConsumerRecord<String, String> record : records) {
                // 简单地打印消息
                log.info("===" + record.value() + " consumed " + record.partition() +
                        " message with offset: " + record.offset());
                dealMessage(record.value());
            }
        }
    }

    /**
     * 功能描述: 处理消息
     *
     * @param: [value]
     * @return: void
     * @auther: wangyuxuan
     * @date: 2019/1/9 11:02
     */
    private void dealMessage(String value) {
        try {
            Stock stock = gson.fromJson(value, Stock.class);
            log.info("consumer stock={}", JSON.toJSONString(stock));
            //创建订单
            orderService.createOptimisticOrderUseRedisAndKafka(stock);
        } catch (RejectedExecutionException e) {
            log.error("rejected message = " + value);
        } catch (Exception e) {
            log.error("unknown exception", e);
        }
    }
}
