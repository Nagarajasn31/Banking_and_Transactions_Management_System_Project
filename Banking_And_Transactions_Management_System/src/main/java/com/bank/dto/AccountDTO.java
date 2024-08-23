package com.bank.dto;

import java.time.LocalDate;

public class AccountDTO {

	private Long id;

	private String accountNumber; // Auto-generated
	private String accountHolderName;
	private Double balance;
	private String branch;
	private String branchCode;
	private String accountType;
	private String ifscCode;
	private LocalDate creationDate; // Auto-generated
	private String status;
	private String currencyType;
	private String phoneNumber;

	public AccountDTO() {
		super();
	}

	public AccountDTO(Long id, String accountNumber, String accountHolderName, Double balance, String branch,
			String branchCode, String accountType, String ifscCode, LocalDate creationDate, String status,
			String currencyType, String phoneNumber) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.branch = branch;
		this.branchCode = branchCode;
		this.accountType = accountType;
		this.ifscCode = ifscCode;
		this.creationDate = creationDate;
		this.status = status;
		this.currencyType = currencyType;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
