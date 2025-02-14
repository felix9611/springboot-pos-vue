package com.fixedasset.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.entity.Invoice;
import com.fixedasset.entity.Payment;
import com.fixedasset.entity.Promotion;
import com.fixedasset.entity.PromotionDepartment;
import com.fixedasset.entity.PromotionLocation;
import com.fixedasset.entity.PromotionType;
import com.fixedasset.mapper.PromotionDepartmentMapper;
import com.fixedasset.mapper.PromotionLocationMapper;
import com.fixedasset.mapper.PromotionMapper;
import com.fixedasset.mapper.PromotionTypeMapper;
import com.fixedasset.service.ActionRecordService;
import com.fixedasset.service.PromotionService;

@Service
public class PromotionMapperServiceImpl extends ServiceImpl<PromotionMapper, Promotion> implements PromotionService {
    
    @Resource private PromotionMapper promotionMapper;

    @Resource private Promotion promotion;

    @Resource private PromotionDepartmentMapper promotionDepartmentMapper;

    @Resource private PromotionDepartment promotionDepartment;

    @Resource private PromotionLocationMapper promotionLocationMapper;

    @Resource private PromotionLocation promotionLocation;

    @Resource private PromotionTypeMapper promotionTypeMapper;

    @Resource private PromotionType promotionType;

    @Resource private ActionRecordService actionRecordService;

    public Promotion getById(Long id) {
        LambdaQueryWrapper<Promotion> lambdaQueryWrapper = Wrappers.lambdaQuery();

        lambdaQueryWrapper.eq(Promotion::getId, id);
        lambdaQueryWrapper.eq(Promotion::getStatu, 1);

        Promotion data = promotionMapper.selectOne(lambdaQueryWrapper);

        LambdaQueryWrapper<PromotionLocation> lambdaQueryWrapperLocation = Wrappers.lambdaQuery();

        lambdaQueryWrapperLocation.eq(PromotionLocation::getPromotionId, id);
        lambdaQueryWrapperLocation.eq(PromotionLocation::getStatu, 1);

        List<PromotionLocation> promotionLocations = promotionLocationMapper.selectList(lambdaQueryWrapperLocation);

        LambdaQueryWrapper<PromotionDepartment> lambdaQueryWrapperDepartment = Wrappers.lambdaQuery();

        lambdaQueryWrapperDepartment.eq(PromotionDepartment::getPromotionId, id);
        lambdaQueryWrapperDepartment.eq(PromotionDepartment::getStatu, 1);

        List<PromotionDepartment> promotionDepartments = promotionDepartmentMapper.selectList(lambdaQueryWrapperDepartment);

        LambdaQueryWrapper<PromotionType> lambdaQueryWrapperType = Wrappers.lambdaQuery();

        lambdaQueryWrapperType.eq(PromotionType::getPromotionId, id);
        lambdaQueryWrapperType.eq(PromotionType::getStatu, 1);

        List<PromotionType> promotionTypes = promotionTypeMapper.selectList(lambdaQueryWrapperType);
        
        data.setPromotionTypeItems(promotionTypes);
        data.setPromotionDepartmentItems(promotionDepartments);
        data.setPromotionLocationItems(promotionLocations);

        return data;
    }

    public void voidData(Long id) {
        LambdaQueryWrapper<Promotion> lambdaQueryWrapper = Wrappers.lambdaQuery();

        lambdaQueryWrapper.eq(Promotion::getId, id);
        lambdaQueryWrapper.eq(Promotion::getStatu, 1);

        Promotion data = promotionMapper.selectOne(lambdaQueryWrapper);

        if (data.getId().equals(id)) {
            actionRecordService.createdAction(
                "Remove", 
                "DELETE", 
               "Promotion Manager",
                id.toString(), 
                "Failure"
            );
        } else {

            promotion.setId(id);
            promotion.setStatu(0);
            promotion.setUpdated(LocalDateTime.now());

            promotionMapper.updateById(data);
            
            actionRecordService.createdAction(
                "Remove", 
                "DELETE", 
                "Promotion Manager", 
                promotion.toString(), 
                "Success"
            );
        }

    }

