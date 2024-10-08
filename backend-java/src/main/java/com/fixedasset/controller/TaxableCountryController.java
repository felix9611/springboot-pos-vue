package com.fixedasset.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fixedasset.common.lang.Result;
import com.fixedasset.dto.TaxInformationUploadData;
import com.fixedasset.entity.TaxableCountry;
import com.fixedasset.service.TaxableCountryService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/country/tax")
public class TaxableCountryController extends BaseController{
    @Resource private TaxableCountryService taxableCountryService;

    @PostMapping("/create")
    public Result create(@RequestBody TaxableCountry taxableCountry) {
        taxableCountryService.createNew(taxableCountry);
        return Result.succ(taxableCountry);
    }

    @PostMapping("/batch-create")
    public Result batchCreate(@RequestBody List<TaxInformationUploadData> taxInformationUploadDatas) {
        taxableCountryService.importData(taxInformationUploadDatas);
        return Result.succ(taxInformationUploadDatas);
    }

    @PostMapping("/update")
    public Result update(@RequestBody TaxableCountry taxableCountry) {
        taxableCountryService.update(taxableCountry);
        return Result.succ(taxableCountry);
    }

    @DeleteMapping("/remove/{id}")
    public Result voidOne(@PathVariable("id") Long id) {
        taxableCountryService.voidData(id);
        return Result.succ(id);
    }


    @GetMapping("/{id}")
    public Result getOne(@PathVariable("id") Long id) {
        return Result.succ(taxableCountryService.findOne(id));
    }

    @PostMapping("/listAll")
    public Result listAll(@RequestBody TaxableCountry taxableCountry) {
        Page page = new Page(taxableCountry.getPage(), taxableCountry.getLimit());
        LambdaQueryWrapper<TaxableCountry> queryWrapper = Wrappers.lambdaQuery();

        if (!StringUtils.isEmpty(taxableCountry.getCountryName())) {
            queryWrapper.like(TaxableCountry::getCountryName, taxableCountry.getCountryName());
        }

        if (!StringUtils.isEmpty(taxableCountry.getTaxName())) {
            queryWrapper.like(TaxableCountry::getTaxName, taxableCountry.getTaxName());
        }

        queryWrapper.eq(TaxableCountry::getStatu, 1);

        Page<TaxableCountry> iPage = taxableCountryService.page(page, queryWrapper);
        return Result.succ(iPage);

    }

    @GetMapping("/getAll")
    public Result getAll() {
        return Result.succ(taxableCountryService.getAll());
    }
}
