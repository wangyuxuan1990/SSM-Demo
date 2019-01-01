package com.wangyuxuan.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: wangyuxuan
 * @Date: 2019-01-01 15:52
 * @Description: Kafka生产者
 */

@Slf4j
public class Producer {

    /**
     * 消费配置文件
     */
    private static String consumerProPath;

    public static void main(String[] args) throws IOException {
        // set up the producer
        consumerProPath = System.getProperty("product_path");
        KafkaProducer<String, String> producer = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(consumerProPath));
            Properties properties = new Properties();
            properties.load(inputStream);
            producer = new KafkaProducer<>(properties);
        } catch (IOException e) {
            log.error("load config error", e);
        }

        try {
            // send lots of messages
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<>(
                        "test", i + "", i + ""));
            }
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        }
    }
}
