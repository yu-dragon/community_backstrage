package com.yulong.manager.service.impl;

import com.yulong.manager.dao.AnswerDao;
import com.yulong.manager.dao.UserDao;
import com.yulong.manager.entity.Answer;
import com.yulong.manager.entity.User;
import com.yulong.manager.service.AnswerService;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AnswerDao answerDao;

    /**
     * 回去回答列表
     * @param id
     * @return
     */
    @Override
    public List<Answer> getAnswerList(Long id) {
        List<Answer> answerList = answerDao.getAnswerList(id);
        for (Answer answer : answerList) {
            User user = userDao.getUserById(answer.getUser_id());
            answer.setNickName(user.getNick_name());
        }
        return answerList;
    }

    /**
     * 发表回答
     * @param userId
     * @param content
     * @param qId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer postAnswer(String userId, String content, String qId) {
        Integer answer = answerDao.postAnswer(qId, userId, content, new Timestamp(System.currentTimeMillis()));
        Integer answerNum = answerDao.updateQuestionAnswerNum(userId, qId);
        if (answer != null && answerNum != null) {
            return answer;
        }
        return null;
    }

    /**
     * 修改回答内容
     * @param userId
     * @param content
     * @param qId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer modifyAnswer(String userId, String content, String qId) {
        return answerDao.modifyAnswer(qId, userId, content, new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public Answer isAnsweredByUserIdAndQId(String id, String qId) {
        return answerDao.isAnsweredByUserId(id, qId);
    }

    @Override
    public Answer getSingleAnswer(String id) {
        Answer ans = answerDao.getSingleAnswer(id);
        if (ans != null) {
            User user = userDao.getUserById(ans.getUser_id());
            ans.setNickName(user.getNick_name());
            return ans;
        }
        return null;
    }
}
