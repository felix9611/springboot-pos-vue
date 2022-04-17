package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.dto.ProductLocationListDto;
import com.fixedasset.entity.ProductList;
import com.fixedasset.entity.ProductLocation;
import com.fixedasset.mapper.ProductLocationMapper;
import com.fixedasset.service.ProductLocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductLocationServiceImpl extends ServiceImpl<ProductLocationMapper, ProductLocation> implements ProductLocationService {

    @Resource private ProductLocationMapper productLocationMapper;

    public void saveProductLoc(ProductLocation productLocation) {
        productLocationMapper.insert(productLocation);
    }

    public void changeQty(ProductLocation productLocation) {
        LambdaQueryWrapper<ProductLocation> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProductLocation::getLocationId, productLocation.getLocationId());
        queryWrapper.eq(ProductLocation::getProductId, productLocation.getProductId());
        productLocationMapper.update(productLocation, queryWrapper);
    }

    public ProductLocation findOne(ProductLocation productLocation) {
        LambdaQueryWrapper<ProductLocation> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProductLocation::getProductId, productLocation.getProductId());
        queryWrapper.eq(ProductLocation::getLocationId, productLocation.getLocationId());
        return productLocationMapper.selectOne(queryWrapper);
    }

    public Page<ProductLocationListDto> newPage(Page page, LambdaQueryWrapper<ProductLocation> queryWrapper) {
        return productLocationMapper.page(page, queryWrapper);
    }

}
