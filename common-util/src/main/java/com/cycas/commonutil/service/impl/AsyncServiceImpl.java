package com.cycas.commonutil.service.impl;

import com.cycas.commonutil.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Async
public class AsyncServiceImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    public void task1() throws InterruptedException {

        long begin=System.currentTimeMillis();
        Thread.sleep(1000L);
        long end =System.currentTimeMillis();
        System.out.println("任务1耗时："+(end-begin));
    }

    @Override
    public void task2() throws InterruptedException {

        long begin=System.currentTimeMillis();
        Thread.sleep(2000L);
        long end =System.currentTimeMillis();
        System.out.println("任务2耗时："+(end-begin));
    }

    @Override
    public void task3() throws InterruptedException {

        long begin=System.currentTimeMillis();
        Thread.sleep(3000L);
        long end =System.currentTimeMillis();
        System.out.println("任务3耗时："+(end-begin));
    }

    @Override
    public Future<String> task4() throws InterruptedException {

        long begin=System.currentTimeMillis();
        Thread.sleep(3000L);
        long end =System.currentTimeMillis();
        System.out.println("任务4耗时："+(end-begin));
        return new AsyncResult<String>("任务4");
    }

    @Override
    public Future<String> task5() throws InterruptedException {

        long begin=System.currentTimeMillis();
        Thread.sleep(2000L);
        long end =System.currentTimeMillis();
        System.out.println("任务5耗时："+(end-begin));
        return new AsyncResult<String>("任务5");
    }

    @Override
    public Future<String> task6() throws InterruptedException {

        long begin=System.currentTimeMillis();
        Thread.sleep(1000L);
        long end =System.currentTimeMillis();
        System.out.println("任务6耗时："+(end-begin));
        return new AsyncResult<String>("任务6");
    }
}
