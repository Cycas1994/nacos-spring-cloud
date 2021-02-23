package com.cycas.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-storage-service")
public interface StorageService {

    @PostMapping("/storage/decrease")
    void decrease(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);

}
