package com.journalmanager;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journalmanager.domain.Transaction;
import com.journalmanager.domain.repository.QueriesRepository;

@SpringBootTest
class ComJournalmanagerApplicationTests {

	@Autowired
	QueriesRepository repo;
	
	@Test
	void contextLoads() {
		List<Transaction> lista=repo.getAllTransactions();
		
		for(Transaction t: lista) {
			System.out.println(t.toString());
		}
	}

}
