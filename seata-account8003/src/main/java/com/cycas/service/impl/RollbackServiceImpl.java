package com.cycas.service.impl;

import com.cycas.dao.AccountDao;
import com.cycas.pojo.Account;
import com.cycas.service.RollbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class RollbackServiceImpl implements RollbackService {

    private RollbackService rollbackService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AccountDao accountDao;

    @PostConstruct
    private void setRollbackService() {
        rollbackService = context.getBean(RollbackService.class);
    }

    @Override
    @Transactional
    public void save() {

        for (int i = 5; i < 10; i++) {

            System.out.println(i);
            Account account = new Account();
            account.setId(i);
            account.setUserId(i);

            try {
                rollbackService.insert(account);
            } catch (Exception e) {
                System.out.println("查询报错！");
            }

            if (i == 8) {
                throw new RuntimeException("exception 8");
            }
        }
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Override
    public void insert(Account account) {
        if (account.getId() == 7) {
            throw new RuntimeException("exception");
        }
        accountDao.insert(account);
    }
}
