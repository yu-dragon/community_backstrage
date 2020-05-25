package com.yulong.manager.service.impl;

import com.yulong.manager.dao.RegisterManageDao;
import com.yulong.manager.dao.UserDao;
import com.yulong.manager.entity.AlienUser;
import com.yulong.manager.entity.User;
import com.yulong.manager.service.RegisterManageService;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RegisterManageServiceImpl implements RegisterManageService {

    @Autowired
    private RegisterManageDao registerManageDao;

    /**
     * 检查是外来人员是否正常，即数据库中是否有
     * 该人员基本信息
     * @param name
     * @param cardId
     * @return
     */
    @Override
    public AlienUser checkIsHasAlien(String name, String cardId) {
        return registerManageDao.checkIsHasAlien(cardId, name);
    }

    /**
     * 外来人员信息登记
     * @param name
     * @param nickName
     * @param cardId
     * @param pwd
     * @param phone
     * @param age
     * @param sex
     * @param isAlien
     * @param date
     * @param health
     * @param address
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer registerAlien(String name, String nickName, String cardId, String pwd, String phone, String age, String sex, String isAlien, Timestamp date, String health, String address) {
        return registerManageDao.registerAlien(name, nickName, cardId, pwd, phone, age, sex, isAlien, date, health, address);
    }
}
