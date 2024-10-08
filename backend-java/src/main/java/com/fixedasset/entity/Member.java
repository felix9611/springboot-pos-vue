package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("member")
@Component
public class Member {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("address")
    private String address;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("fax")
    private String fax;

    @TableField("class")
    private int classes;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("status")
    private int status;

    @TableField(exist = false)
    private int page = 1;

    @TableField(exist = false)
    private int limit = 10;

    @TableField(exist = false)
    private List<MemberSpecialDay> memberSpecialDays;

    @TableField(exist = false)
    private String className;
}
