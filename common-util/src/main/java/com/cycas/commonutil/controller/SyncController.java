package com.cycas.commonutil.controller;

import com.cycas.commonutil.pojo.JsonData;
import com.cycas.commonutil.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SyncController {

    @Autowired
    private SyncService syncService;

    @RequestMapping("/syncExec")
    public JsonData syncExec() throws InterruptedException {

        syncService.syncTest1();
        return JsonData.buildSuccess("success");

    }

    @RequestMapping("/syncGet")
    public JsonData syncGet() throws InterruptedException {

        int num = syncService.getNum();
        return JsonData.buildSuccess(num);

    }
}
