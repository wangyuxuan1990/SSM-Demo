package com.wangyuxuan.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/26 11:11
 * @Description: 返回结果状态。
 */
public enum StatusEnum {

    /**
     * 成功
     */
    SUCCESS("9000", "成功"),

    /**
     * 失败
     */
    FAIL("4000", "失败"),

    /**
     * 重复请求
     */
    REPEAT_REQUEST("5000", "重复请求"),
    ;

    /**
     * 枚举值码
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构建一个 StatusEnum 。
     *
     * @param code    枚举值码。
     * @param message 枚举描述。
     */
    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 得到枚举值码。
     *
     * @return 枚举值码。
     */
    public String getCode() {
        return code;
    }

    /**
     * 得到枚举描述。
     *
     * @return 枚举描述。
     */
    public String getMessage() {
        return message;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 StatusEnum 。
     */
    public static StatusEnum findStatus(String code) {
        for (StatusEnum statusEnum : values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("ResultInfo StatusEnum not legal:" + code);
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<StatusEnum> getAllStatus() {
        List<StatusEnum> list = new ArrayList<>();
        for (StatusEnum statusEnum : values()) {
            list.add(statusEnum);
        }
        return list;
    }

    /**
     * 获取全部枚举值码。
     *
     * @return 全部枚举值码。
     */
    public static List<String> getAllStatusCode() {
        List<String> list = new ArrayList<>();
        for (StatusEnum statusEnum : values()) {
            list.add(statusEnum.getCode());
        }
        return list;
    }
}
