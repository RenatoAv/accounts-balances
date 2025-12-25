package br.com.exam.accounts_balances.account;

import br.com.exam.accounts_balances.account.service.AccountService;
import br.com.exam.accounts_balances.account.service.repository.AccountEntity;
import br.com.exam.accounts_balances.account.service.repository.AccountRepository;
import br.com.exam.accounts_balances.balance.BalanceRequest;
import br.com.exam.accounts_balances.balance.BalanceService;
import br.com.exam.accounts_balances.balance.repository.BalanceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldPersistAccountsForGivenCustomerId() {
        var accountEntity = new AccountEntity();
        accountEntity.setId(1L);

        when(accountRepository.save(any())).thenReturn(accountEntity);
        Assertions.assertDoesNotThrow(() ->  accountService.create(mockAccountRequest(), 1L));
    }

    @Test
    public void shouldReturnAllAccountsForGivenCustomerId() {
        when(accountRepository.findByCustomerId(anyLong())).thenReturn(mockAccounts());
        when(balanceService.findByAccountIdIn(anyList())).thenReturn(mockBalances());

        var response = accountService.accountsBy(2L);

        Assertions.assertEquals("500", response.getFirst().totalAmount());
    }

    public AccountRequest mockAccountRequest() {
        var balances = new ArrayList<BalanceRequest>();
        var balanceAvaiable = new BalanceRequest(new BigDecimal(200), true);
        var balanceBlocked = new BalanceRequest(new BigDecimal(300), true);

        balances.add(balanceAvaiable);
        balances.add(balanceBlocked);

        return new AccountRequest("2", "F", balances);
    }

    public List<AccountEntity> mockAccounts() {
        var account = new AccountEntity();
        account.setId(2L);
        account.setType("F");
        account.setNumber("37891");
        return List.of(account);
    }

    public List<BalanceEntity> mockBalances() {
        var balanceAvaiable = new BalanceEntity();
        balanceAvaiable.setId(1L);
        balanceAvaiable.setAccountId(2L);
        balanceAvaiable.setAmount(new BigDecimal(150));
        balanceAvaiable.setBlocked(false);

        var balanceBlocked = new BalanceEntity();
        balanceBlocked.setId(1L);
        balanceBlocked.setAccountId(2L);
        balanceBlocked.setAmount(new BigDecimal(350));
        balanceBlocked.setBlocked(false);

        return List.of(balanceAvaiable, balanceBlocked);
    }
}
