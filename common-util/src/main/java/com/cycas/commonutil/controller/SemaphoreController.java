package com.cycas.commonutil.controller;

import com.cycas.commonutil.pojo.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("/semaphore")
public class SemaphoreController {

    Logger logger = LoggerFactory.getLogger(SemaphoreController.class);

    Semaphore semaphore = new Semaphore(1);

    @RequestMapping("/request")
    public JsonData request() {
        int availablePermits = semaphore.availablePermits();
        if (availablePermits > 0) {
            logger.info("抢到资源");
        } else {
            logger.info("资源已被占用，稍后再试");
        }
        try {
            semaphore.acquire(1);
            logger.info("资源正在被使用");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(1);
        }
        return JsonData.buildSuccess("success");
    }
}
