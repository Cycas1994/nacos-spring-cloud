package com.cycas.commonutil.controller;

import com.cycas.commonutil.pojo.JsonData;
import com.cycas.commonutil.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/asyncTest")
    public JsonData exeTask() throws InterruptedException {

        long begin = System.currentTimeMillis();
        asyncService.task1();
        asyncService.task2();
        asyncService.task3();
//        Future<String> task4 = asyncService.task4();
////        Future<String> task5 = asyncService.task5();
////        Future<String> task6 = asyncService.task6();
////        for (; ; ) {
////            if (task4.isDone() && task5.isDone() && task6.isDone()) {
////                break;
////            }
////        }

        long end = System.currentTimeMillis();
        long total = end - begin;
        System.out.println("执行总耗时" + total);
        return JsonData.buildSuccess(total);
    }
}
