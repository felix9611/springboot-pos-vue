package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.entity.ActionRecord;
import com.fixedasset.entity.ProductListFile;
import com.fixedasset.mapper.ActionRecordMapper;
import com.fixedasset.mapper.ProductListFileMapper;
import com.fixedasset.service.ActionRecordService;
import com.fixedasset.service.ProductListFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductListFileServiceImpl extends ServiceImpl<ProductListFileMapper, ProductListFile> implements ProductListFileService {

    @Resource private ProductListFileMapper productListFileMapper;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private ProductListFile productListFile;

    @Resource private ActionRecordService actionRecordService;

    public void saveListPicture(ProductListFile productListFile){

        productListFile.setStatu(1);
        productListFile.setCreated(LocalDateTime.now());
        productListFileMapper.insert(productListFile);

        actionRecordService.createdAction(
            "Save", 
            "POST", 
            "Department Manger", 
            "Any Data", 
            "Success"
        );
    }

    public List<ProductListFile> getByAssetId(ProductListFile productListFile) {
        LambdaQueryWrapper<ProductListFile> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProductListFile::getStatu, 1);
        queryWrapper.eq(ProductListFile::getProductId, productListFile.getProductId());
        return productListFileMapper.selectList(queryWrapper);
    }

    public void removeFile(Long id) {
        productListFile.setId(id);
        productListFile.setStatu(0);
        productListFileMapper.updateById(productListFile);

        actionRecordService.createdAction(
            "REMOVE", 
            "DELETE", 
            "Department Manger", 
            "Any Data", 
            "Success"
        );
    }

}
