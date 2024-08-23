package com.bank.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.dao.AccountDAO;
import com.bank.domain.Account;
import com.bank.dto.AccountDTO;
import com.bank.exception.ResourceNotFoundException;
import com.bank.mapper.AccountMapper;
import com.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	@Override
	public AccountDTO createAccount(AccountDTO accountDTO) {
		if (accountDAO.existsByPhoneNumber(accountDTO.getPhoneNumber())) {
			throw new RuntimeException("Phone number already exists!");
		}
		if (accountDAO.existsByAccountNumber(accountDTO.getAccountNumber())) {
			throw new RuntimeException("Account number already exists!");
		}

		Account account = AccountMapper.mapToAccount(accountDTO);
		Account savedAccount = accountDAO.createAccount(account);
		return AccountMapper.mapToAccountDTO(savedAccount);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		List<Account> accounts = accountDAO.getAllAccounts();
		return accounts.stream()
				.map(AccountMapper::mapToAccountDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<AccountDTO> getAccountById(Long id) {
		Optional<Account> account = accountDAO.getAccountById(id);
		return account.map(AccountMapper::mapToAccountDTO);
	}

	@Override
	public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
		Account existingAccount = accountDAO.getAccountById(id).orElseThrow(() ->
		new ResourceNotFoundException("Account", "Id", id));

		if(accountDTO.getAccountHolderName()!=null) {
			existingAccount.setAccountHolderName(accountDTO.getAccountHolderName());
		}

		if(accountDTO.getBalance()!=null) {
			existingAccount.setBalance(accountDTO.getBalance());
		}

		if(accountDTO.getBranch()!=null) {
			existingAccount.setBranch(accountDTO.getBranch());
		}

		if(accountDTO.getBranchCode()!=null) {
			existingAccount.setBranchCode(accountDTO.getBranchCode());
		}

		if(accountDTO.getAccountType()!=null) {
			existingAccount.setAccountType(accountDTO.getAccountType());
		}

		if(accountDTO.getIfscCode()!=null) {

			existingAccount.setIfscCode(accountDTO.getIfscCode());
		}
		if(accountDTO.getCurrencyType()!=null) {

			existingAccount.setCurrencyType(accountDTO.getCurrencyType());
		}

		if (accountDTO.getPhoneNumber() != null) {
			existingAccount.setPhoneNumber(accountDTO.getPhoneNumber());
		}

		Account updatedAccount = accountDAO.updateAccount(id, existingAccount);
		return AccountMapper.mapToAccountDTO(updatedAccount);
	}

	@Override
	public void deleteAccount(Long id) {
		accountDAO.getAccountById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Account", "Id", id));
		accountDAO.deleteAccount(id);
	}

	@Override
	public Optional<AccountDTO> findAccountByAccountNumber(String accountNumber) {
		return accountDAO.findAccountByAccountNumber(accountNumber)
				.map(AccountMapper::mapToAccountDTO);
	}

	@Override
	public Optional<AccountDTO> findAccountByPhoneNumber(String phoneNumber) {
		return accountDAO.findAccountByPhoneNumber(phoneNumber)
				.map(AccountMapper::mapToAccountDTO);
	}

	@Override
	public boolean existsByAccountNumber(String accountNumber) {
		return accountDAO.existsByAccountNumber(accountNumber);
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return accountDAO.existsByPhoneNumber(phoneNumber);
	}

	@Override
    @Transactional
    public AccountDTO deposit(Long id, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        Account account = accountDAO.depositAmount(id, amount);
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    @Transactional
    public AccountDTO withdraw(Long id, double amount) {
        Account account = accountDAO.withdrawAmount(id, amount);
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public double checkBalance(Long id) {
        return accountDAO.checkBalance(id);
    }
}
