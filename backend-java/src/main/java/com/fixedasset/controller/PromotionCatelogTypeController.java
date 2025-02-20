package com.fixedasset.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fixedasset.common.lang.Result;
import com.fixedasset.entity.ProductType;
import com.fixedasset.entity.PromotionCatelogType;
import com.fixedasset.service.PromotionCatelogTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Promotion Catelog Type")
@RestController
@RequestMapping("/base/promotion-catelog")
public class PromotionCatelogTypeController extends BaseController {

    @Resource private PromotionCatelogTypeService promotionCatelogTypeService;

    @Operation(summary = "Create")
    @PostMapping("/create")
    public Result save(@RequestBody PromotionCatelogType promotionCatelogType) {
   //     promotionCatelogTypeService.(promotionCatelogType);
        return Result.succ(promotionCatelogType);
    }
    
}
