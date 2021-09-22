package com.journalmanager.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.journalmanager.domain.RequestJournal;
import com.journalmanager.domain.Transaction;
import com.journalmanager.domain.repository.CommandRepository;

public class CommandService {

	@Autowired
	CommandRepository comandRepository;

	private final Logger log = LoggerFactory.getLogger(CommandService.class);

	public void ingresarTransacciones(RequestJournal request) {
		for (Transaction t : request.getTransactions()) {
			log.info("llegó una trx {}/{}/{}", t.getStoreCode(), t.getTerminalCode(), t.getSequenceCode());
			comandRepository.inserta(t);
		}
	}
}
