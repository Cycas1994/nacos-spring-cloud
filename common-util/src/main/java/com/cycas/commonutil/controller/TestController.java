package com.cycas.commonutil.controller;

import com.cycas.commonutil.pojo.JsonData;
import com.cycas.commonutil.service.AccountService;
import com.cycas.commonutil.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/test")
    public JsonData test() {

        accountService.test();
        return JsonData.buildSuccess("success");

    }
}
