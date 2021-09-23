package com.journalmanager.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.journalmanager.domain.Transaction;

@Mapper
public interface QueriesRepository {

	@Select("select uuid as id, dt_entry as date, dt_time as time, cod_store as storeCode, cod_ter as terminalCode, cod_seq as sequenceCode,\r\n"
			+ "cod_doc as trxType, num_order as orderNumber, q_amount as totalAmount, num_folio as foliumDocumentId, cod_status as status, txt_body as body \r\n"
			+ "from pos_transactions pt ;")
	public List<Transaction> getAllTransactions();

	@Select("select distinct cod_store as storeCode \r\n"
			+ "FROM fal_pos_journal.pos_transactions;")
	public List<String> getAllLocals();
}
