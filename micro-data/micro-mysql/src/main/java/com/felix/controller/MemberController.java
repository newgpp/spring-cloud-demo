package com.felix.controller;

import com.felix.dao.member.MemberMapper;
import com.felix.entity.member.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/memberId/{memberId}")
    public Member get(@PathVariable("memberId") Long memberId) {

        Member member = memberMapper.selectByPrimaryKey(memberId);

        log.info("====> member get, memberId {}, member {}", memberId, member);

        return member;

    }

    @GetMapping("/memberName/{nickName}")
    public List<Member> getByNickName(@PathVariable("nickName") String nickName) {

        List<Member> members = memberMapper.selectByNickName(nickName);

        log.info("====> member get, nickName {}, member {}", nickName, members);

        return members;

    }

    @GetMapping("/memberNameIndex/{nickName}")
    public List<Member> getByNickNameIndex(@PathVariable("nickName") String nickName) {

        List<Member> members = memberMapper.selectByNickNameIndex(nickName);

        log.info("====> member get, nickName {}, member {}", nickName, members);

        return members;

    }

    @PostMapping("/realName/set")
    public Integer setRealName(@RequestParam("nickName") String nickName, @RequestParam("realName") String realName) {

        int i = memberMapper.updateRealNameByNickname(nickName, realName);

        log.info("====> member get, nickName {}, realName {}", nickName, realName);

        return i;

    }


}
