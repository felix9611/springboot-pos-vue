package com.fixedasset.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@TableName("product_list")
@Component
public class ProductList extends BaseEntity{
    @TableField("product_code")
    private String productCode;

    @TableField("product_name")
    private String productName;

    @TableField("item_code")
    private String itemCode;

    @TableField("brand_name")
    private String brandName;

    @TableField("type_id")
    private int typeId;

    @TableField("unit")
    private String unit;

    @TableField("description")
    private String description;

    @TableField("retail_price")
    private Double retailPrice;

    @TableField("dept_id")
    private int deptId;

    @TableField("vendor_id")
    private int vendorId;

    @TableField(exist = false)
    private int page = 1;

    @TableField(exist = false)
    private int limit = 10;

}