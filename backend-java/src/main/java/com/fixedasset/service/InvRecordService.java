package com.fixedasset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fixedasset.entity.InvRecord;

public interface InvRecordService extends IService<InvRecord> {
    void saveRecord(InvRecord invRecord);
}
