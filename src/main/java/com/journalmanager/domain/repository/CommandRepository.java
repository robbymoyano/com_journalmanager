package com.journalmanager.domain.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.journalmanager.domain.Transaction;

@Mapper
public interface CommandRepository {

	@Insert("insert into pos_transactions values (3, #{id}, #{date}, #{time}, #{storeCode}, #{terminalCode}, #{sequenceCode}"
			+ " , #{trxType}, #{orderNumber}, #{totalAmount}, #{foliumDocumentId}, #{status}, #{body} )")
	public void inserta(Transaction t);
	
}



