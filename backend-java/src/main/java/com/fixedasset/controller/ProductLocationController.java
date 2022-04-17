package com.fixedasset.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fixedasset.common.lang.Result;
import com.fixedasset.dto.ProductLocationListDto;
import com.fixedasset.entity.ProductLocation;
import com.fixedasset.service.ProductLocationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product/location")
public class ProductLocationController extends BaseController{

    @Resource private ProductLocationService productLocationService;

    @PostMapping("/save")
    public Result saveQty(@RequestBody ProductLocation productLocation) {
        ProductLocation oldRecord = productLocationService.findOne(productLocation);
        if (oldRecord != null) {
            ProductLocation renewRecord = new ProductLocation();
            renewRecord.setLocationId(productLocation.getLocationId());
            renewRecord.setProductId(productLocation.getProductId());
            int newQty = 0;
            newQty = oldRecord.getQty() + productLocation.getQty();
            renewRecord.setQty(newQty);
            productLocationService.changeQty(renewRecord);

        } else if (oldRecord == null) {
            productLocationService.saveProductLoc(productLocation);
        }
        return Result.succ(productLocation);
    }

    @PostMapping("/list")
    public Result list(@RequestBody ProductLocation productLocation) {
        Page page = new Page(productLocation.getPage(), productLocation.getLimit());
        LambdaQueryWrapper<ProductLocation> queryWrapper = Wrappers.lambdaQuery();

        if(!(productLocation.getLocationId() == 0)) {
            queryWrapper.eq(ProductLocation::getLocationId, productLocation.getLocationId());
        }

        Page<ProductLocationListDto> iPage = productLocationService.newPage(page, queryWrapper);
        return Result.succ(iPage);
    }

}
