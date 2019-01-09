package com.wangyuxuan.kafka.orderconsumer.util;

import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/8 20:49
 * @Description: 继承自己的MyMapper
 */
public interface IOPMapper<T> extends Mapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
