package com.yulong.manager.controller;

import com.yulong.manager.entity.User;
import com.yulong.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public ModelAndView getUser(@RequestParam String id) {
        List<User> user = userService.getUser(Integer.parseInt(id));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/json")
    @ResponseBody
    public Object getUserJSON(@RequestParam String id) {
        User user = userService.getUser(Integer.parseInt(id)).get(0);
        return user;
    }

}
