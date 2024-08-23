package com.bank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.dto.AccountDTO;
import com.bank.exception.ResourceNotFoundException;
import com.bank.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;


    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAccount(@RequestBody AccountDTO accountDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Ensure balance is correctly set from accountDTO
            AccountDTO savedAccount = accountService.createAccount(accountDTO);
            response.put("data", savedAccount);
            response.put("message", "Account created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", "Failed to create account: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAllAccounts() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<AccountDTO> accounts = accountService.getAllAccounts();
            response.put("data", accounts);
            response.put("message", "Accounts retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", "Failed to retrieve accounts: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getById/{id}")
    public ResponseEntity<Map<String, Object>> getAccountById(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            AccountDTO account = accountService.getAccountById(id).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", id));
            response.put("data", account);
            response.put("message", "Account retrieved by ID");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            response.put("error", "Account not found: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("error", "Failed to retrieve account: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity<Map<String, Object>> updateAccount(@PathVariable("id") Long id, @RequestBody AccountDTO accountDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            AccountDTO updatedAccount = accountService.updateAccount(id, accountDTO);
            response.put("data", updatedAccount);
            response.put("message", "Account updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            response.put("error", "Account not found: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("error", "Failed to update account: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteById/{id}")
    public ResponseEntity<Map<String, Object>> deleteAccount(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            accountService.deleteAccount(id);
            response.put("message", "Account deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            response.put("error", "Account not found: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("error", "Failed to delete account: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getByAccountNumber/{accountNumber}")
    public ResponseEntity<Map<String, Object>> findAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<AccountDTO> account = accountService.findAccountByAccountNumber(accountNumber);
            response.put("data", account);
            response.put("message", "Account retrieved by account number");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            response.put("error", "Account not found: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("error", "Failed to retrieve account by account number: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getByPhoneNumber/{phoneNumber}")
    public ResponseEntity<Map<String, Object>> findAccountByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<AccountDTO> account = accountService.findAccountByPhoneNumber(phoneNumber);
            response.put("data", account);
            response.put("message", "Account retrieved by phone number");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            response.put("error", "Account not found: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("error", "Failed to retrieve account by phone number: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/existsByAccountNumber/{accountNumber}")
    public ResponseEntity<Map<String, Object>> doesAccountExistByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean exists = accountService.existsByAccountNumber(accountNumber);
            response.put("data", exists);
            response.put("message", "Account existence status by account number retrieved");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", "Failed to check account existence by account number: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/existsByPhoneNumber/{phoneNumber}")
    public ResponseEntity<Map<String, Object>> doesAccountExistByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean exists = accountService.existsByPhoneNumber(phoneNumber);
            response.put("data", exists);
            response.put("message", "Account existence status by phone number retrieved");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", "Failed to check account existence by phone number: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PostMapping("/depositAmountById/{id}")
	public ResponseEntity<Map<String, Object>> deposit(@PathVariable("id") Long id, @RequestParam("amount") double amount) {
		Map<String, Object> response = new HashMap<>();
		try {
			AccountDTO updatedAccount = accountService.deposit(id, amount);
			response.put("data", updatedAccount);
			response.put("message", "Deposit successful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response.put("error", "Account not found: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.put("error", "Failed to deposit amount: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/withdrawAmountById/{id}")
	public ResponseEntity<Map<String, Object>> withdraw(@PathVariable("id") Long id, @RequestParam("amount") double amount) {
		Map<String, Object> response = new HashMap<>();
		try {
			AccountDTO updatedAccount = accountService.withdraw(id, amount);
			response.put("data", updatedAccount);
			response.put("message", "Withdrawal successful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response.put("error", "Account not found: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.put("error", "Failed to withdraw amount: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/checkBalanceById/{id}")
	public ResponseEntity<Map<String, Object>> checkBalance(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			double balance = accountService.checkBalance(id);
			response.put("data", balance);
			response.put("message", "Balance retrieved successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response.put("error", "Account not found: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.put("error", "Failed to retrieve balance: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
