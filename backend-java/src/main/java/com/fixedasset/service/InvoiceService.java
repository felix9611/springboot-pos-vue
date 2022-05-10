package com.fixedasset.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fixedasset.dto.InvoiceListDto;
import com.fixedasset.entity.Invoice;

import java.util.List;

public interface InvoiceService extends IService<Invoice> {
    void saveNew(Invoice invoice);
    Invoice findId(String number);
    Page<InvoiceListDto> listAll(Page page, Wrapper queryWrapper);
    void voidInv(Long id);
    InvoiceListDto selectOneItem(Long id);
}
