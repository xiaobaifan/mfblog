package com.mofan.mfbolg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mofan.mfbolg.entity.Users;
import com.mofan.mfbolg.service.UserService;
import com.mofan.mfbolg.utils.BlogResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: mfbolg
 * @description: 登录控制类
 * @author: mofan
 * @create: 2024-10-30 21:33
 **/
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BlogResult<Users> login(@RequestBody Map<String, String> loginData, HttpSession session) {
        Users users = new Users();
        // 输入验证
        if (loginData.get("username") == null || loginData.get("username").isEmpty() || loginData.get("password") == null || loginData.get("password").isEmpty()) {
            return BlogResult.fail("Invalid username or password: Username and password cannot be empty");
        }

        users.setUsername(loginData.get("username"));
        users.setPassword(loginData.get("password"));


        users = userService.findByUsername(users);

        try {
            users = userService.findByUsername(users);
        } catch (Exception e) {
            // 记录异常日志
            return BlogResult.fail("Internal error occurred during login");
        }

        if (users!= null && users.getPassword().equals(loginData.get("password"))) {  // 假设您有一个 encryptPassword 方法对输入密码进行加密处理
            // 登录成功，将用户信息存储到 Session 中
            session.setAttribute("user", users);
            return BlogResult.ok(users);
        } else {
            return BlogResult.fail("Login failed: Invalid username or password");
        }

    }

    @PostMapping("/register")
    public BlogResult<String> register(@RequestBody Users data, HttpSession session) {
        Users user = userService.findByUsername(data);
        if (user == null){
            data.setRole(2);
            boolean status = userService.save(data);
            session.setAttribute("user", data);
            return BlogResult.ok("用户创建成功！");
        }else {
            return BlogResult.fail("该用户已存在！");
        }
    }


}
