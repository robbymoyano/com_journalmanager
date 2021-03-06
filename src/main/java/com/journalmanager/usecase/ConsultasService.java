package com.journalmanager.usecase;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.journalmanager.domain.Transaction;
import com.journalmanager.domain.repository.QueriesRepository;

@Service
public class ConsultasService {

	@Autowired
	QueriesRepository repo;

	private final Logger log = LoggerFactory.getLogger(ConsultasService.class);

	public List<Transaction> getAllTransactions() {
		log.info("Consultado todas las transacciones");
		return repo.getAllTransactions();
	}

}
