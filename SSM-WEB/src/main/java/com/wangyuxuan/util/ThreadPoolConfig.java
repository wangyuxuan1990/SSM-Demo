package com.wangyuxuan.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/28 17:08
 * @Description:
 */

@Component
public class ThreadPoolConfig {

    private static final int MAX_SIZE = 10;
    private static final int CORE_SIZE = 5;
    private static final int SECOND = 1000;

    private ThreadPoolExecutor executor;

    public ThreadPoolConfig() {
        this.executor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, SECOND, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public void submit(Thread thread){
        executor.submit(thread);
    }
}
