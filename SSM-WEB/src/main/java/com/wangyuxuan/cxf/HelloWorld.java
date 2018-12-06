package com.wangyuxuan.cxf;

import javax.jws.WebService;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/6 19:18
 * @Description:
 */

@WebService
public interface HelloWorld {

    public String say(String str);
}
