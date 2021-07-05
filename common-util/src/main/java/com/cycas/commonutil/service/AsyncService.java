package com.cycas.commonutil.service;

import java.util.concurrent.Future;

public interface AsyncService {

    void task1() throws InterruptedException;

    void task2() throws InterruptedException;

    void task3() throws InterruptedException;

    Future<String> task4() throws InterruptedException;

    Future<String> task5() throws InterruptedException;

    Future<String> task6() throws InterruptedException;
}
