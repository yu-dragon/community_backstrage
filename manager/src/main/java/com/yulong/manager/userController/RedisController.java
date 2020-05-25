package com.yulong.manager.userController;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/test/redis")
    public String testRedis() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        JSONArray resut = new JSONArray();
        int i = 3;
        while (i > 0) {
            valueOperations.set("redis-" + i, "redis-" + i);
            i--;
        }
        Object o = redisTemplate.opsForValue().get("redis-1");
        resut.add(o.toString());
        System.out.println(o.toString());
        return resut.toString();
    }

}
