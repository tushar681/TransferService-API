package com.banking.transferservice.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.banking.transferservice.model.Account;
import com.banking.transferservice.model.Transaction;
import com.banking.transferservice.repository.TransferServiceApiRepository;
import com.banking.transferservice.service.TransferServiceApiService;
import com.banking.transferservice.testing.SlowTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@SlowTest
class TransferServiceApiControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Mock
	private TransferServiceApiService transferServiceApiService;

	@Autowired
	private TransferServiceApiRepository repository;

	@Test
	@DisplayName("transfer amount")
	void transferAmount() throws Exception {

		Transaction transaction = new Transaction();
		transaction.setAmount(10.0);
		transaction.setSourceAccountNumber("0934567890786531");
		transaction.setDestinationAccountNumber("0934567890786532");

		Account sourceAccount = new Account();
		sourceAccount.setAccountNumber("0934567890786531");

		sourceAccount = repository.findById(sourceAccount.getAccountNumber())
				.orElseThrow(() -> new IllegalStateException("invalid account"));

		double expectedBalance = sourceAccount.getBalance() - transaction.getAmount();

		Account expectedSourceResponse = new Account();
		expectedSourceResponse.setAccountNumber("0934567890786531");
		expectedSourceResponse.setBalance(expectedBalance);

		mockMvc.perform(
				put("/banking").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(transaction)))
				.andExpect(status().is2xxSuccessful());

		assertThat(repository.findById(sourceAccount.getAccountNumber())
				.orElseThrow(() -> new IllegalStateException("invalid account")), equalTo(expectedSourceResponse));
	}

	@Test
	@DisplayName("When all account are requested then they are all returned")
	void getAllAccounts() throws Exception {
		mockMvc.perform(get("/banking/accounts")).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$", hasSize((int) repository.count())));
	}
}
