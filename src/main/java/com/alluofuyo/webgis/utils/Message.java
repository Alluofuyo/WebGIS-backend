package com.alluofuyo.webgis.utils;


import lombok.Data;

import java.util.HashMap;

@Data
public class Message {
    int status;
    String msg;
    HashMap<String, Object> data=new HashMap<>();

    public Message success(String msg) {
        this.status = 0;
        this.msg = msg;
        return this;
    }

    public Message error(String msg) {
        this.msg = msg;
        this.status = -1;
        return this;
    }
}
