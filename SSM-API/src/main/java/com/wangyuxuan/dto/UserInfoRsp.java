package com.wangyuxuan.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/20 10:08
 * @Description:
 */

@Data
public class UserInfoRsp implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Integer roleid ;
}
