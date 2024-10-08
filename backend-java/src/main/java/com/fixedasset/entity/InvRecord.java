package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@TableName("invrecord")
@Component
public class InvRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("qty")
    private int qty;

    @TableField("product_id")
    private int productId;

    @TableField("loc_From")
    private int locFrom;

    @TableField("loc_to")
    private int locTo;

    @TableField("cost")
    private double cost;

    @TableField("staff")
    private String staff;

    @TableField("time")
    private LocalDateTime timeAt;

    @TableField(exist = false)
    private LocalDateTime timeAtFrom;

    @TableField(exist = false)
    private LocalDateTime timeAtTo;

    @TableField(exist = false)
    private int page = 1;

    @TableField(exist = false)
    private int limit = 10;
}
