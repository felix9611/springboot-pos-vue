package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fixedasset.dto.InvoiceItemListDto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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

    @TableField("discount_type")
    private String discountType;

    @TableField("location_id")
    private int locationId;

    @TableField("tax_total")
    private Double taxTotal;

    @TableField("tax_ref_no")
    private String taxRefNo;

    @TableField("void")
    private int voidNum;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("void_at")
    private LocalDateTime voidAt;

    @TableField(exist = false)
    private int page = 1;

    @TableField(exist = false)
    private int limit = 10;

    @TableField(exist = false)
    private String dateTo;

    @TableField(exist = false)
    private String dateFrom;

    @TableField(exist = false)
    private List<InvoiceItemListDto> invoiceItems;
    
    @TableField(exist = false)
    private List<InvoiceItem> newInvoiceItems;

    @TableField(exist = false)
    private List<Payment> paymentItems;

    @TableField(exist = false)
    private List<Payment> newPaymentItems;

}
