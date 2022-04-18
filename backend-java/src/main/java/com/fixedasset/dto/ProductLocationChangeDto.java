package com.fixedasset.dto;

public class ProductLocationChangeDto {
    private int productId;
    private int newPlace;
    private int oldPlace;
    private int qty;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int assetId) {
        this.productId = assetId;
    }

    public int getNewPlace() {
        return newPlace;
    }

    public void setNewPlace(int newPlace) {
        this.newPlace = newPlace;
    }

    public int getOldPlace() {
        return oldPlace;
    }

    public void setOldPlace(int oldPlace) {
        this.oldPlace = oldPlace;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
