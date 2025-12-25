package br.com.exam.accounts_balances.balance;

import br.com.exam.accounts_balances.balance.repository.BalanceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record BalanceRequest(@JsonProperty BigDecimal amount,
                             @JsonProperty boolean blocked) {
    public BalanceEntity toEntity(Long accountId, BalanceRequest balanceRequest) {
        var entity = new BalanceEntity();
        entity.setAmount(balanceRequest.amount());
        entity.setBlocked(balanceRequest.blocked());
        entity.setAccountId(accountId);
        return entity;
    }
}
