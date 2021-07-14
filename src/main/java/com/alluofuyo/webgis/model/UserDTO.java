package com.alluofuyo.webgis.model;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

@Data
public class UserDTO {
    Integer id;
    String username;
    String nickname;
    Integer gender;
    String email;
    String birthday;

    public UserDTO(User user) {
        this.id = user.getId();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.birthday = dateFormat.format(user.getBirthday());
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.nickname = user.getNickname();
    }
}
