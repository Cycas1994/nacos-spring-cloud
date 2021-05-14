package com.cycas.shardinginterceptor.controller;

import com.cycas.shardinginterceptor.entity.User;
import com.cycas.shardinginterceptor.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public User query(Long id, String monthStr) {
        return userService.selectByPrimaryKey(id, monthStr);
    }

    @GetMapping("/queryByCompanyId")
    public User queryByCompanyId(Long id, String companyId) {
        return userService.queryByCompanyId(id, companyId);
    }

    @GetMapping("/queryUsersByCondition")
    public PageInfo<User> queryUsersByCondition(String name, String companyId, int pageNum, int pageSize) {
        return userService.queryUsersByCondition(name, companyId, pageNum, pageSize);
    }

    @GetMapping("/save")
    public Integer save() {
        userService.saveUser(1L);
        return 1;
    }

    public static void main(String[] args) {

        System.out.println(getDigit());
    }

    public static boolean getDigit() {

        try {
            int i = 1/0;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
