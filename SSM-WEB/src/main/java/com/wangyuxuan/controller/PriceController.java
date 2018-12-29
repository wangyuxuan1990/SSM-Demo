package com.wangyuxuan.controller;

import com.wangyuxuan.dao.PriceMapper;
import com.wangyuxuan.dao.PriceVersionMapper;
import com.wangyuxuan.enums.StatusEnum;
import com.wangyuxuan.pojo.Price;
import com.wangyuxuan.pojo.PriceVersion;
import com.wangyuxuan.req.RedisContentReq;
import com.wangyuxuan.res.BaseResponse;
import com.wangyuxuan.util.ThreadPoolConfig;
import com.wangyuxuan.vo.NULLBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Random;

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
    private PriceVersionMapper priceVersionMapper;

    @Autowired
    private ThreadPoolConfig config;

    /**
     * 功能描述: 线程池 无锁
     *
     * @param: [redisContentReq]
     * @return: com.wangyuxuan.res.BaseResponse<com.wangyuxuan.vo.NULLBody>
     * @auther: wangyuxuan
     * @date: 2018/12/29 10:39
     */
    @RequestMapping(value = "/threadPrice", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<NULLBody> threadPrice(@RequestBody RedisContentReq redisContentReq) {
        BaseResponse<NULLBody> response = new BaseResponse<>();
        try {
            for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Price price = priceMapper.selectByPrimaryKey(1);
                        int ron = 10;
                        price.setFront(price.getFront().subtract(new BigDecimal(ron)));
                        price.setEnd(price.getEnd().add(new BigDecimal(ron)));
                        priceMapper.updateByPrimaryKey(price);

                        price.setId(null);
                        priceMapper.insertSelective(price);
                    }
                });

                config.submit(thread);
            }
            response.setReqNo(redisContentReq.getReqNo());
            response.setCode(StatusEnum.SUCCESS.getCode());
            response.setMessage(StatusEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("system error", e);
            response.setReqNo(redisContentReq.getReqNo());
            response.setCode(StatusEnum.FAIL.getCode());
            response.setMessage(StatusEnum.FAIL.getMessage());
        }
        return response;
    }

    /**
     *
     * 功能描述: 线程池，乐观锁
     *
     * @param: [redisContentReq]
     * @return: com.wangyuxuan.res.BaseResponse<com.wangyuxuan.vo.NULLBody>
     * @auther: wangyuxuan
     * @date: 2018/12/29 15:39
     */
    @RequestMapping(value = "/threadPriceVersion", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<NULLBody> threadPriceVersion(@RequestBody RedisContentReq redisContentReq){
        BaseResponse<NULLBody> response = new BaseResponse<>();
        try {
            for (int i = 0; i < 3; i++) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PriceVersion priceVersion = priceVersionMapper.selectByPrimaryKey(1);
                        int ron = new Random().nextInt(20);
                        log.info("本次消费={}", ron);
                        priceVersion.setFront(new BigDecimal(ron));
                        int count = priceVersionMapper.updateByVersion(priceVersion);
                        if(count == 0){
                            log.error("更新失败");
                        }else {
                            log.info("更新成功");
                        }
                    }
                });

                config.submit(thread);
            }
            response.setReqNo(redisContentReq.getReqNo());
            response.setCode(StatusEnum.SUCCESS.getCode());
            response.setMessage(StatusEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("system error", e);
            response.setReqNo(redisContentReq.getReqNo());
            response.setCode(StatusEnum.FAIL.getCode());
            response.setMessage(StatusEnum.FAIL.getMessage());
        }
        return response;
    }
}
