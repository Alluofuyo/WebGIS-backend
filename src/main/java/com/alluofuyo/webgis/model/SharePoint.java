package com.alluofuyo.webgis.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@TableName(value = "tbl_share",autoResultMap = true)
@ToString
public class SharePoint {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    @TableField(exist = false)
    private String nickname;
    private String title;
    private String content;
    private Integer positionid;
    @TableField(exist = false,typeHandler = JacksonTypeHandler.class)
    private List<Tag> tags;
    @TableField(exist = false, typeHandler = JacksonTypeHandler.class)
    private Position position;
    private Date time;
}
