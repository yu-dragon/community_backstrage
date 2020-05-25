package com.yulong.manager.userController;

import com.yulong.common.utils.result.BuildResult;
import com.yulong.common.utils.constant.ResultCon;
import com.yulong.manager.entity.AlienUser;
import com.yulong.manager.entity.User;
import com.yulong.manager.service.RegisterManageService;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RequestMapping(value = "reg")
@RestController
public class RegisterManageController implements ResultCon {

    @Autowired
    private RegisterManageService registerManageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/commity")
    public String signCommOrAlien(@RequestParam String cardId) {

        return null;
    }

    /**
     * 外来人员登记信息
     * @return
     */
    @RequestMapping(value = "/alien")
    public String registerCommOrAlien(@RequestParam String name, @RequestParam String cardId,
                                      @RequestParam String pwd, @RequestParam String phone,
                                      @RequestParam String age, @RequestParam String sex,
                                      @RequestParam String isAlien, @RequestParam String health,
                                      @RequestParam String address, HttpServletRequest request) {
        String isLegal = checkParams(name, cardId, pwd, phone, age, address);
        if (isLegal != "true") {
            return isLegal;
        }
        Integer insert = registerManageService.registerAlien(name, name.substring(1), cardId, pwd, phone, age, sex, isAlien, new Timestamp(System.currentTimeMillis()), health, address);
        System.out.println(insert);
        if (insert == 0) {
            return BuildResult.buildFailRes("系统异常，请联系社区", REGISTER_EXCEPTION);
        }
        User user = userService.getUser(cardId, pwd);
        return BuildResult.buildSuccessRes(user.getId(), user.getCard_id(), user.getNick_name(), SUCCESS);
    }

    /**
     * 检验外来人员信息
     * @param name
     * @param cardId
     * @return
     */
    @RequestMapping(value = "/alien/exist")
    public String checkIsHasAlien(@RequestParam String name, @RequestParam String cardId) {
        AlienUser alienUser = registerManageService.checkIsHasAlien(name, cardId);
        if (alienUser == null) {
            return BuildResult.buildFailRes("请输入正确的用户名和身份证号码！", FAIL);
        }
        return BuildResult.buildSuccessRes(null, alienUser.getCard_id(), alienUser.getAlienName().substring(1), SUCCESS);
    }

    private String checkParams(String name, String cardId, String pwd, String phone, String age, String address) {
        if ("".equals(name)) {
            return BuildResult.buildFailRes("姓名不能为空", FAIL);
        }
        if ("".equals(cardId)) {
            return BuildResult.buildFailRes("身份证号码不能为空", FAIL);
        }
        if ("".equals(pwd) || pwd.length() < 6 || pwd.length() > 8) {
            return BuildResult.buildFailRes("密码不能为空且6-8位", FAIL);
        }
        if ("".equals(phone) || phone.length() != 11) {
            return BuildResult.buildFailRes("手机号码不能为空且格式合法", FAIL);
        }
        if ("".equals(age) || Integer.parseInt(age) < 0 || Integer.parseInt(age) > 120) {
            return BuildResult.buildFailRes("年龄不能为空且0-120！", FAIL);
        }
        return checkAddress(address);
    }

    private String checkAddress(String address) {
        //暂时先不写地址校验
        String[] addressName = address.split("-");
        for (String name : addressName) {
            System.out.println(name);
        }
        return "true";
    }
}
