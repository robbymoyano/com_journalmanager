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
public class ConsultasController {

	@Autowired
	ConsultasService service;
	
	@GetMapping(value = "/transactions", produces = { "application/json" })
	public ResponseEntity<Object> getAllTransactions() {
		try {
			List<Transaction> transactions = service.getAllTransactions();
			return new ResponseEntity<Object>(transactions, HttpStatus.OK);
		} catch (Exception e) {

			MessageResponse message = new MessageResponse();
			message.setStatus(500);
			message.setError(e.getMessage());
			message.setPath("/transactions");
			return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@GetMapping(value = "/locals", produces = { "application/json" })
	public ResponseEntity<Object> getLocals() {
		try {
			List<String> transactions = service.getAllLocals();
			return new ResponseEntity<Object>(transactions, HttpStatus.OK);
		} catch (Exception e) {

			MessageResponse message = new MessageResponse();
			message.setStatus(500);
			message.setError(e.getMessage());
			message.setPath("/locals");
			return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/terminals", produces = { "application/json" })
	public ResponseEntity<Object> getTerminals() {
		try {
			List<String> transactions = service.getAllTerminals();
			return new ResponseEntity<Object>(transactions, HttpStatus.OK);
		} catch (Exception e) {

			MessageResponse message = new MessageResponse();
			message.setStatus(500);
			message.setError(e.getMessage());
			message.setPath("/terminals");
			return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
