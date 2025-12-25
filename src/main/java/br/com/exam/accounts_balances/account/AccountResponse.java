package br.com.exam.accounts_balances.account;

import br.com.exam.accounts_balances.account.service.repository.AccountEntity;
import br.com.exam.accounts_balances.balance.BalanceResponse;
import br.com.exam.accounts_balances.balance.repository.BalanceEntity;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;

public record AccountResponse(String number,
                              String type,
                              List<BalanceResponse> balances,
                              String totalAmount) {

    public AccountResponse(AccountEntity account,
                           List<BalanceEntity> balances) {
        this(
            account.getNumber(),
            account.getType(),
            toBalanceResponse(balances),
            calculateTotalAmount(balances)
        );

    }

    private static List<BalanceResponse> toBalanceResponse(List<BalanceEntity> balances) {
        return balances != null ?
                balances.stream().map(BalanceResponse::fromEntity).toList() : emptyList();
    }

    private static String calculateTotalAmount(List<BalanceEntity> balances) {
        return balances != null ?
                balances.stream().map(BalanceEntity::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add).toString() : "0";
    }
}
