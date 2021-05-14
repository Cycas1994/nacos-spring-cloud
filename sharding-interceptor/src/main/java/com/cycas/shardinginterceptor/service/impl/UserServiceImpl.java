package com.cycas.shardinginterceptor.service.impl;

import com.cycas.shardinginterceptor.dao.UserDao;
import com.cycas.shardinginterceptor.entity.User;
import com.cycas.shardinginterceptor.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
//    @Transactional
    public int saveUser(Long i) {
        User user = new User();
        user.setId(i);
        user.setName("name" + i);
        user.setNote("note" + i);
        userMapper.insert(user);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Long id, String monthStr) {
        return userMapper.selectByPrimaryKey(id, monthStr);
    }

    @Override
    public User queryByCompanyId(Long id, String companyId) {
        return userMapper.queryByCompanyId(id, companyId);
    }

    @Override
    public PageInfo<User> queryUsersByCondition(String name, String companyId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.queryUsersByCondition(name, companyId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void select() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.addAll(userMapper.selectAll());
        }
        list.forEach(user -> {
            user.setName("cycas");
            System.out.println(user.getId() + "-" + user.getName() + "-" + user.getNote());
        });
    }

}
