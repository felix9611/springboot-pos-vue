package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@TableName("product_location")
@Component
public class ProductLocation {
    @TableField("product_id")
    private int productId;

    @TableField("location_id")
    private int locationId;

    @TableField("qty")
    private int qty;

    @TableField(exist = false)
    private int page = 1;

    @TableField(exist = false)
    private int limit = 10;
}
