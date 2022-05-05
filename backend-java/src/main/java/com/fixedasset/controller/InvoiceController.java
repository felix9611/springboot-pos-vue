package com.fixedasset.controller;

import com.fixedasset.common.lang.Result;
import com.fixedasset.entity.Invoice;
import com.fixedasset.entity.InvoiceItem;
import com.fixedasset.service.InvoiceItemService;
import com.fixedasset.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/invoice")
public class InvoiceController extends  BaseController{
    @Resource private InvoiceService invoiceService;
    @Resource private InvoiceItemService invoiceItemService;

    @PostMapping("/save")
    public Result saveName(@RequestBody Invoice invoice) {
        invoiceService.saveNew(invoice);
        return Result.succ(invoice);
    }

    @GetMapping("/{number}")
    public Result findOne(@PathVariable("number")String number) {
        return Result.succ(invoiceService.findId(number));
    }

    @PostMapping("/saveItem")
    public Result saveItems(@RequestBody InvoiceItem invoiceItem) {
        invoiceItemService.saveItem(invoiceItem);
        return Result.succ(invoiceItem);
    }

}
