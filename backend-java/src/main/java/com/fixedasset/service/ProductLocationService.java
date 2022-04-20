package com.fixedasset.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fixedasset.dto.ProductLocationChangeDto;
import com.fixedasset.dto.ProductLocationListDto;
import com.fixedasset.entity.ProductLocation;

public interface ProductLocationService extends IService<ProductLocation> {
    void saveProductLoc(ProductLocation productLocation);
    void changePlace(ProductLocationChangeDto productLocationChangeDto);
    void changeQty(ProductLocation productLocation);
    ProductLocation findOne(ProductLocation productLocation);
    Page<ProductLocationListDto> newPage(Page page, LambdaQueryWrapper<ProductLocation> queryWrapper);
}
