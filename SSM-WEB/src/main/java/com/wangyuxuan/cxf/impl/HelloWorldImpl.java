package com.wangyuxuan.cxf.impl;

import com.wangyuxuan.cxf.HelloWorld;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/6 19:24
 * @Description:
 */

@Component("helloWorld")
@WebService
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String say(String str) {
        return "Hello" + str;
    }
}
