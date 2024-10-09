package com.fixedasset.dto;

import javax.persistence.Transient;

public class ProductLocationUploadDto {
    @Transient private String locationCode;

    @Transient private String locationName;

    @Transient private int qty;
}
