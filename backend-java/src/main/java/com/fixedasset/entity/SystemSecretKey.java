package com.fixedasset.entity;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Component
@Data
@TableName("system_secret_key")
public class SystemSecretKey {

    @TableField("system_code")
    private String systemCode;
    
    @TableField("system_name")
    private String systemName;
}
