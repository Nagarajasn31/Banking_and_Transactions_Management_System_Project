package com.bank.dao;

import com.bank.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDAO {

    // Basic CRUD operations
    Account createAccount(Account account);
    List<Account> getAllAccounts();
    Optional<Account> getAccountById(Long id);
    Account updateAccount(Long id, Account account);
    void deleteAccount(Long id);

    // Additional methods
    Optional<Account> findAccountByAccountNumber(String accountNumber);
    Optional<Account> findAccountByPhoneNumber(String phoneNumber);
    
    // Existence checks
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByAccountNumber(String accountNumber);
    
    // Transaction methods
    Account depositAmount(Long id, double amount);
    Account withdrawAmount(Long id, double amount);
    double checkBalance(Long id);
}
