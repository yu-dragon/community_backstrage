package com.yulong.manager.userController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yulong.common.utils.result.BuildResult;
import com.yulong.common.utils.constant.ResultCon;
import com.yulong.manager.entity.Answer;
import com.yulong.manager.entity.Question;
import com.yulong.manager.service.AnswerService;
import com.yulong.manager.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@RequestMapping(value = "answer")
@RestController
public class AnswerController implements ResultCon {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/list")
    public String getAnswerList(@RequestParam String id) {
        List<Answer> answerList = answerService.getAnswerList(Long.parseLong(id));
        System.out.println(answerList.toString());
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Answer answer : answerList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", answer.getId());
            jsonObject.put("content", answer.getContent());
            jsonObject.put("nickName", answer.getNickName());
            jsonObject.put("agree", answer.getAgree());
            jsonObject.put("like", answer.getLike());
            long timeAgo = new Timestamp(System.currentTimeMillis()).getTime() - answer.getComm_time().getTime();
            jsonObject.put("time", getTimeAgo(timeAgo / 1000));
            jsonArray.add(jsonObject);
        }
        Question question = questionService.getQuestion(Long.parseLong(id));
        result.put("result", jsonArray);
        result.put("qId", question.getId());
        result.put("qTitle", question.getTitle());
        result.put("qDesc", question.getDescription());
        result.put("qUserId", question.getUser_id());
        result.put("code", 100);
        result.put("message", "success");
        System.out.println(result.toString());
        return result.toString();
    }

    /**
     * 发表回答
     * @param request
     * @return
     */
    @RequestMapping(value = "/publish")
    public String postAnswer(@RequestParam String userId, @RequestParam String content,
                             @RequestParam String qId, HttpServletRequest request) {
        System.out.println(userId);
        System.out.println(content);
        Answer isAnswer = answerService.isAnsweredByUserIdAndQId(userId, qId);
        if (isAnswer != null) {
            return BuildResult.buildFailRes("该问题下已经回答了", REPEAT_ANSWER);
        }
        Integer isSuccess = answerService.postAnswer(userId, content, qId);
        if (isSuccess == 1) {
            return BuildResult.buildSuccessBase("success", SUCCESS);
        }
        return BuildResult.buildExceptionRes("系统异常，请反馈！", POST_ANSWER_EXCEPTION);
    }

    /**
     * 修改回答内容
     * @param userId
     * @param content
     * @param qId
     * @return
     */
    @RequestMapping(value = "/modify")
    public String modifyAnswer(@RequestParam String userId, @RequestParam String content,
                               @RequestParam String qId) {
        System.out.println(userId);
        System.out.println(content);
        Integer isSuccess = answerService.modifyAnswer(userId, content, qId);
        if (isSuccess == 1) {
            return BuildResult.buildSuccessBase("success", SUCCESS);
        }
        return BuildResult.buildExceptionRes("系统异常，请反馈！", MODIFY_ANSWER_EXCEPTION);
    }

    /**
     * 删除回答
     * @param cardId
     * @param answerId
     * @return
     */
    @RequestMapping(value = "/delete")
    public String deleteAnswer(@RequestParam String cardId, @RequestParam String answerId) {

        return null;
    }

    /**
     * 检查某用户是否回答了某个回答
     * @param id
     * @return
     */
    @RequestMapping(value = "/isAnswered")
    public String isAnswered(@RequestParam String id, @RequestParam String qId) {
        Answer answer = answerService.isAnsweredByUserIdAndQId(id, qId);
        if (answer != null) {
            JSONObject data = new JSONObject();
            data.put("answerId", answer.getId());
            return BuildResult.buildSuccessData("已经回答了", IS_ANSWERWD, data);
        }
        return BuildResult.buildSuccessBase("success", 100);
    }


    /**
     * 获取某个一回答详细情况
     * @param id
     * @return
     */
    @RequestMapping(value = "/contents")
    public String getSingleAnswer(@RequestParam String id) {
        Answer answer = answerService.getSingleAnswer(id);
        if (answer != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", answer.getId());
            jsonObject.put("qId", answer.getQuestion_id());
            jsonObject.put("content", answer.getContent());
            jsonObject.put("nickName", answer.getNickName());
            jsonObject.put("agree", answer.getAgree());
            jsonObject.put("like", answer.getLike());
            long timeAgo = new Timestamp(System.currentTimeMillis()).getTime() - answer.getComm_time().getTime();
            jsonObject.put("time", getTimeAgo(timeAgo / 1000));
            jsonObject.put("code", 100);
            jsonObject.put("message", "success");
            return jsonObject.toString();
        }
        return BuildResult.buildFailRes("回答可能被禁用了，请反馈！", FAIL);
    }


    /**
     * 计算发布时间距现在多久
     * @param time
     * @return
     */
    private String getTimeAgo(Long time) {
        System.out.println(time);
        if (time < 60) {
            return time + "秒前";
        }
        if (time < 60 * 60) {
            long second = time / 60;
            return second + "分钟前";
        }
        if (time < 60*60*24) {
            long hour = time / (60 * 60);
            return hour + "小时前";
        }
        if (time < 60*60*24*30) {
            long day = time / (60*60*24);
            return day + "天前";
        }
        if (time < 60*60*24*30*12) {
            long month = time / (60*60*24*30);
            return month + "月前";
        }
        long year = time / (60*60*24*30*12);
        return year + "年前";
    }
}
