package com.wangyuxuan.pojo;

import java.io.Serializable;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/11/28 15:10
 * @Description:
 */
public class PageEntity implements Serializable {

    /**
     * 第几页
     */
    private int page ;

    /**
     * 起始页
     */
    private int start ;

    /**
     * 每页多少条记录
     */
    private int pageSize ;

    public PageEntity(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStart() {
        return (page-1)*pageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
