package com.wangyuxuan.seconds.kill.controller;

import com.wangyuxuan.seconds.kill.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: wangyuxuan
 * @Date: 2019/1/4 13:49
 * @Description:
 */

@Slf4j
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/health")
    @ResponseBody
    public String health() {
        log.info("health");
        return "OK";
    }

    @RequestMapping("/createWrongOrder/{sid}")
    @ResponseBody
    public String createWrongOrder(@PathVariable int sid) {
        log.info("sid = [{}]", sid);
        int id = 0;
        try {
            id = orderService.createWrongOrder(sid);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return String.valueOf(id);
    }
}
