package com.maomi.coder.controller;

import com.alibaba.fastjson.JSONObject;
import com.maomi.coder.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lucky on 2018/6/26.
 */
@RestController
public class WebController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/query")
    public @ResponseBody User queryUser(@RequestParam("id") int id){
        String user = redisTemplate.opsForValue().get(String.valueOf(id));
        User User = JSONObject.parseObject(user, User.class);
        return User;
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertUser(@RequestBody User user){
        redisTemplate.opsForValue().set(String.valueOf(user.getId()),JSONObject.toJSONString(user));
        return "success";
    }
}
