package com.power.async;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Powerveil
 * @Date 2024/1/22 11:01
 */
@Component
public class MockQueue {

    // 下单消息
    private String placeOrder; // 当这个字段有值的时候认为下单

    // 订单完成消息
    private String completeOrder;// 当这个字段有值的时候认为订单完成


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(() -> {
            logger.info("接到下单请求");
            try {
                Thread.sleep(1000);
                this.completeOrder = placeOrder;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.placeOrder = placeOrder;
            logger.info("下单请求处理完毕，" + placeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
