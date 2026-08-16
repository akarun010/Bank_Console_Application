package model;

import java.sql.Date;

public class Transactions {
	//=================================================== FIELDS ===================================================
	private int transaction_id;
	private int account_number;
	private String transaction_type;
	private int amount;
	private Date date;
	
	//=================================================== CONSTRACTORS ===================================================
	public Transactions(int transaction_id,int account_number,String transaction_type ,int amount,Date date) {
		this.transaction_id = transaction_id;
		this.account_number = account_number;
		this.transaction_type = transaction_type;
		this.amount = amount;
		this.date = date;
	}
	
	//=================================================== OVERRIDE METHODS ===================================================
	@Override
	public String toString() {
		return "{ Transaction ID: " + this.transaction_id + ", Account Number: " + this.account_number + ", Transaction Type: " + this.transaction_type + ", Amount: " + this.amount + ", Date: " + this.date + "}";
	}
	
	//=================================================== GETTERS ===================================================
	public int getTransactionID() {
		return this.transaction_id;
	}
	public int getAccountNumber() {
		return this.account_number;
	}
	public String getTransactionType() {
		return this.transaction_type;
	}
	public int getAmount() {
		return this.amount;
	}
	public Date getDate() {
		return this.date;
	}
	//=================================================== SETTERS ===================================================
	public void getTransactionID(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public void setAge(int account_number) {
		this.account_number = account_number;
	}
	public void setTransactionType(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setPhone(Date date) {
		this.date = date;
	}
}

