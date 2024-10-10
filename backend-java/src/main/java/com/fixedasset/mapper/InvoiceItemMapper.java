package com.fixedasset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fixedasset.dto.InvoiceItemListDto;
import com.fixedasset.entity.InvoiceItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InvoiceItemMapper extends BaseMapper<InvoiceItem> {

    List<InvoiceItemListDto> listByInvoiceId(@Param("invoiceId") Long invoiceId);
}
