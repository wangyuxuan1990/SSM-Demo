package com.wangyuxuan.vo;

import lombok.NoArgsConstructor;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/26 11:30
 * @Description: 空对象,用在泛型中,表示没有额外的请求参数或者返回参数
 */

@NoArgsConstructor
public class NULLBody {

    public static NULLBody creat(){
        return new NULLBody();
    }
}
