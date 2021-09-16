package com.banking.transferservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.transferservice.model.Account;

public interface TransferServiceApiRepository extends JpaRepository<Account, String>{

}
