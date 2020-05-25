package com.yulong.manager.userController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yulong.common.utils.constant.ResultCon;
import com.yulong.manager.entity.Announcement;
import com.yulong.manager.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/announ")
@RestController
public class AnnouncementController implements ResultCon {

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping(value = "/obtain")
    public String getAnncouncement() {
        List<Announcement> announcement = announcementService.getAnnouncement();
        System.out.println(announcement);
        JSONArray jsonArray = new JSONArray();
        for (Announcement ann : announcement) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", ann.getId());
            jsonObject.put("content", ann.getContent());
            jsonObject.put("post_time", ann.getPost_time());
            jsonObject.put("type", ann.getType());
            jsonArray.add(jsonObject);
        }
        JSONObject result = new JSONObject();
        result.put("result", jsonArray);
        result.put("code", 100);
        result.put("message", "success");
        return result.toString();
    }

}
