package com.journalmanager.domain;

import java.util.List;

public class RequestJournal {

	List<Transaction> transactions;
	List<String> locals;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public List<String> getLocals() {
		return locals;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
