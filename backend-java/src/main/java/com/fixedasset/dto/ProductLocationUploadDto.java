package com.fixedasset.dto;

import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode()
@Data
public class ProductLocationUploadDto {
    @Transient private String placeCode;

    @Transient private String placeName;

    @Transient private int qty;

    @Transient private int totalPrice;
}
