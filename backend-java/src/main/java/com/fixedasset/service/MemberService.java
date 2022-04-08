package com.fixedasset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fixedasset.entity.Member;

import java.util.List;

public interface MemberService extends IService<Member> {
    void createNew(Member member);

    void voidMember(Long id);

    public List<Member> getAll();

    void updateData(Member member);
}
