package com.wangyuxuan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wangyuxuan.pojo.PageEntityNew;
import com.wangyuxuan.pojo.Rediscontent;
import com.wangyuxuan.service.RediscontentService;
import com.wangyuxuan.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/11 14:10
 * @Description:
 */

@Slf4j
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RediscontentService rediscontentService;

    @RequestMapping("/redis_list")
    public void club_list(HttpServletResponse response, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "0") int pageSize) {
        JSONObject jsonpObject = new JSONObject();
        JSONObject jo = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray();
            PageHelper.startPage(1, 10);
            PageEntityNew<Rediscontent> rediscontentPageEntityNew = rediscontentService.selectByPage(page, pageSize);
            for (Rediscontent rediscontent : rediscontentPageEntityNew.getList()){
                JSONObject jo1 = new JSONObject();
                jo1.put("rediscontent", rediscontent);
                jsonArray.add(jo1);
            }
            jo.put("redisContents", jsonArray);
            jo.put("count", rediscontentPageEntityNew.getCount());
            jsonpObject = CommonUtil.parseJson("1", "成功", jo);
        }catch (Exception e){
            jsonpObject = CommonUtil.parseJson("2", "操作异常", "");
            log.error(e.getMessage(), e);
        }
        //构建返回
        CommonUtil.responseBuildJson(response, jsonpObject);
    }
}
