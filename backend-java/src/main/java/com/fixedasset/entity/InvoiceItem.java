package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@TableName("invoice_item")
@Component
public class InvoiceItem {
    @TableField("invoice_id")
    private int invoiceId;
    @TableField("product_id")
    private int productId;
    @TableField("qty")
    private int qty;
    @TableField("price")
    private Double price;
    @TableField("discount")
    private Double discount;
    @TableField("discount_type")
    private String discountType;
}
