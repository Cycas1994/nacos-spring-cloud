package com.cycas.commonutil.service.impl;

import com.cycas.commonutil.service.SyncService;
import org.springframework.stereotype.Service;

@Service
public class SyncServiceImpl implements SyncService {

    private static int num = 1;

    @Override
    public synchronized void syncTest1() throws InterruptedException {

        Thread.sleep(10L);
        num++;
    }

    @Override
    public int getNum() {
        return num;
    }
}
