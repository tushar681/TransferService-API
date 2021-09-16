package com.banking.transferservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banking.transferservice.model.Account;
import com.banking.transferservice.model.Transaction;
import com.banking.transferservice.service.TransferServiceApiService;

@RestController
@RequestMapping("/banking")
public class TransferServiceApiController {

	private final TransferServiceApiService transferServiceApiService;

	@Autowired
	public TransferServiceApiController(TransferServiceApiService transferServiceApiService) {
		this.transferServiceApiService = transferServiceApiService;
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void transferAmount(@RequestBody Transaction transaction) {
		transferServiceApiService.transferAmount(transaction);
	}

	@GetMapping("/accounts")
	@ResponseStatus(HttpStatus.OK)
	public List<Account> getAllAccounts() {
		return transferServiceApiService.getAllAccounts();
	}
}
