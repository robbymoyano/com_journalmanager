package com.journalmanager.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.journalmanager.domain.Transaction;
import com.journalmanager.domain.TransactionFilter;

@Mapper
public interface QueriesRepository {

	@Select("select uuid as id, dt_entry as date, dt_time as time, cod_store as storeCode, cod_ter as terminalCode, cod_seq as sequenceCode,\r\n"
			+ "cod_doc as trxType, num_order as orderNumber, q_amount as totalAmount, num_folio as foliumDocumentId, cod_status as status, txt_body as body \r\n"
			+ "from pos_transactions pt ;")
	public List<Transaction> getAllTransactions();

	@Select("select uuid as id, dt_entry as date, dt_time as time, cod_store as storeCode, cod_ter as terminalCode, cod_seq as sequenceCode,\r\n"
			+ "cod_doc as trxType, num_order as orderNumber, q_amount as totalAmount, num_folio as foliumDocumentId, cod_status as status, txt_body as body \r\n"
			+ "from pos_transactions pt  \r\n"
			+ "where dt_entry = #{date} \r\n"
			+ "and (cod_store = #{storeCode} or #{storeCode} IS null) \r\n"
			+ "and (cod_doc = #{orderNumber} or #{orderNumber} IS null) \r\n"
			+ "and (cod_ter = #{terminalCode} or #{terminalCode} IS null);")
	public List<Transaction> getTransactionsFilter(TransactionFilter t);

	@Select("select distinct cod_store as storeCode \r\n"
			+ "FROM pos_transactions;")
	public List<String> getAllLocals();

	@Select("select distinct cod_ter as terminalCode \r\n"
			+ "FROM pos_transactions;")
	public List<String> getAllTerminals();
}
