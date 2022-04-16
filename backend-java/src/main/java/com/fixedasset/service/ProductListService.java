package com.fixedasset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fixedasset.entity.ProductList;

public interface ProductListService extends IService<ProductList> {
    void createOne(ProductList productList);
    void voidOne(Long id);
    void updateOne(ProductList productList);
    ProductList findOne(ProductList productList);

}
