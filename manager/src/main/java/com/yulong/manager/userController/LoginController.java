package com.yulong.manager.userController;

import com.alibaba.fastjson.JSONObject;
import com.yulong.common.utils.result.BuildResult;
import com.yulong.common.utils.constant.ResultCon;
import com.yulong.manager.entity.User;
import com.yulong.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController implements ResultCon {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户登录功能，保存用户的session到服务器
     * @param cardId
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(@RequestParam String cardId,
                        @RequestParam String password,
                        HttpServletRequest request) {
        JSONObject result = new JSONObject();
        if ("".equals(cardId) || "".equals(password)) {
            System.out.println("执行参数为空了");
            return BuildResult.buildFailRes("参数不能为空！", FAIL);
        }
        //社区人员，去userBase中验证是否已经登录过了，
        // 登录过了则去user表中验证信息，
        // 否则去userBase验证信息并且修改已经不是第一次登录
        System.out.println("社区人员登录处理开始------------------------");
        User user = userService.loginUser(cardId, password);
        System.out.println("社区人员登录处理成功------------------------");
        if (user != null) {
            System.out.println("社区人员登录成功");
            setSession(request,user.getCard_id(), user.getNick_name());
            System.out.println("session: " + request.getSession().getAttribute("card_id"));
            return BuildResult.buildSuccessRes(user.getId(), user.getCard_id(), user.getNick_name(), SUCCESS);
        }
        System.out.println("社区人员登录失败，用户名或者密码错误");
        return BuildResult.buildFailRes("用户名或密码错误！", FAIL);
    }

    /**
     * 用户退出功能，清除session
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/logout")
    public Object login(@RequestParam String cardId,
                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("card_id");
        session.removeAttribute("nickName");
        session.setMaxInactiveInterval(0);
        return null;
    }

    @RequestMapping(value = "/noLogin")
    public String returnResult() {
        return BuildResult.buildNoRights("未登录，请先登陆！", 98);
    }

    /**
     * 设置登录状态session
     * @param request
     * @param cardId
     */
    private void setSession(HttpServletRequest request, String cardId, String nickName) {
        HttpSession session = request.getSession();
        session.setAttribute("card_id", cardId);
        session.setAttribute("nickName", nickName);
        session.setMaxInactiveInterval(60*60);
    }
}
