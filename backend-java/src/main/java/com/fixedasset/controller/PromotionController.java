package com.fixedasset.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fixedasset.service.PromotionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Promotion")
@RestController
@RequestMapping("/base/promotion")
public class PromotionController extends BaseController {

    @Resource private PromotionService promotionService;
    
}
