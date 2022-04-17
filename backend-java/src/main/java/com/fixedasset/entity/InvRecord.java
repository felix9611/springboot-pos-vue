package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@TableName("login_record")
@Component
public class InvRecord {
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private int page = 1;

    @TableField(exist = false)
    private int limit = 10;
}
