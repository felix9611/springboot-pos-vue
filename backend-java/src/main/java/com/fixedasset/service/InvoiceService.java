package com.fixedasset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fixedasset.entity.Invoice;

public interface InvoiceService extends IService<Invoice> {
    void saveNew(Invoice invoice);
    Invoice findId(String number);
}
