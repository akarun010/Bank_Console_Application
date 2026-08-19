package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customers;
import util.DBConnection;

public class CustomersDAO {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		//=============================================== ADD CUSTOMER ===========================================
		public void addCustomer(Customers customers) {
			String sql = "insert into Customers(customer_id, name, age, phone, address) values(?,?,?,?,?)";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) 
			{
				preparedStatement.setInt(1, customers.getCustomerID());
				preparedStatement.setString(2, customers.getName());
				preparedStatement.setInt(3, customers.getAge());
				preparedStatement.setString(4, customers.getPhone());
				preparedStatement.setString(5, customers.getAddress());
				
				int isAdded = preparedStatement.executeUpdate();
				
				if(isAdded > 0) {
					System.out.println("New Customer Added");
				} else {
					System.out.println("Could'nt Add Customer");
				}
			} catch (SQLException e) {
				System.err.println("Database error occurred: " + e.getMessage());
			}
		}
		
		//=============================================== VIEW CUSTOMERS ===========================================
		public void viewCustomers() {
			String sql = "select * from Customers";
			try(
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();  ) 
			{
				boolean isAvaliable = false;
				while(rs.next()) {
					isAvaliable = true;
					int id = rs.getInt("customer_id");
		            String name = rs.getString("name");
		            int age = rs.getInt("age");
		            String phone = rs.getString("phone");
		            String address = rs.getString("address");
		            Customers customers = new Customers(id, name, age, phone, address);
					System.out.println(customers.toString());
				} if(!isAvaliable) {
					System.out.println("No Customer Found");
				}
			} catch(SQLException e) {
				System.err.println("Database error occurred: " + e.getMessage());
			}
		}
	
		//=============================================== SEARCH CUSTOMER ===========================================
		public void searchCustomer(int cusId) {
			String sql = "select * from Customers where customer_id = ?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
				preparedStatement.setInt(1, cusId);
				try(ResultSet rs = preparedStatement.executeQuery()){
					if(rs.next()) {
						int id = rs.getInt("customer_id");
			            String name = rs.getString("name");
			            int age = rs.getInt("age");
			            String phone = rs.getString("phone");
			            String address = rs.getString("address");
			            Customers customers = new Customers(id, name, age, phone, address);
						System.out.println(customers.toString());
					} else {
						System.out.println("No Customer Found");
					} 
				}
			}  catch(SQLException e) {
				System.err.println("Database error occurred: " + e.getMessage());
			}	
		}
		
		//=============================================== UPDATE CUSTOMER ===========================================
		public void updateCustomer(Customers customers) {
			String sql = "update customers set name = ?, age = ?, phone = ?, address = ? where customer_id = ?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, customers.getName());
				preparedStatement.setInt(2, customers.getAge());
				preparedStatement.setString(3, customers.getPhone());
				preparedStatement.setString(4, customers.getAddress());
				preparedStatement.setInt(5, customers.getCustomerID());
				int isUpdated = preparedStatement.executeUpdate();
				if(isUpdated > 0) {
					System.out.println("Customer Data Updated");
				} else {
					System.out.println("No Customer Found");
				}
			}  catch(SQLException e) {
				System.err.println("Database error occurred: " + e.getMessage());
			}	
		}
		
		
		//=============================================== DELETE CUSTOMER ===========================================
		public void deleteCustomer(int id) {
			String sql = "delete from customers where customer_id = ?";
			String sql2 = "delete from accounts where customer_id = ?";
			try(
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
					){
				preparedStatement2.setInt(1, id);
				int isdeleted = preparedStatement2.executeUpdate();
				preparedStatement.setInt(1, id);
				int isdeleted2 = preparedStatement.executeUpdate();
				if(isdeleted > 0 && isdeleted2 > 0) {
					System.out.println("Customer Data Deleted");
				} else {
					System.out.println("No Customer Found");
				}
			}  catch(SQLException e) {
				System.err.println("Database error occurred: " + e.getMessage()); 
			}	
		} 
}
