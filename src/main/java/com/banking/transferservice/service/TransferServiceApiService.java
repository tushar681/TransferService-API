package com.banking.transferservice.service;

import java.util.List;

import com.banking.transferservice.model.Account;
import com.banking.transferservice.model.Transaction;

public interface TransferServiceApiService {
	
	void transferAmount(Transaction transaction);
	
	List<Account> getAllAccounts();

}
