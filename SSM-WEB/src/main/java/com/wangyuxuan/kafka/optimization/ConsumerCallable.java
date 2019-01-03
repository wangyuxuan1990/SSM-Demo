package com.wangyuxuan.kafka.optimization;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/3 15:29
 * @Description:
 */

@Slf4j
public class ConsumerCallable implements Callable<ConsumerFuture> {

    private AtomicInteger totalCount = new AtomicInteger();
    private AtomicInteger count = new AtomicInteger();
    private AtomicLong totalTime = new AtomicLong();
    /**
     * 每个线程维护KafkaConsumer实例
     */
    private final KafkaConsumer<String, String> consumer;

    public ConsumerCallable(String brokerList, String groupId, String topic) {
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

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public ConsumerFuture call() throws Exception {
        boolean flag = true;
        int failPollTimes = 0;
        long startTime = System.currentTimeMillis();
        while (flag) {
            // 使用200ms作为获取超时时间
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));
            if(records.count() <= 0){
                failPollTimes++;
                if(failPollTimes >= 20){
                    log.debug("达到{}次数，退出", failPollTimes);
                    flag = false;
                }
                continue;
            }

            //获取到之后则清零
            failPollTimes = 0;

            log.debug("本次获取："+records.count());
            count.addAndGet(records.count());
            totalCount.addAndGet(count.get());
            long endTime = System.currentTimeMillis();
            if(count.get() > 10000){
                log.info("this consumer {} record，use {} milliseconds",count,endTime-startTime);
                totalTime.addAndGet(endTime-startTime);
                startTime = System.currentTimeMillis();
                count = new AtomicInteger();
            }
            log.debug("end totalCount={},min={}",totalCount,totalTime);

            /*for (ConsumerRecord<String, String> record : records) {
                // 简单地打印消息
                LOGGER.debug(record.value() + " consumed " + record.partition() +
                        " message with offset: " + record.offset());
            }*/
        }
        ConsumerFuture consumerFuture = new ConsumerFuture(totalCount.get(), totalTime.get());
        return consumerFuture;
    }
}
