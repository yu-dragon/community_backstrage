package com.yulong.manager.service;

import com.yulong.manager.entity.Question;
import com.yulong.manager.entity.User;
import com.yulong.manager.entity.UserFollowed;

public interface QuestionService {

    /**
     * 发表问题
     * @param cardId
     * @param title
     * @param description
     * @return
     */
    public boolean postQuestion(String cardId, String title, String description);

    /**
     * 根据问题id获取问题信息
     * @param id
     * @return
     */
    public Question getQuestion(Long id);

    /**
     * 是否关注问题
     * @param qId
     * @param userId
     * @param status
     * @return
     */
    public Boolean isFollowedQuestion(String qId, String userId, String status);

    /**
     * 检查某用户是否关注了某问题
     * @param qId
     * @param userId
     * @return
     */
    public UserFollowed getIsFollowed(String qId, String userId);

}
