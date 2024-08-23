package com.bank.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByPhoneNumber(String phoneNumber);
    //List<Account> findByBranch(String branch);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByAccountNumber(String accountNumber);
}
