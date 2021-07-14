package com.alluofuyo.webgis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.alluofuyo.webgis.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
