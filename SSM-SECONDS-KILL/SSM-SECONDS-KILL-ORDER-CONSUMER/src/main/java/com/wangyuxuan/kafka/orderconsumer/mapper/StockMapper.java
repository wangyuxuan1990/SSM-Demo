package com.wangyuxuan.kafka.orderconsumer.mapper;

import com.wangyuxuan.kafka.orderconsumer.pojo.Stock;
import com.wangyuxuan.kafka.orderconsumer.pojo.StockExample;
import com.wangyuxuan.kafka.orderconsumer.util.IOPMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockMapper extends IOPMapper<Stock> {
    int countByExample(StockExample example);

    int deleteByExample(StockExample example);

    List<Stock> selectByExample(StockExample example);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByOptimistic(Stock record);
}