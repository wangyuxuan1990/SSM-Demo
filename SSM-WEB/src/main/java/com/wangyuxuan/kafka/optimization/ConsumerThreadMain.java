package com.wangyuxuan.kafka.optimization;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/3 15:17
 * @Description:
 */
public class ConsumerThreadMain {

    private static String brokerList = "localhost:9092";
    private static String groupId = "group1";
    private static String topic = "test";
    /**
     * 线程数量
     */
    private static int threadNum = 3;

    public static void main(String[] args) {
        ConsumerGroup consumerGroup = new ConsumerGroup(threadNum, groupId, topic, brokerList);
        consumerGroup.execute();
    }
}
