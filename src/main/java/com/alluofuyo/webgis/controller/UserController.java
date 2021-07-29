package com.alluofuyo.webgis.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alluofuyo.webgis.model.User;
import com.alluofuyo.webgis.model.UserDTO;
import com.alluofuyo.webgis.service.UserService;
import com.alluofuyo.webgis.utils.Message;
import com.alluofuyo.webgis.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    @ResponseBody
    public Message login(@RequestParam String username, @RequestParam String password) {
        System.out.println("Receive Request:" + username + "," + password);
        if (!(Validation.validateUsername(username) && Validation.validatePassword(password))) {
            Message message = new Message();
            message.error("账号或密码不正确");
            return message;
        }
        if (StpUtil.isLogin()) {
            Message message = new Message();
            message.success("已登录");
            return message;
        } else {
            User user = userService.findUser(username, password);
            if (user == null) {
                Message message = new Message();
                message.error("账号或密码不正确");
                return message;
            } else {
                StpUtil.login(user.getId());
                Message message = new Message();
                message.getData().put("user", new UserDTO(user));
                message.getData().put("token", StpUtil.getTokenValue());
                message.success("登录成功");
                return message;
            }
        }
    }


    @PostMapping("/regist")
    @ResponseBody
    public Message regist(@RequestBody User user_to_regist) {
        System.out.println("Receive Request:" + user_to_regist);
        if (Validation.validateUserInfo(user_to_regist)) {
            if (userService.addUser(user_to_regist) == -1) {
                Message message = new Message();
                message.error("用户已存在");
                return message;
            } else {
                UserDTO userDTO = new UserDTO(user_to_regist);
                Message message = new Message();
                message.success("注册成功");
                return message;
            }
        } else {
            Message message = new Message();
            message.error("注册信息有误");
            return message;
        }
    }

    @PostMapping("/updateUserInfo")
    @ResponseBody
    public Message updateUserInfo(@RequestBody User user_to_update) {
        System.out.println("Receive Request:" + user_to_update);
        if (userService.updateUser(user_to_update)) {
            Message message = new Message();
            UserDTO userDTO = new UserDTO(user_to_update);
            message.getData().put("user", userDTO);
            message.success("修改成功");
            return message;
        } else {
            Message message = new Message();
            message.error("修改失败");
            return message;
        }
    }

    @GetMapping("/getUserInfo")
    @ResponseBody
    public Message getUserInfo() {
        long id = StpUtil.getLoginIdAsLong();
        User user = userService.getUserById(id);
        Message message = new Message();
        if (user != null) {
            UserDTO userDTO = new UserDTO(user);
            HashMap<String, Object> map = new HashMap<>();
            map.put("user", userDTO);
            message.setData(map);
            message.success("获取成功");
        } else {
            message.error("获取失败");
        }
        return message;
    }

    @PostMapping("/uploadAvatar")
    @ResponseBody
    public void uploadAvatar(@RequestBody byte[] data){
        userService.uploadUserAvatar(data);
    }

    @GetMapping("/getAvatar/{username}")
    @ResponseBody
    public void getUserAvatar(@PathVariable String username,HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] userAvatar = userService.getUserAvatar(username);
        if (userAvatar!=null){
            response.setContentType("image/png;charset=utf-8");
            try (OutputStream stream = response.getOutputStream()) {
                stream.write(userAvatar);
                stream.flush();
            }
            response.addHeader("Access-Control-Allow-Origin","*");
        }
    }
}
