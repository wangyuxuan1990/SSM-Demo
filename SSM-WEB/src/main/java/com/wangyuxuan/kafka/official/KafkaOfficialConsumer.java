package com.wangyuxuan.kafka.official;

import com.wangyuxuan.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Auther: wangyuxuan
 * @Date: 2019-01-01 18:16
 * @Description: kafka官方消费
 */

@Slf4j
public class KafkaOfficialConsumer {

    /**
     * 日志文件地址
     */
    private static String logPath;

    /**
     * 主题名称
     */
    private static String topic;

    /**
     * 消费配置文件
     */
    private static String consumerProPath;

    /**
     * 初始化参数校验
     *
     * @return
     */
    private static boolean initCheck() {
        topic = System.getProperty("topic");
        logPath = System.getProperty("log_path");
        consumerProPath = System.getProperty("consumer_pro_path");

        if (StringUtil.isEmpty(topic) || StringUtil.isEmpty(logPath) || consumerProPath.isEmpty()) {
            log.error("system property topic ,consumer_pro_path, log_path is required !");
            return true;
        }

        return false;
    }

    /**
     * 初始化kafka配置
     *
     * @return
     */
    private static KafkaConsumer<String, String> initKafkaConsumer() {
        KafkaConsumer<String, String> consumer = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(consumerProPath));
            Properties properties = new Properties();
            properties.load(inputStream);
            consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(Arrays.asList(topic));
        } catch (IOException e) {
            log.error("加载consumer.properties文件出错", e);
        }
        return consumer;
    }

    public static void main(String[] args) {
        if (initCheck()) {
            return;
        }

        int totalCount = 0;
        long totalMin = 0L;
        int count = 0;

        KafkaConsumer<String, String> consumer = initKafkaConsumer();

        long startTime = System.currentTimeMillis();

        //消费消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));
            if (records.count() <= 0) {
                continue;
            }

            log.debug("本次获取:" + records.count());
            count += records.count();
            long endTime = System.currentTimeMillis();
            log.debug("count=" + count);

            if (count >= 10000) {
                totalCount += count;
                log.info("this consumer {} record，use {} milliseconds", count, endTime - startTime);
                totalMin += (endTime - startTime);
                startTime = System.currentTimeMillis();
                count = 0;
            }
            log.debug("end totalCount={},min={}", totalCount, totalMin);

            /*for (ConsumerRecord<String, String> record : records) {
                record.value() ;
                JsonNode msg = null;
                try {
                    msg = mapper.readTree(record.value());
                } catch (IOException e) {
                    log.error("消费消息出错", e);
                }
                log.info("kafka receive = "+msg.toString());
            }*/
        }
    }
}
