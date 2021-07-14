package com.alluofuyo.webgis.service;

import com.alluofuyo.webgis.mapper.UserMapper;
import com.alluofuyo.webgis.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public int addUser(User user_to_add) {
        User user = new User();
        user.setUsername(user_to_add.getUsername());
        user.setEmail(user_to_add.getEmail());

        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        Integer count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            System.out.println("用户已存在");
            return -1;
        }
        int insert = userMapper.insert(user_to_add);
        System.out.println("用户已添加" + insert);
        return insert;
    }

    public User findUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        User user1 = userMapper.selectOne(new QueryWrapper<>(user));
        if (user1==null) return null;
        if (!password.equals(user1.getPassword())) {
            return null;
        }
        return user1;
    }

    public boolean updateUser(User user_to_update) {
        int i = userMapper.updateById(user_to_update);
        if (i==1||i==0){
            return true;
        }else{
            return false;
        }
    }
}
