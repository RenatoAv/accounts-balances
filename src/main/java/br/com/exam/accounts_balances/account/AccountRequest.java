package br.com.exam.accounts_balances.account;

import br.com.exam.accounts_balances.balance.BalanceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AccountRequest(@JsonProperty("number") String number,
                             @JsonProperty("type") String type,
                             @JsonProperty("balances") List<BalanceRequest> balances) {
}
