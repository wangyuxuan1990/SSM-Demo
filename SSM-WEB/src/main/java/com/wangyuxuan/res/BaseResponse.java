package com.wangyuxuan.res;

import com.wangyuxuan.enums.StatusEnum;
import com.wangyuxuan.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/26 11:01
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private String code;

    private String message;

    /**
     * 请求号
     */
    private String reqNo;

    private T messageBody;

    public BaseResponse(T messageBody) {
        this.messageBody = messageBody;
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(String code, String message, T messageBody) {
        this.code = code;
        this.message = message;
        this.messageBody = messageBody;
    }

    public static <T> BaseResponse<T> create(T t){
        return new BaseResponse<T>(t);
    }

    public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum){
        return new BaseResponse<T>(statusEnum.getCode(), statusEnum.getMessage(), t);
    }

    public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum, String message){

        return new BaseResponse<T>(statusEnum.getCode(), message, t);
    }

    public static <T> BaseResponse<T> createSuccess(T t, String message){
        return new BaseResponse<T>(StatusEnum.SUCCESS.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.SUCCESS.getMessage() : message, t);
    }

    public static <T> BaseResponse<T> createFail(T t, String message){
        return new BaseResponse<T>(StatusEnum.FAIL.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.FAIL.getMessage() : message, t);
    }

}
