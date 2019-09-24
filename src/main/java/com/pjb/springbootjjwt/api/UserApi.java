package com.pjb.springbootjjwt.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pjb.springbootjjwt.annotation.UserLoginToken;
import com.pjb.springbootjjwt.model.User;
import com.pjb.springbootjjwt.service.TokenService;
import com.pjb.springbootjjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinbin
 * @date 2018-07-08 20:45
 */
@RestController
@RequestMapping("/api")
public class UserApi {
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    //登录
    @PostMapping("/login")
    public ResponseEntity<?> login(User user) {
        System.out.println(user.toString());

        User user2 = userService.findByUsername(user);

        HashMap<String, Object> map = new HashMap<>();

        if (user2 == null) {
            map.put("message", "登录失败,用户不存在");
            return ResponseEntity.ok(map);
        } else {
            if (!user2.getPassword().equals(user.getPassword())) {
                map.put("message", "登录失败,密码错误");
                return ResponseEntity.ok(map);
            } else {
                String token = tokenService.getToken(user2);
                map.put("token", token);
                map.put("user", user2);

                return ResponseEntity.ok(map);
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }
}
