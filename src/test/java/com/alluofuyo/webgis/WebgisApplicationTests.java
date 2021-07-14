package com.alluofuyo.webgis;

import com.alluofuyo.webgis.mapper.PositionMapper;
import com.alluofuyo.webgis.mapper.SharePointMapper;
import com.alluofuyo.webgis.mapper.TagMapper;
import com.alluofuyo.webgis.mapper.UserMapper;
import com.alluofuyo.webgis.model.Position;
import com.alluofuyo.webgis.model.SharePoint;
import com.alluofuyo.webgis.model.Tag;
import com.alluofuyo.webgis.model.User;
import com.alluofuyo.webgis.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
class WebgisApplicationTests {
    @Resource
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Resource
    SharePointMapper sharePointMapper;
    @Resource
    PositionMapper positionMapper;
    @Resource
    TagMapper tagMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void testInsert(){
        User user = new User();
        user.setUsername("test04");
        user.setBirthday(new Date());
        user.setGender(0);
        user.setNickname("test02");
        user.setPassword("test03");
        userService.addUser(user);
    }
    @Test
    void testSelectSharePoint(){
        SharePoint sharePoint = sharePointMapper.selectById(1);
        Position position = positionMapper.selectById(sharePoint.getPositionid());
        sharePoint.setPosition(position);
        HashMap<String, Object> map = new HashMap<>();
        map.put("shareid",sharePoint.getId());
        List<Tag> tags = tagMapper.selectByMap(map);
        sharePoint.setTags(tags);
        System.out.println(sharePoint);
    }


}
