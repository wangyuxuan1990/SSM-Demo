package com.wangyuxuan.kafka.orderconsumer.kafka;

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

    private List<ConsumerTask> consumers;

    public ConsumerGroup(int threadNum, String groupId, String topic, String brokerList) {
        log.info("kafka parameter={},{},{},{}",threadNum,groupId,topic,brokerList);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-pool-%d").build();

        threadPool = new ThreadPoolExecutor(threadNum, threadNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        consumers = new ArrayList<>(threadNum);
        for (int i = 0; i < threadNum; i++) {
            ConsumerTask consumerThread = new ConsumerTask(brokerList, groupId, topic);
            consumers.add(consumerThread);
        }
    }

    /**
     * 执行任务
     */
    public void execute() {
        for (ConsumerTask runnable : consumers) {
            threadPool.submit(runnable);
        }
    }
}
