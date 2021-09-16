package com.banking.transferservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.transferservice.expection.BadRequestException;
import com.banking.transferservice.expection.ElementNotFoundException;
import com.banking.transferservice.model.Account;
import com.banking.transferservice.model.Transaction;
import com.banking.transferservice.repository.TransferServiceApiRepository;
import com.banking.transferservice.service.TransferServiceApiService;

@Service
@Transactional
public class TransferServiceApiServiceImpl implements TransferServiceApiService {

	private final TransferServiceApiRepository transferServiceRepository;

	@Autowired
	public TransferServiceApiServiceImpl(final TransferServiceApiRepository transferServiceRepository) {
		this.transferServiceRepository = transferServiceRepository;
	}

	/**
	 * Method to transfer amount from source to destination account
	 **/
	@Override
	public void transferAmount(Transaction transaction) {
		if (null != transaction.getSourceAccountNumber() && null != transaction.getDestinationAccountNumber()) {
			Account sourceAccount = transferServiceRepository.findById(transaction.getSourceAccountNumber())
					.orElseThrow(() -> new ElementNotFoundException("SourceAccountNumber is invalid"));
			Account destinationAccount = transferServiceRepository.findById(transaction.getDestinationAccountNumber())
					.orElseThrow(() -> new ElementNotFoundException("DestinationAccountNumber is invalid"));

			if (transaction.getAmount() <= sourceAccount.getBalance()) {

				// Deducting transactional amount from source account
				sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());

				// Adding transactional amount to destination account
				destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());
			} else {
				throw new BadRequestException(" Source account do not have sufficient balance");
			}

			transferServiceRepository.saveAndFlush(sourceAccount);
			transferServiceRepository.saveAndFlush(destinationAccount);
		} else {
			throw new BadRequestException(" Source and destination account number are mandatory fields");
		}
	}

	/**
	 * Method to retrieve all accounts
	 **/
	@Override
	public List<Account> getAllAccounts() {
		return transferServiceRepository.findAll();
	}
}
