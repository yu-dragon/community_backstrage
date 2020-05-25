package com.yulong.manager.service;

import com.yulong.manager.entity.AlienUser;
import com.yulong.manager.entity.User;

import java.sql.Date;
import java.sql.Timestamp;

public interface RegisterManageService {

    public AlienUser checkIsHasAlien(String name, String cardId);

    public Integer registerAlien(String name, String nickName, String cardId, String pwd,
                                 String phone, String age, String sex,
                                 String isAlien, Timestamp date, String health, String address);

}
