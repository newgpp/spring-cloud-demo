package com.felix.dao.member;


import com.felix.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Long memberId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long memberId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    List<Member> selectByNickName(@Param("nickName") String nickName);

    List<Member> selectByNickNameIndex(@Param("nickName") String nickName);

    int updateRealNameByNickname(@Param("nickName") String nickName, @Param("realName") String realName);
}