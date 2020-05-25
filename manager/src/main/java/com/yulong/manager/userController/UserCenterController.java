package com.yulong.manager.userController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "userCenter")
@RestController
public class UserCenterController {

    /**
     * 获取用户数据中心所需数据
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/data")
    public String userDataShow(@RequestParam String cardId) {

        return null;
    }

    /**
     * 获取用户关注列表
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/followed/list")
    public String getUserFollowedList(@RequestParam String cardId) {

        return null;
    }

    /**
     * 获取用户回答列表
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/answer/list")
    public String getUserAnswerList(@RequestParam String cardId) {

        return null;
    }

    /**
     * 获取用户收藏列表
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/collects/list")
    public String getUserCollectsList(@RequestParam String cardId) {

        return null;
    }

    /**
     * 获取用户的健康状态
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/health/status")
    public String getUserHealthStatus(@RequestParam String cardId) {

        return null;
    }

    /**
     * 用户系统反馈信息列表
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/feedback/list")
    public String userFeedbackList(@RequestParam String cardId) {

        return null;
    }

    /**
     * 用户系统反馈信息
     * @param cardId
     * @param request
     * @return
     */
    @RequestMapping(value = "/feedback")
    public String userFeedback(@RequestParam String cardId, HttpServletRequest request) {

        return null;
    }

    /**
     * 用户举报信息列表
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/report/list")
    public String userReportList(@RequestParam String cardId) {

        return null;
    }

    /**
     * 用户举报信息
     * @param cardId
     * @param request
     * @return
     */
    @RequestMapping(value = "/report")
    public String userReportUp(@RequestParam String cardId, HttpServletRequest request) {

        return null;
    }

    /**
     * 用户修改密码
     * @param cardId
     * @param originalPwd
     * @param newPwd
     * @param confirmPwd
     * @return
     */
    @RequestMapping(value = "/modify/pwd")
    public String modifyPassWord(@RequestParam String cardId,
                                 @RequestParam String originalPwd,
                                 @RequestParam String newPwd,
                                 @RequestParam String confirmPwd) {

        return null;
    }

}
