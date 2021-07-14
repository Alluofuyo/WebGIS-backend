package com.alluofuyo.webgis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName(value = "tbl_points")
@ToString
public class Position {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String x;
    private String y;
}