    public void update(Promotion data) {
        Promotion oldData = promotionMapper.selectById(data.getId());

        if (oldData.getStatu() == 1) {
            data.setUpdated(LocalDateTime.now());
            promotionMapper.updateById(data);

            List<PromotionLocation> promotionLocations = data.getPromotionLocationItems();
            List<PromotionDepartment> promotionDepartments = data.getPromotionDepartmentItems();
            List<PromotionType> promotionTypes = data.getPromotionTypeItems();

            if (promotionLocations.size() > 0) {
                for (PromotionLocation promotionLocation : promotionLocations) {
                    if (promotionLocation.getId() == null) {
                        promotionLocation.setCreated(LocalDateTime.now());
                        promotionLocation.setStatu(1);
                        promotionLocation.setPromotionId(Math.toIntExact(data.getId()));

                        promotionLocationMapper.insert(promotionLocation);

                    } else {
                        promotionLocation.setUpdated(LocalDateTime.now());
                        promotionLocationMapper.updateById(promotionLocation);
                    }
                }
            }

            if (promotionDepartments.size() > 0) {

                for (PromotionDepartment promotionDepartment : promotionDepartments) {
                    if (promotionDepartment.getId() == null) {
                        promotionDepartment.setCreated(LocalDateTime.now());
                        promotionDepartment.setStatu(1);
                        promotionDepartment.setPromotionId(Math.toIntExact(data.getId()));
                    } else {
                        promotionDepartment.setUpdated(LocalDateTime.now());
                        promotionDepartmentMapper.updateById(promotionDepartment);
                    }
                }
            }

            if (promotionTypes.size() > 0) {
            
                for (PromotionType promotionType : promotionTypes) {
                    if (promotionType.getId() == null) {
                        promotionType.setCreated(LocalDateTime.now());
                        promotionType.setStatu(1);
                        promotionType.setPromotionId(Math.toIntExact(data.getId()));

                        promotionTypeMapper.insert(promotionType);
                    } else {
                        promotionType.setUpdated(LocalDateTime.now());
                        promotionTypeMapper.updateById(promotionType);
                    }
                }
            }

            actionRecordService.createdAction(
                "Update", 
                    "POST", 
                    "Promotion Manager", 
                    promotion.toString(), 
                    "Success"
            );

        }
    }

    public void create(Promotion newData) {
        LambdaQueryWrapper<Promotion> lambdaQueryWrapper = Wrappers.lambdaQuery();

        lambdaQueryWrapper.eq(Promotion::getPromotionCode, newData.getPromotionCode());
        lambdaQueryWrapper.eq(Promotion::getPromotionName, newData.getPromotionName());

        Promotion data = promotionMapper.selectOne(lambdaQueryWrapper);

        if (data == null) {

            promotion.setCreated(LocalDateTime.now());
            promotion.setStatu(1);
            promotionMapper.insert(newData);

            List<PromotionLocation> promotionLocations = newData.getNewPromotionLocationItems();
            List<PromotionDepartment> promotionDepartments = newData.getNewPromotionDepartmentItems();
            List<PromotionType> promotionTypes = newData.getNewPromotionTypeItems();

            if (promotionLocations.size() > 0) {
                for (PromotionLocation promotionLocation : promotionLocations) {
                    promotionLocation.setCreated(LocalDateTime.now());
                    promotionLocation.setStatu(1);
                    promotionLocation.setPromotionId(Math.toIntExact(newData.getId()));

                    promotionLocationMapper.insert(promotionLocation);
                }
            }

            if (promotionDepartments.size() > 0) {

                for (PromotionDepartment promotionDepartment : promotionDepartments) {
                    promotionDepartment.setCreated(LocalDateTime.now());
                    promotionDepartment.setStatu(1);
                    promotionDepartment.setPromotionId(Math.toIntExact(newData.getId()));

                    promotionDepartmentMapper.insert(promotionDepartment);
                }
            }

            if (promotionTypes.size() > 0) {
            
                for (PromotionType promotionType : promotionTypes) {
                    promotionType.setCreated(LocalDateTime.now());
                    promotionType.setStatu(1);
                    promotionType.setPromotionId(Math.toIntExact(newData.getId()));

                    promotionTypeMapper.insert(promotionType);
                }
            }

            actionRecordService.createdAction(
                "Save", 
                    "POST", 
                    "Promotion Manager", 
                    promotion.toString(), 
                    "Success"
            );


        } else {
            actionRecordService.createdAction(
                "Save", 
                "POST", 
                "Promotion Manager", 
                promotion.toString(),
                "Failure"
            );

            throw new RuntimeException("Exist in records! Please check again!");
        }
    }
}
