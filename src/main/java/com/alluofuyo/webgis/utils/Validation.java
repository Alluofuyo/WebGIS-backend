package com.alluofuyo.webgis.utils;

import com.alluofuyo.webgis.model.User;

public class Validation {
    public static Boolean validateUsername(String username) {
        if (username == null) return false;
        if (username.length() > 20 || username.length() < 6) {
            return false;
        }

        return username.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$");
    }

    public static Boolean validatePassword(String password) {
        if (password == null) return false;
        return password.length() <= 20 && password.length() >= 6 && password.matches("^(?=.*[A-Za-z])(?=.*\\d)" +
                "[A-Za-z\\d]{6,20}$");
    }

    public static Boolean validateEmail(String email) {
        if (email == null) return false;
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+");
    }

    public static Boolean validateUserInfo(User user) {
        if (user == null) {
            return false;
        }
        System.out.println("用户名验证:" + validateUsername(user.getUsername()));
        System.out.println("密码验证:" + validatePassword(user.getPassword()));
        System.out.println("邮件验证:" + validateEmail(user.getEmail()));
        return validateUsername(user.getUsername()) && validatePassword(user.getPassword()) && validateEmail(user.getEmail());
    }
}
