package com.wangyuxuan.service;

import com.wangyuxuan.pojo.Content;

import java.util.List;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/7 10:35
 * @Description:
 */
public interface ContentService {

    /**
     * 根据条件新增
     * @param content
     * @return
     */
    int insertSelective(Content content);

    /**
     * 获取内容list
     * @return
     */
    List<Content> findContentList();
}
