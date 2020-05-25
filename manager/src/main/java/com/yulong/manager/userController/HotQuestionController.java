package com.yulong.manager.userController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yulong.manager.entity.Question;
import com.yulong.manager.service.HotQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "hotQuestion")
@RestController
public class HotQuestionController {

    @Autowired
    private HotQuestionService hotQuestionService;

    @RequestMapping(value = "/list")
    public String getHotQuestionsList() {
        List<Question> hotQuesList = hotQuestionService.getHotQuesList();
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        if (hotQuesList.size() > 0) {
            for (Question que : hotQuesList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", que.getId());
                jsonObject.put("hot", que.getHot());
                jsonObject.put("title", que.getTitle());
                jsonObject.put("followed", que.getFollowed());
                jsonObject.put("nums", que.getAnswer_num());
                jsonArray.add(jsonObject);
            }
        }
        result.put("result", jsonArray);
        result.put("code", 100);
        result.put("message", "success");
        return result.toString();
    }

}
