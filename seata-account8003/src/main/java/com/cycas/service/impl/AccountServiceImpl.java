package com.cycas.service.impl;

import com.cycas.dao.AccountDao;
import com.cycas.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, Integer money) {

        logger.info("--->storage-account扣减账户开始");
        accountDao.decrease(userId, money);
        logger.info("--->storage-account扣减账户结束");

    }
}
