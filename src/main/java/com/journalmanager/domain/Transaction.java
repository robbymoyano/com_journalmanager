package com.journalmanager.domain;

public class Transaction {
	
	String id;
	String date;
	String time;
	int storeCode;
	int terminalCode;
	long sequenceCode;
	int trxType;
	String orderNumber;
	Double totalAmount;
	String foliumDocumentId;
	String status;
	String body;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(int storeCode) {
		this.storeCode = storeCode;
	}

	public int getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(int terminalCode) {
		this.terminalCode = terminalCode;
	}

	public long getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(long sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public int getTrxType() {
		return trxType;
	}

	public void setTrxType(int trxType) {
		this.trxType = trxType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getFoliumDocumentId() {
		return foliumDocumentId;
	}

	public void setFoliumDocumentId(String foliumDocumentId) {
		this.foliumDocumentId = foliumDocumentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", time=" + time + ", storeCode=" + storeCode
				+ ", terminalCode=" + terminalCode + ", sequenceCode=" + sequenceCode + ", trxType=" + trxType
				+ ", orderNumber=" + orderNumber + ", totalAmount=" + totalAmount + ", foliumDocumentId="
				+ foliumDocumentId + ", status=" + status + ", body=" + body + "]";
	}
	
	
}
