package com.alluofuyo.webgis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("tbl_tags")
@ToString
public class Tag {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String tag;
    private Integer shareid;
}
