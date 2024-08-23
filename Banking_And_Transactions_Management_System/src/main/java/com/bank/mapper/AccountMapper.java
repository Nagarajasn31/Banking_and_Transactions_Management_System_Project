package com.bank.mapper;

import com.bank.domain.Account;
import com.bank.dto.AccountDTO;

public class AccountMapper {

	public static Account mapToAccount(AccountDTO accountDTO) {
		if (accountDTO == null) {
			return null;
		}

		Account account = new Account();
		account.setId(accountDTO.getId());
		account.setAccountNumber(accountDTO.getAccountNumber());
		account.setAccountHolderName(accountDTO.getAccountHolderName());
		account.setBalance(accountDTO.getBalance()); // Include balance
		account.setBranch(accountDTO.getBranch());
		account.setBranchCode(accountDTO.getBranchCode());
		account.setAccountType(accountDTO.getAccountType());
		account.setIfscCode(accountDTO.getIfscCode());
		account.setCreationDate(accountDTO.getCreationDate());
		account.setStatus(accountDTO.getStatus());
		account.setCurrencyType(accountDTO.getCurrencyType());
		account.setPhoneNumber(accountDTO.getPhoneNumber());

		return account;
	}

	public static AccountDTO mapToAccountDTO(Account account) {
		if (account == null) {
			return null;
		}

		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setAccountNumber(account.getAccountNumber());
		accountDTO.setAccountHolderName(account.getAccountHolderName());
		accountDTO.setBalance(account.getBalance()); // Include balance
		accountDTO.setBranch(account.getBranch());
		accountDTO.setBranchCode(account.getBranchCode());
		accountDTO.setAccountType(account.getAccountType());
		accountDTO.setIfscCode(account.getIfscCode());
		accountDTO.setCreationDate(account.getCreationDate());
		accountDTO.setStatus(account.getStatus());
		accountDTO.setCurrencyType(account.getCurrencyType());
		accountDTO.setPhoneNumber(account.getPhoneNumber());

		return accountDTO;
	}
}
