package br.com.exam.accounts_balances.balance;

import br.com.exam.accounts_balances.balance.repository.BalanceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BalanceResponse(@JsonProperty String description,
                              @JsonProperty String amount) {

    public static BalanceResponse fromEntity(BalanceEntity entity) {
        return new BalanceResponse(
            entity.isBlocked() ? "saldo bloqueado" : "saldo disponível",
            entity.getAmount() != null ? entity.getAmount().toString() : "0"
        );
    }
}
