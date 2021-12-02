package com.journalmanager.infra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.journalmanager.domain.Transaction;
import com.journalmanager.domain.TransactionFilter;
import com.journalmanager.domain.response.MessageResponse;
import com.journalmanager.usecase.ConsultasService;

@RestController
public class CargaController {
	@Autowired
	ConsultasService service;

	@PostMapping(value = "/transactionsFilter", produces = { "application/json" })
	public ResponseEntity<Object> getTransactionsFilter2(@RequestBody TransactionFilter transactionFilter) {
		try {
			List<Transaction> transactions = service.getTransactionsFilter(transactionFilter);
			return new ResponseEntity<Object>(transactions, HttpStatus.OK);
		} catch (Exception e) {

			MessageResponse message = new MessageResponse();
			message.setStatus(500);
			message.setError(e.getMessage());
			message.setPath("/transactionsFilter");
			return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
