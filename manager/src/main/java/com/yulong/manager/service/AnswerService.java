package com.yulong.manager.service;

import com.yulong.manager.entity.Answer;

import java.util.List;

public interface AnswerService {

    public List<Answer> getAnswerList(Long id);

    public Integer postAnswer(String userId, String content, String qId);

    public Integer modifyAnswer(String userId, String content, String qId);

    public Answer isAnsweredByUserIdAndQId(String id, String qId);

    public Answer getSingleAnswer(String id);

}
