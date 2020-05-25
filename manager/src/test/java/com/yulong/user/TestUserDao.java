package com.yulong.user;

import com.yulong.manager.dao.UserDao;
import com.yulong.manager.entity.User;
import com.yulong.manager.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-mybatis.xml")
public class TestUserDao {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {

    }

}
