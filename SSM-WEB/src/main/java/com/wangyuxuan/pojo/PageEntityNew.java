package com.wangyuxuan.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/11 13:38
 * @Description: 分页实体
 */
@Getter
@Setter
public class PageEntityNew<T> implements Serializable {

    private List<T> list; // 分页后的数据
    private Integer count;
}
