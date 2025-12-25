package br.com.exam.accounts_balances.account.service.repository;

import br.com.exam.accounts_balances.account.AccountRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private String number;
    private String type;

    public AccountEntity() {}

    public AccountEntity(AccountRequest accountRequest, Long customerId) {
        this.customerId = customerId;
        this.number = accountRequest.number();
        this.type = accountRequest.type();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
