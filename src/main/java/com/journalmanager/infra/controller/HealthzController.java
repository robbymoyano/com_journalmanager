package com.journalmanager.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthzController {

	@GetMapping(value = "/healthz")
	public ResponseEntity<?> getAllTransactions() {

		return new ResponseEntity(HttpStatus.OK);

	}
}
