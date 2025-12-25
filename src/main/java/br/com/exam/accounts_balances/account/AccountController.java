package br.com.exam.accounts_balances.account;

import br.com.exam.accounts_balances.account.service.AccountService;
import br.com.exam.accounts_balances.account.service.repository.AccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/accounts/{customerId}")
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountEntity> create(@RequestBody AccountRequest accountRequest,
                                                @PathVariable Long customerId) {
        try {
            accountService.create(accountRequest, customerId);
            return ok().build();
        } catch (Exception e) {
            logger.error("Error when creating account for customerId: {}", customerId, e);
            return internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> list(@PathVariable Long customerId) {
        try {
            return ok(accountService.accountsBy(customerId));
        } catch (Exception e) {
            logger.error("Error when finding accounts for customerId: {}", customerId, e);
            return internalServerError().build();
        }
    }
}
