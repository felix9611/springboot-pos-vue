package com.fixedasset.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fixedasset.common.lang.Result;
import org.springframework.util.StringUtils;
import com.fixedasset.entity.Member;
import com.fixedasset.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/base/member")
public class MemberController extends BaseController {

    @Resource private MemberService memberService;

    @PostMapping("/create")
    public Result save(@RequestBody Member member) {
        memberService.save(member);
        return Result.succ(member);
    }

    @PostMapping("/update")
    // @PreAuthorize("hasAuthority('base:dept:update')")
    public Result update(@RequestBody Member member) {
        return Result.succ(member);
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable("id") Long id) {
        return Result.succ(memberService.getById(id));
    }

    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Long id) {
        memberService.voidMember(id);
        return Result.succ(id);
    }

    @PostMapping("/listAll")
    public Result listAll(@RequestBody Member member) {
        Page page = new Page(member.getPage(), member.getLimit());
        LambdaQueryWrapper<Member> queryWrapper = Wrappers.lambdaQuery();

        if (!StringUtils.isEmpty(member.getPhone())) {
            queryWrapper.like(Member::getPhone, member.getPhone());
        }

        queryWrapper.eq(Member::getStatus, 1);
        Page<Member> iPage = memberService.page(page, queryWrapper);
        return Result.succ(iPage);
    }

    @GetMapping("/getAll")
    public Result getAll() {
        return Result.succ(memberService.getAll());
    }

}
