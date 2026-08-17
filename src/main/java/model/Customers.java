package model;

public class Customers {
	//=================================================== FIELDS ===================================================
	private int customer_id;
	private String name;
	private int age;
	private String phone;
	private String address;
	
	//=================================================== CONSTRACTORS ===================================================
	public Customers(int customer_id,String name,int age,String phone,String address) {
		this.name = name;
		this.customer_id = customer_id;
		this.age = age;
		this.phone = phone;
		this.address = address;
	}
	
	//=================================================== OVERRIDE METHODS ===================================================
	@Override
	public String toString() {
		return "{ Customer ID: " + this.customer_id + ", Name: " + this.name + ", Age: " + this.age + ", Phone: " + this.phone + ", Address: " + this.address + "}";
	}
	
	//=================================================== GETTERS ===================================================
	public String getPhone() {
		return this.phone;
	}
	public String getAddress() {
		return this.address;
	}
	public String getName() {
		return this.name;
	}
	public int getCustomerID() {
		return this.customer_id;
	}
	public int getage() {
		return this.age;
	}
	//=================================================== SETTERS ===================================================
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCustomerID(int customer_id) {
		this.customer_id = customer_id;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}

