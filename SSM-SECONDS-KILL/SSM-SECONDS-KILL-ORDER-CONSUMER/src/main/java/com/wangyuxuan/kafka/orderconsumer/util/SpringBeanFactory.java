package com.wangyuxuan.kafka.orderconsumer.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/9 10:12
 * @Description:
 */

@Component
public final class SpringBeanFactory implements ApplicationContextAware {
    public static ApplicationContext context;

    public static <T> T getBean(Class<T> c){
        return context.getBean(c);
    }

    public static <T> T getBean(String name, Class<T> c){
        return context.getBean(name, c);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
