package com.felix.dao.transaction;


import com.felix.entity.transaction.AccountHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountHistoryMapper {
    int deleteByPrimaryKey(Long accountHistoryId);

    int insert(AccountHistory record);

    int insertSelective(AccountHistory record);

    AccountHistory selectByPrimaryKey(Long accountHistoryId);

    int updateByPrimaryKeySelective(AccountHistory record);

    int updateByPrimaryKey(AccountHistory record);
}