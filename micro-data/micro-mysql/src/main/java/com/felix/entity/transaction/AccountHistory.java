package com.felix.entity.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * generated at 2023-10-16 14:28:25
 */
public class AccountHistory {
    /**
     * id
     */
    private Long accountHistoryId;

    /**
     * 交易类型
     */
    private String transactionType;

    /**
     * id
     */
    private Long accountId;

    /**
     * 可用金额
     */
    private BigDecimal balance;

    /**
     * 冻结金额
     */
    private BigDecimal freeze;

    /**
     * 余额
     */
    private BigDecimal total;

    /**
     * 变动金额
     */
    private BigDecimal amount;

    /**
     * 毫秒时间戳
     */
    private Long ts;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    private LocalDateTime updatedTime;

    /**
     * 其他信息
     */
    private String extraJson;

    public Long getAccountHistoryId() {
        return accountHistoryId;
    }

    public void setAccountHistoryId(Long accountHistoryId) {
        this.accountHistoryId = accountHistoryId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getExtraJson() {
        return extraJson;
    }

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }
}