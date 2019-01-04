package com.wangyuxuan.seconds.kill.api;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 15:05
 * @Description:
 */
public interface OrderService {

    /**
     *
     * 功能描述: 创建订单
     *
     * @param: [sid] 库存ID
     * @return: int 订单ID
     * @auther: wangyuxuan
     * @date: 2019/1/4 15:26
     */
    int createWrongOrder(int sid) throws Exception;
}
