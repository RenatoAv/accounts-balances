package br.com.exam.accounts_balances.account.service;

import br.com.exam.accounts_balances.account.AccountRequest;
import br.com.exam.accounts_balances.account.AccountResponse;
import br.com.exam.accounts_balances.account.service.repository.AccountEntity;
import br.com.exam.accounts_balances.account.service.repository.AccountRepository;
import br.com.exam.accounts_balances.balance.BalanceService;
import br.com.exam.accounts_balances.balance.repository.BalanceEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BalanceService balanceService;

    public AccountService(AccountRepository accountRepository, BalanceService balanceService) {
        this.accountRepository = accountRepository;
        this.balanceService = balanceService;
    }

    public void create(AccountRequest accountRequest, Long customerId) {
        AccountEntity accountEntity = new AccountEntity(accountRequest, customerId);
        accountEntity = accountRepository.save(accountEntity);
        balanceService.saveAll(accountRequest.balances(), accountEntity.getId());
    }

    public List<AccountResponse> accountsBy(Long customerId) {
        var accounts = accountRepository.findByCustomerId(customerId);
        var accountIds = accounts.stream().map(AccountEntity::getId).toList();
        var accountBalances = balanceService.findByAccountIdIn(accountIds)
                .stream()
                .collect(groupingBy(BalanceEntity::getAccountId));

        var accountsResponse = new ArrayList<AccountResponse>();

        for (var account : accounts) {
            var balance = accountBalances.get(account.getId());
            accountsResponse.add(new AccountResponse(account, balance));
        }

        return accountsResponse;
    }
}
