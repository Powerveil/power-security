package com.power.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * @author Powerveil
 * @Date 2024/1/22 10:41
 */
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        logger.info("主线程开始");
        // 这种方式副线程必须由主线程调起 但是无法处理下面的请求
        //
        // 1.http请求      应用1
        //                线程1    2.发消息
        //                                               3.监听并处理消息
        //                                     消息队列                      应用2
        //                                               4.监听并处理消息
        //                线程2    5.监听处理结果
        // 6.http响应
//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                logger.info("副线程开始");
//                Thread.sleep(1000);
//                logger.info("副线程结束");
//                return "success";
//            }
//        };


        // 第二种方式
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();

        deferredResultHolder.getMap().put(orderNumber, result);

        logger.info("主线程结束");
        return result;
    }
}
