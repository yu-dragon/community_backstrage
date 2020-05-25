package com.yulong.manager.userController;

import com.alibaba.fastjson.JSONObject;
import com.yulong.common.utils.qrcode.ProductQRCode;
import com.yulong.manager.dao.UserDao;
import com.yulong.manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@RequestMapping(value = "user")
@RestController
public class UserController {

    private final String IMAGE_PATH = "E:/code/GraduationProject/community_backstrage/manager/src/main/webapp/images/";

    private final String BASE_URL = "http://localhost:8689/images/";

    private final String WEIXIN_APPID_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/healthCode")
    public String productHealthCode(@RequestParam String nickName, @RequestParam String cardId, HttpServletRequest request) {
        String fileName = nickName + "-health-" + cardId + ".png";
        String content;
        User user = userDao.getUserByCardId(cardId);
        Integer health_status = user.getHealth_status();
        Color color;
        if (health_status == 1) {
            color = new Color(0,255,0);
            content = nickName + "健康";
        } else {
            color = new Color(255,0,0);
            content = nickName + "不健康";
        }
        //上传到weapp目录下
        ProductQRCode.encode(fileName, content, 300, 300, IMAGE_PATH, color);
        //上传到系统发布目录下
        String codeDeployPath = request.getSession().getServletContext().getRealPath("/") + "images\\";
        System.out.println(codeDeployPath);
        String codeFileName = ProductQRCode.encode(fileName, content, 300, 300, codeDeployPath, color);
        System.out.println(BASE_URL + codeFileName);
        String codePath = BASE_URL + codeFileName;
        JSONObject result = new JSONObject();
        result.put("code", 100);
        result.put("message", "success");
        result.put("path", codePath);
        result.put("name", user.getName());
        return result.toString();
    }


    @RequestMapping(value = "/sessionKey")
    public String parseUserSessionKey(@RequestParam String code) {

        return null;
    }

}
