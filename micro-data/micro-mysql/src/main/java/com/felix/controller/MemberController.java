package com.felix.controller;

import com.felix.dao.member.MemberMapper;
import com.felix.entity.member.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author felix
 * @desc some desc
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Resource
    private MemberMapper memberMapper;

    @GetMapping("/{memberId}")
    public Member get(@PathVariable("memberId") Long memberId) {

        Member member = memberMapper.selectByPrimaryKey(memberId);

        log.info("====> member get, memberId {}, member {}", memberId, member);

        return member;

    }
}
