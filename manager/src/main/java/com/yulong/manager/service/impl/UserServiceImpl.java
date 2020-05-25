package com.yulong.manager.service.impl;

import com.yulong.manager.dao.UserDao;
import com.yulong.manager.entity.AlienUser;
import com.yulong.manager.entity.User;
import com.yulong.manager.entity.UserBase;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * @author 赵玉龙
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User getUser(String cardId, String password) {
        return userDao.getUser(cardId, password);
    }

    @Override
    public List<User> checkAlienUser(String name) {
        return userDao.getUserByName(name);
    }

    /**
     * 外来人员登记
     * @param cardId
     * @param password
     * @param alienName
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User alienLoginUser(String cardId, String password, String alienName) {
        AlienUser alienUser = userDao.checkAlienIsExist(cardId, alienName);
        if (alienUser == null) {
            return null;
        }
        Integer isSuccess = userDao.insertUser(cardId, alienUser.getAlienName(), alienUser.getAlienName().substring(1), null, 1, password, new Date(System.currentTimeMillis()));
        if (isSuccess == 1) {
            return userDao.getUser(cardId, password);
        }
        return null;
    }

    @Override
    public List<UserBase> getUserBase(String cardId) {
        return null;
    }

    @Override
    public List<UserBase> loginUserBase(String cardId, String password) {
        return null;
    }

    /**
     * 人员登录
     * @param cardId
     * @param password
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User loginUser(String cardId, String password) {
        UserBase userBase = userDao.getUserBase(cardId);
        if (userBase == null) {
            User user = userDao.getUser(cardId, password);
            if (user != null) {
                return user;
            }
            return null;
        }
        if (userBase.getIs_first_login() == 1) {
            //已经登录过，直接去user表进行登录密码校验
            System.out.println("社区人员已经登录过了，直接去user表验证登录信息");
            return userDao.login(cardId, password);
        } else {
            User user = userDao.getUser(cardId, password);
            if (user == null) {
                //第一次登录，先更改userBase表中的
                Integer update = userDao.updateIsFirstLogin(userBase.getCard_id() + "", 1);
                //直接在user创建该用户，成功就相当于登录成功
                String nickName = userBase.getName().substring(1);
                Integer count = userDao.insertUser(cardId, userBase.getName(), nickName, userBase.getSex(), 1, password, new Date(System.currentTimeMillis()));
                System.out.println("社区人员user没有该用户信息且不是第一次登录，直接注册user表");
                return userDao.getUser(cardId, password);
            }
            System.out.println("社区人员已经登录过，直接验证user信息登录");
            return user;
        }
    }
}
