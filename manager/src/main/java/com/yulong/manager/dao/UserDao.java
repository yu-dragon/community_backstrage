package com.yulong.manager.dao;

import com.yulong.manager.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public List<User> getUser(Integer id);
}
