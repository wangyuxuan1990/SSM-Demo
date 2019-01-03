package com.wangyuxuan.kafka.optimization;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/3 15:20
 * @Description:
 */

@Slf4j
public class ConsumerGroup {

    /**
     * 线程池
     */
    private ExecutorService threadPool;

    private List<ConsumerCallable> consumers;

    public ConsumerGroup(int threadNum, String groupId, String topic, String brokerList) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-pool-%d").build();

        threadPool = new ThreadPoolExecutor(threadNum, threadNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        consumers = new ArrayList<>(threadNum);
        for (int i = 0; i < threadNum; i++) {
            ConsumerCallable consumerThread = new ConsumerCallable(brokerList, groupId, topic);
            consumers.add(consumerThread);
        }
    }

    /**
     * 执行任务
     */
    public void execute() {
        long startTime = System.currentTimeMillis();
        for (ConsumerCallable runnable : consumers) {
            Future<ConsumerFuture> future = threadPool.submit(runnable);
        }
        if(threadPool.isShutdown()){
            long endTime = System.currentTimeMillis();
            log.info("main thread use {} Millis" ,endTime -startTime);
        }
        threadPool.shutdown();
    }
}
