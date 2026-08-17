package model;

public class Accounts {
	//=================================================== FIELDS ===================================================
	private int account_number;
	private int customer_id;
	private String account_type;
	private int balance;
	
	//=================================================== CONSTRACTORS ===================================================
	public Accounts(int account_number,int customer_id,String account_type,int balance) {
		if(balance <= 0) {
			System.out.println("Balance Can't Be Less Than Or Equal To 0..");
			return;
		}
		this.account_number = account_number;
		this.customer_id = customer_id;
		this.account_type = account_type;
		this.balance = balance;
	}
	
	//=================================================== OVERRIDE METHODS ===================================================
	@Override
	public String toString() {
		return "{ Account Number: " + this.account_number + ", Customer ID: " + this.customer_id + ", Accounts Type: " + this.account_type + ", Balance: " + this.balance + "}";
	}
	
	//=================================================== GETTERS ===================================================
	public int getAccountNumber() {
		return this.account_number;
	}
	public String getAccountType() {
		return this.account_type;
	}
	
	public int getCustomerID() {
		return this.customer_id;
	}
	public int getBalance() {
		return this.balance;
	}
	//=================================================== SETTERS ===================================================
	public void setAccountNumber(int account_number) {
		this.account_number = account_number;
	}
	public void setAccountType(String account_type) {
		this.account_type = account_type;
	}
	
	public void setCustomerID(int customer_id) {
		this.customer_id = customer_id;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
