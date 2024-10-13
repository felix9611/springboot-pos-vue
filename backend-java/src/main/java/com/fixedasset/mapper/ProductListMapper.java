package com.fixedasset.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fixedasset.dto.ProductListDto;
import com.fixedasset.dto.ProductLocationListDto;
import com.fixedasset.entity.ProductList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductListMapper extends BaseMapper<ProductList> {

    Page<ProductListDto> page(Page page, @Param("ew") Wrapper queryWrapper);

    List<ProductListDto> listAll(@Param("ew") Wrapper queryWrapper);
}
