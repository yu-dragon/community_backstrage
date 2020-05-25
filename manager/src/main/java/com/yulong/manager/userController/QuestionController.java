package com.yulong.manager.userController;

import com.alibaba.fastjson.JSONObject;
import com.yulong.common.utils.result.BuildResult;
import com.yulong.common.utils.constant.ResultCon;
import com.yulong.manager.entity.UserFollowed;
import com.yulong.manager.service.QuestionService;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "question")
@RestController
public class QuestionController implements ResultCon {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    /**
     * 发表问题
     * @param cardId
     * @param title
     * @param description
     * @return
     */
    @RequestMapping(value = "/publish")
    public String postQuestion(@RequestParam String cardId,
                               @RequestParam String title,
                               @RequestParam String description,
                               HttpServletRequest request) {
        System.out.println("执行了问题提交请求。。。");
        JSONObject result = new JSONObject();
        if ("".equals(cardId) || "".equals(title) || "<p><br></p>".equals(description)) {
            return BuildResult.buildFailRes("输入参数不能为空！", FAIL);
        }
        boolean isSuccess = questionService.postQuestion(cardId, title, description);
        System.out.println("post is " + isSuccess);
        if (isSuccess) {
            return BuildResult.buildSuccessBase("发表成功！", SUCCESS);
        } else {
            return BuildResult.buildFailRes("发表失败，系统异常请联系社区", POST_QUESTION_exception);
        }
    }

    /**
     * 修改问题
     * @param cardId
     * @param request
     * @return
     */
    @RequestMapping(value = "/modify")
    public String modifyQuestion(@RequestParam String cardId, HttpServletRequest request) {

        return null;
    }

    /**
     * 删除问题
     * @param cardId
     * @param questionId
     * @return
     */
    @RequestMapping(value = "/delete")
    public String deleteQuestion(@RequestParam String cardId, @RequestParam String questionId) {

        return null;
    }

    /**
     * 用户关注问题
     * @param status
     * @param qId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/isFollowed")
    public String isFollowedQue(@RequestParam String status, @RequestParam String qId,
                                @RequestParam String userId) {
        Boolean isFollowed = questionService.isFollowedQuestion(qId, userId, status);
        if (!isFollowed) {
            return BuildResult.buildFailRes("系统异常，请反馈！", FOLLOWED_EXCEPTION);
        }
        return BuildResult.buildSuccessBase("success", 100);
    }

    /**
     * 检查用户是否已经关注了某问题
     * @param qId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getIsFollowed")
    public String getIsFollowed(@RequestParam String qId, @RequestParam String userId) {
        UserFollowed isFollowed = questionService.getIsFollowed(qId, userId);
        if (isFollowed == null) {
            return BuildResult.buildFailRes("还未关注该问题！", CHECK_FOLLOWED_EXCEPTION);
        }
        return BuildResult.buildSuccessBase("success", 100);
    }

}
