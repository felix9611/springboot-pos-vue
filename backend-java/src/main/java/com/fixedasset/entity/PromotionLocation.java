package com.fixedasset.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.hutool.core.date.DateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("promotion_location")
public class PromotionLocation extends BaseEntity {
    @Schema(description = "The place id")
    @TableField("place_id")
    private int placeId;

    @Schema(description = "The promotion name")
    @TableField("promotion_name")
    private String promotionName;

    @Schema(description = "The promotion code")
    @TableField("promotion_code")
    private String promotionCode;

    @Schema(description = "The discount amount")
    @TableField("discount_amount")
    private Double discountAmount;

    @Schema(description = "Discount type")
    @TableField("discount_type")
    private String discountType;
}   
