package br.com.exam.accounts_balances.balance;

import br.com.exam.accounts_balances.balance.repository.BalanceEntity;
import br.com.exam.accounts_balances.balance.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;


    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public void saveAll(List<BalanceRequest> balances, Long accountId) {
        var balanceEntities = balances.stream().map(
                balanceRequest -> balanceRequest.toEntity(accountId, balanceRequest))
                .toList();
        balanceRepository.saveAll(balanceEntities);
    }

    public Collection<BalanceEntity> findByAccountIdIn(List<Long> accountIds) {
        return balanceRepository.findByAccountIdIn(accountIds);
    }
}
