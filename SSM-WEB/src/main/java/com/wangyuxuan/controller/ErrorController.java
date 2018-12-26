package com.wangyuxuan.controller;

import com.wangyuxuan.enums.StatusEnum;
import com.wangyuxuan.res.BaseResponse;
import com.wangyuxuan.vo.NULLBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/26 13:10
 * @Description: 错误异常统一处理
 */

@Slf4j
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object processUnauthenticatedException(NativeWebRequest request, Exception e) {
        log.error("请求出现异常:", e);
        BaseResponse<NULLBody> response = new BaseResponse<>();
        response.setCode(StatusEnum.FAIL.getCode());
        if (e instanceof RuntimeException) {
            response.setMessage(e.getMessage());
        } else {
            response.setMessage(StatusEnum.FAIL.getMessage());
        }
        return response;
    }
}
