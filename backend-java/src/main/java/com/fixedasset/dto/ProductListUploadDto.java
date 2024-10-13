package com.fixedasset.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import javax.persistence.Transient;

@EqualsAndHashCode()
@Data
public class ProductListUploadDto {
    @Transient private String productCode;

    @Transient private String productName;

    @Transient private String itemCode;

    @Transient private String brandName;

    @Transient private String typeCode;

    @Transient private String typeName;

    @Transient private String unit;

    @Transient private String description;

    @Transient private double retailPrice;

    @Transient private String departmentCode;

    @Transient private String departmentName;

    @Transient private String vendorCode;

    @Transient private String vendorName;

    @Transient private String vendorOtherName;

    @Transient private String vendorType;

    @Transient private String vendorEmail;

    @Transient private String vendorPhone;

    @Transient private String vendorFax;

    @Transient private String vendorAddress;

    @Transient private String vendorContactPerson;

    @Transient private String vendorRemark;

    @Transient private int tax;

    @Transient private String taxCode;

    @Transient private double taxRate;

    @Transient private double afterTax;

    @Transient private double taxAmount;

    @Transient List<ProductLocationUploadDto> productLocations;
}
