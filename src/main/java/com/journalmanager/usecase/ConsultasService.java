package com.journalmanager.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.journalmanager.domain.Transaction;
import com.journalmanager.domain.repository.QueriesRepository;

@Service
public class ConsultasService {

	@Autowired
	QueriesRepository repo;
	
	public List<Transaction> getAllTransactions(){
		return repo.getAllTransactions();
	}
}
