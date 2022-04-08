package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.entity.Member;
import com.fixedasset.mapper.MemberMapper;
import com.fixedasset.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource private MemberMapper memberMapper;

    @Resource private Member member;

    public void createNew(Member member) {
        member.setCreatedAt(LocalDateTime.now());
        member.setUpdatedAt(LocalDateTime.now());
        member.setStatus(1);
        memberMapper.insert(member);
    }

    public void voidMember(Long id) {
        member.setId(id);
        member.setStatus(0);
        memberMapper.updateById(member);
    }

    public  void updateData(Member member) {
        member.setUpdatedAt(LocalDateTime.now());
        memberMapper.updateById(member);
    }

    public List<Member> getAll() { return memberMapper.getAll(); }
}
