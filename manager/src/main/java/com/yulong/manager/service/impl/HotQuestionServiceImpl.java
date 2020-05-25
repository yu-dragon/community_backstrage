package com.yulong.manager.service.impl;

import com.yulong.manager.dao.QuestionDao;
import com.yulong.manager.dao.UserDao;
import com.yulong.manager.entity.Question;
import com.yulong.manager.entity.User;
import com.yulong.manager.service.HotQuestionService;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotQuestionServiceImpl implements HotQuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Question> getHotQuesList() {
        return questionDao.getHotQuesList();
    }
}
