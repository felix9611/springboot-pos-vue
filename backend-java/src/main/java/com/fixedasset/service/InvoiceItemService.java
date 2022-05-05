package com.fixedasset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fixedasset.entity.InvoiceItem;

public interface InvoiceItemService extends IService<InvoiceItem> {
    void saveItem(InvoiceItem invoiceItem);
}
