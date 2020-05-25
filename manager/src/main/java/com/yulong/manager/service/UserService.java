package com.yulong.manager.service;

import com.yulong.manager.entity.User;
import com.yulong.manager.entity.UserBase;

import java.util.List;

/**
 * @author 赵玉龙
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param cardId 身份证id
     * @param password 密码
     * @return 用户信息
     */
    public User getUser(String cardId, String password);

    public List<User> checkAlienUser(String name);

    public User alienLoginUser(String cardId, String alienName, String password);

    public List<UserBase> getUserBase(String cardId);

    public List<UserBase> loginUserBase(String cardId, String password);
    
    public User loginUser(String cardId, String password);

}
