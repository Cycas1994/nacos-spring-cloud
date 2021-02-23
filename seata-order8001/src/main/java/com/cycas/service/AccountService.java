package com.cycas.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-account-service")
public interface AccountService {

    @PostMapping("/account/decrease")
    void decrease(@RequestParam("userId") String userId, @RequestParam("money") Integer money);

}
