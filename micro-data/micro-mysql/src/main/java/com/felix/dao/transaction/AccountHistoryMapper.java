package com.felix.dao.transaction;


import com.felix.entity.transaction.AccountHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountHistoryMapper {
    int deleteByPrimaryKey(Long accountHistoryId);

    int insert(AccountHistory record);

    int insertSelective(AccountHistory record);

    AccountHistory selectByPrimaryKey(Long accountHistoryId);

    int updateByPrimaryKeySelective(AccountHistory record);

    int updateByPrimaryKey(AccountHistory record);

    List<AccountHistory> selectPage(@Param("accountId") Long accountId, @Param("startTs") Long startTs
            , @Param("endTs") Long endTs, @Param("from") Long from, @Param("size") Integer size
            , @Param("direct") String direct);

    List<AccountHistory> selectList(@Param("accountId") Long accountId, @Param("startTs") Long startTs
            , @Param("endTs") Long endTs);


}