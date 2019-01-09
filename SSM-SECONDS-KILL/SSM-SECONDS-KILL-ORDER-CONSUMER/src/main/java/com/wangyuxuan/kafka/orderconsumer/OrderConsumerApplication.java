package com.wangyuxuan.kafka.orderconsumer;

import com.wangyuxuan.kafka.orderconsumer.kafka.ConsumerGroup;
import com.wangyuxuan.kafka.orderconsumer.util.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.system.ApplicationPidFileWriter;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = MapperAutoConfiguration.class, scanBasePackages = "com.wangyuxuan.kafka.orderconsumer")
@MapperScan(basePackages = "com.wangyuxuan.kafka.orderconsumer.mapper")
public class OrderConsumerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(OrderConsumerApplication.class)
                .listeners(new ApplicationPidFileWriter())
                .web(false)
                .run(args);
        ConsumerGroup consumerGroup = SpringBeanFactory.getBean(ConsumerGroup.class);
        consumerGroup.execute();
        log.info("启动成功");
    }

}

