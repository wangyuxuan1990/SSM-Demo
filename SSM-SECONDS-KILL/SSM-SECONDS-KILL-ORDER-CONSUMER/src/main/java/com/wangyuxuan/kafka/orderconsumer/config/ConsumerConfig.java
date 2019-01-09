package com.wangyuxuan.kafka.orderconsumer.config;

import com.google.gson.Gson;
import com.wangyuxuan.kafka.orderconsumer.kafka.ConsumerGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.autoconfigure.MapperProperties;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/9 11:20
 * @Description:
 */

@Slf4j
@Configuration
@EnableConfigurationProperties(MapperProperties.class)
public class ConsumerConfig {

    @Value("${consumer.broker.list}")
    private String brokerList;

    @Value("${consumer.group.id}")
    private String groupId;

    @Value("${consumer.topic}")
    private String topic;

    @Value("${consumer.thread}")
    private int threadNum;

    @Autowired
    private MapperProperties properties;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Bean(value = "consumerGroup")
    public ConsumerGroup createConsumerGroup() {
        ConsumerGroup consumerGroup = new ConsumerGroup(threadNum, groupId, topic, brokerList);
        log.info("ConsumerGroup 初始化成功");
        return consumerGroup;
    }

    @Bean
    public Gson build() {
        return new Gson();
    }

    @PostConstruct
    public void addPageInterceptor() {
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setConfig(properties);
        if (properties.getMappers().size() > 0) {
            for (Class mapper : properties.getMappers()) {
                //提前初始化MapperFactoryBean,注册mappedStatements
                applicationContext.getBeansOfType(mapper);
                mapperHelper.registerMapper(mapper);
            }
        } else {
            //提前初始化MapperFactoryBean,注册mappedStatements
            applicationContext.getBeansOfType(Mapper.class);
            mapperHelper.registerMapper(Mapper.class);
        }
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
        }
    }
}
