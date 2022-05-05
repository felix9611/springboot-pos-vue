package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@TableName("invoice")
@Component
public class Invoice {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("number")
    private String number;

    @TableField("member_id")
    private int memberId;

    @TableField("total_amount")
    private Double totalAmount;

    @TableField("discount")
    private Double discount;

    @TableField("location_id")
    private int locationId;

    @TableField("void")
    private int voidNum;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("void_at")
    private LocalDateTime voidAt;

    public Long getId() {
        return id;
    }


}
