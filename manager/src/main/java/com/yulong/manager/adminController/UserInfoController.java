package com.yulong.manager.adminController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "user")
@RestController
public class UserInfoController {

    @RequestMapping(value = "/delete")
    public String deleteRegister() {

        return null;
    }

    @RequestMapping(value = "/update")
    public String updateRegister() {

        return null;
    }

    @RequestMapping(value = "/register/list")
    public String getRegisterList() {

        return null;
    }

}
