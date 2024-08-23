package com.bank.service;

import java.util.List;
import java.util.Optional;

import com.bank.dto.AccountDTO;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);
    List<AccountDTO> getAllAccounts();
    Optional<AccountDTO> getAccountById(Long id);
    AccountDTO updateAccount(Long id, AccountDTO accountDTO);
    void deleteAccount(Long id);

    Optional<AccountDTO> findAccountByAccountNumber(String accountNumber);
    Optional<AccountDTO> findAccountByPhoneNumber(String phoneNumber);

    boolean existsByAccountNumber(String accountNumber);
    boolean existsByPhoneNumber(String phoneNumber);

    // New methods for transactions
    AccountDTO deposit(Long id, double amount);
    AccountDTO withdraw(Long id, double amount);
    double checkBalance(Long id);
}
