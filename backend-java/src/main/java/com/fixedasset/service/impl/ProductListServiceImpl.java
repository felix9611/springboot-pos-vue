package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.dto.ProductListDto;
import com.fixedasset.dto.ProductLocationListDto;
import com.fixedasset.entity.ActionRecord;
import com.fixedasset.entity.ProductList;
import com.fixedasset.entity.ProductLocation;
import com.fixedasset.entity.ProductType;
import com.fixedasset.mapper.ActionRecordMapper;
import com.fixedasset.mapper.ProductListMapper;
import com.fixedasset.service.ProductListService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductListServiceImpl extends ServiceImpl<ProductListMapper, ProductList> implements ProductListService {

    @Resource private ProductListMapper productListMapper;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private ActionRecord actionRecord;

    @Resource private ProductList productList;

    public void createOne(ProductList productList) {
        String newCode = getNewCode();
        productList.setProductCode(newCode);
        productList.setStatu(1);
        productList.setCreated(LocalDateTime.now());
        productListMapper.insert(productList);

        actionRecord.setActionName("Create");
        actionRecord.setActionMethod("POST");
        actionRecord.setActionFrom("Product List");
        actionRecord.setActionData(productList.toString());
        actionRecord.setActionSuccess("Success");
        actionRecord.setCreated(LocalDateTime.now());
        createdAction(actionRecord);
    }

    public void voidOne(Long id) {
        productList.setId(id);
        productList.setStatu(0);
        productListMapper.updateById(productList);

        actionRecord.setActionName("Remove");
        actionRecord.setActionMethod("DELETE");
        actionRecord.setActionFrom("Product List");
        actionRecord.setActionData(productList.toString());
        actionRecord.setActionSuccess("Success");
        actionRecord.setCreated(LocalDateTime.now());
        createdAction(actionRecord);
    }

    public void updateOne(ProductList productList) {
        productList.setUpdated(LocalDateTime.now());
        productListMapper.updateById(productList);

        actionRecord.setActionName("Update");
        actionRecord.setActionMethod("POST");
        actionRecord.setActionFrom("Product List");
        actionRecord.setActionData(productList.toString());
        actionRecord.setActionSuccess("Success");
        actionRecord.setCreated(LocalDateTime.now());
        createdAction(actionRecord);
    }

    public ProductList findOne(ProductList productList) {
        LambdaQueryWrapper<ProductList> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProductList::getProductCode, productList.getProductCode());
        queryWrapper.eq(ProductList::getStatu, 1);
        return productListMapper.selectOne(queryWrapper);
    }

    public Page<ProductListDto> newPage(Page page, LambdaQueryWrapper<ProductList> queryWrapper) {
        return productListMapper.page(page, queryWrapper);
    }

    public int createdAction(ActionRecord actionRecord) {
        return actionRecordMapper.insert(actionRecord);
    }

    public String getNewCode() {
        LambdaQueryWrapper<ProductList> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.select(ProductList::getProductCode);

        List<Object> productCodes = productListMapper.selectObjs(lambdaQueryWrapper);
        AtomicReference<Integer> maxCodes = new AtomicReference<>(0);

        productCodes.forEach(o -> {
            String code = String.valueOf(o);
            if (code.length() >= 6) {
                Integer one = Integer.parseInt(code.substring(code.length() - 5));
                if (one > maxCodes.get()) {
                    maxCodes.set(one);
                }
            }

        });
        return padRight(maxCodes.get() + 1, 6, "0");
    }

    public static String padRight(int oriStr, int len, String alexi) {
        StringBuilder str = new StringBuilder();
        int strlen = String.valueOf(oriStr).length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str.append(alexi);
            }
        }
        str.append(oriStr);
        return str.toString();
    }

}