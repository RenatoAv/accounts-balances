package br.com.exam.accounts_balances.balance;

import br.com.exam.accounts_balances.account.service.AccountService;
import br.com.exam.accounts_balances.account.service.repository.AccountRepository;
import br.com.exam.accounts_balances.balance.repository.BalanceEntity;
import br.com.exam.accounts_balances.balance.repository.BalanceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BalanceServiceTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldPersistBalancesForGivenAccountId()  {
        var balances = new ArrayList<BalanceRequest>();
        balances.add(new BalanceRequest(new BigDecimal(100), true));
        Assertions.assertDoesNotThrow(() -> balanceService.saveAll(balances, 1L));
    }

    @Test
    public void shouldListAllBalancesForGivenAccountId()  {
        var balances = balanceService.findByAccountIdIn(List.of(1L));
        Assertions.assertNotNull(balances);
    }

    public List<BalanceEntity> mocklBalanceList() {
        var balance = new BalanceEntity();
        balance.setId(1L);
        balance.setAmount(new BigDecimal(100));
        balance.setBlocked(false);

        var balances = new ArrayList<BalanceEntity>();
        balances.add(balance);

        return balances;
    }
}

