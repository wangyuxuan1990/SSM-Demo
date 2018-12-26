package com.wangyuxuan.req;

import com.wangyuxuan.request.req.BaseRequest;
import lombok.Data;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/26 11:00
 * @Description:
 */

@Data
public class RedisContentReq extends BaseRequest {

    private Integer id ;
    private String content ;
}
