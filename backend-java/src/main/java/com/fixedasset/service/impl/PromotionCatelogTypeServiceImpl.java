package com.fixedasset.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.entity.Promotion;
import com.fixedasset.entity.PromotionCatelogType;
import com.fixedasset.mapper.PromotionCatelogTypeMapper;
import com.fixedasset.service.ActionRecordService;
import com.fixedasset.service.PromotionCatelogTypeService;

@Service
public class PromotionCatelogTypeServiceImpl extends ServiceImpl<PromotionCatelogTypeMapper, PromotionCatelogType> implements PromotionCatelogTypeService {
    
    @Resource private ActionRecordService actionRecordService;

    @Resource private PromotionCatelogTypeMapper promotionCatelogTypeMapper;

    @Resource private PromotionCatelogType promotionCatelogType;

    public PromotionCatelogType getById(Long id) {
        LambdaQueryWrapper<PromotionCatelogType> wrapper = Wrappers.lambdaQuery();

        wrapper.eq(PromotionCatelogType::getId, id);
        wrapper.eq(PromotionCatelogType::getStatu, 1);

        return promotionCatelogTypeMapper.selectOne(wrapper);
    }

    public List<PromotionCatelogType> getAll() {
        LambdaQueryWrapper<PromotionCatelogType> wrapper = Wrappers.lambdaQuery();

        wrapper.eq(PromotionCatelogType::getStatu, 1);

        return promotionCatelogTypeMapper.selectList(wrapper);
    }

    public void voidData(Long id) {
        LambdaQueryWrapper<PromotionCatelogType> lambdaQueryWrapper = Wrappers.lambdaQuery();

        lambdaQueryWrapper.eq(PromotionCatelogType::getId, id);
        lambdaQueryWrapper.eq(PromotionCatelogType::getStatu, 1);

        PromotionCatelogType data = promotionCatelogTypeMapper.selectOne(lambdaQueryWrapper);

        if (data.getId().equals(id)) {
            promotionCatelogType.setId(id);
            promotionCatelogType.setStatu(0);
            promotionCatelogType.setUpdated(LocalDateTime.now());

            promotionCatelogTypeMapper.updateById(promotionCatelogType);
            
            actionRecordService.createdAction(
                "Remove", 
                "DELETE", 
                "Promotion Catelog Type Manager", 
                promotionCatelogType.toString(), 
                "Success"
            );
            
        } else {

            actionRecordService.createdAction(
                "Remove", 
                "DELETE", 
               "Promotion Catelog Type Manager",
                id.toString(), 
                "Failure"
            );
        }

    }

    
    public void update(PromotionCatelogType promotionCatelogType) {
        PromotionCatelogType oldData = getById(promotionCatelogType.getId());
    }
}
