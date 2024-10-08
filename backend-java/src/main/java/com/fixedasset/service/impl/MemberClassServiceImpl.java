package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.entity.ActionRecord;
import com.fixedasset.entity.Location;
import com.fixedasset.entity.MemberClass;
import com.fixedasset.mapper.ActionRecordMapper;
import com.fixedasset.mapper.MemberClassMapper;
import com.fixedasset.mapper.MemberMapper;
import com.fixedasset.service.MemberClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberClassServiceImpl extends ServiceImpl<MemberClassMapper, MemberClass> implements MemberClassService {
    @Resource private MemberClassMapper memberClassMapper;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private MemberClass memberClass;

    @Resource private ActionRecord actionRecord;

    public List<MemberClass> getAll() {
        LambdaQueryWrapper<MemberClass> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MemberClass::getStatus, 1);
        return memberClassMapper.selectList(queryWrapper);
    }

    public void saveData(MemberClass memberClass){
        memberClass.setCreatedAt(LocalDateTime.now());
        memberClass.setStatus(1);
        memberClassMapper.insert(memberClass);

        actionRecord.setActionName("Create");
        actionRecord.setActionMethod("POST");
        actionRecord.setActionFrom("Member Class Manger");
        actionRecord.setActionData(memberClass.toString());
        actionRecord.setActionSuccess("Success");
        actionRecord.setCreated(LocalDateTime.now());
        createdAction(actionRecord);
    }

    public void update(MemberClass memberClass){
        memberClass.setUpdatedAt(LocalDateTime.now());
        memberClassMapper.updateById(memberClass);

        actionRecord.setActionName("Update");
        actionRecord.setActionMethod("POST");
        actionRecord.setActionFrom("Member Class Manger");
        actionRecord.setActionData(memberClass.toString());
        actionRecord.setActionSuccess("Success");
        actionRecord.setCreated(LocalDateTime.now());
        createdAction(actionRecord);
    }

    public void voidClass(Long id) {
        memberClass.setId(id);
        memberClass.setStatus(0);

        actionRecord.setActionName("Remove");
        actionRecord.setActionMethod("DELETE");
        actionRecord.setActionFrom("Member Class Manger");
        actionRecord.setActionData(memberClass.toString());
        actionRecord.setActionSuccess("Success");
        actionRecord.setCreated(LocalDateTime.now());
        createdAction(actionRecord);

        memberClassMapper.updateById(memberClass);
    }


    public int createdAction(ActionRecord actionRecord) {
        return actionRecordMapper.insert(actionRecord);
    }
}
