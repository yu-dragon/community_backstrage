package com.yulong.manager.service.impl;

import com.yulong.manager.dao.UserDao;
import com.yulong.manager.entity.User;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUser(Integer id) {
        return userDao.getUser(id);
    }
}
