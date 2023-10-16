package com.felix.dao.member;


import com.felix.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Long memberId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long memberId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}