package com.felix.dao.transaction;


import com.felix.entity.transaction.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Long accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}