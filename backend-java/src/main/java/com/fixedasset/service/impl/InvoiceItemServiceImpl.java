package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.entity.InvoiceItem;
import com.fixedasset.mapper.InvoiceItemMapper;
import com.fixedasset.service.InvoiceItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InvoiceItemServiceImpl extends ServiceImpl<InvoiceItemMapper, InvoiceItem> implements InvoiceItemService {
    @Resource InvoiceItemMapper invoiceItemMapper;

    public void saveItem(InvoiceItem invoiceItem) {
        invoiceItemMapper.insert(invoiceItem);
    }
}
