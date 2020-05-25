package com.yulong.manager.service.impl;

import com.yulong.manager.dao.QuestionDao;
import com.yulong.manager.dao.UserDao;
import com.yulong.manager.entity.Question;
import com.yulong.manager.entity.User;
import com.yulong.manager.entity.UserFollowed;
import com.yulong.manager.service.QuestionService;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private QuestionDao questionDao;

    /**
     * 发表问题
     * @param cardId
     * @param title
     * @param description
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean postQuestion(String cardId, String title, String description) {
        User user = userDao.getUserByCardId(cardId);
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setComm_time(new Timestamp(System.currentTimeMillis()));
        question.setUser_id(user.getId());
        Integer count = questionDao.postQuestion(question);
        if (count == 1) {
            return true;
        }
        return false;
    }

    /**
     * 根据问题id获取问题信息
     * @param id
     * @return
     */
    @Override
    public Question getQuestion(Long id) {
        return questionDao.getQuestionById(id);
    }

    /**
     * 关注与否
     * @param qId
     * @param userId
     * @param status
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean isFollowedQuestion(String qId, String userId, String status) {
        Question question = questionDao.getQuestionByQidandUserId(qId, userId);
        //status为0时说明要取消关注，即关注数减一
        if ("0".equals(status)) {
            System.out.println(status);
            Integer update = questionDao.updateFollowed(qId, -1);
        } else {
            System.out.println(status);
            Integer update = questionDao.updateFollowed(qId, 1);
        }
        if (question == null) {
            Integer userFollowed = questionDao.insertUserFollowed(userId, qId, status, new Timestamp(System.currentTimeMillis()));
            if (userFollowed == 1) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else {
            Integer userFollowed = questionDao.updateUserFollowed(userId, qId, status, new Timestamp(System.currentTimeMillis()));
            if (userFollowed == 1) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    /**
     * 检查某用户是否关注了某问题
     * @param qId
     * @param userId
     * @return
     */
    @Override
    public UserFollowed getIsFollowed(String qId, String userId) {
        return questionDao.getIsFollowed(qId, userId);
    }
}
