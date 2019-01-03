package com.wangyuxuan.kafka.optimization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/3 15:31
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
public class ConsumerFuture {

    private Integer totalCount;
    private Long TotalTime;
}
