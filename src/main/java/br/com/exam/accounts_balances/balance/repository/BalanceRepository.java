package br.com.exam.accounts_balances.balance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {
    List<BalanceEntity> findByAccountIdIn(List<Long> accountIds);
}
