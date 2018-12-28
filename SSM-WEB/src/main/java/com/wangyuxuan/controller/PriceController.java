package com.wangyuxuan.controller;

import com.wangyuxuan.dao.PriceMapper;
import com.wangyuxuan.util.ThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/28 17:07
 * @Description:
 */

@Slf4j
@Controller
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private ThreadPoolConfig config;
}
