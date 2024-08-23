package com.bank.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.dao.AccountDAO;
import com.bank.domain.Account;
import com.bank.exception.ResourceNotFoundException;
import com.bank.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        if (accountRepository.existsById(id)) {
            account.setId(id);
            return accountRepository.save(account);
        }
        throw new ResourceNotFoundException("Account", "Id", id);
    }

    @Override
    public void deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Account", "Id", id);
        }
    }

    @Override
    public Optional<Account> findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Optional<Account> findAccountByPhoneNumber(String phoneNumber) {
        return accountRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return accountRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }

    @Override
    @Transactional
    public Account depositAmount(Long id, double amount) {
        Account account = getAccountById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", id));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }
    
    @Override
    @Transactional
    public Account withdrawAmount(Long id, double amount) {
        Account account = getAccountById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", id));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance!");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    @Override
    public double checkBalance(Long id) {
        return getAccountById(id)
                .map(Account::getBalance)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", id));
    }

	


}
