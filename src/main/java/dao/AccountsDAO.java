package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Accounts;
import util.DBConnection;

public class AccountsDAO {
	DBConnection dbConnection = new DBConnection();
	Connection connection = dbConnection.getConnection();
	
	//======================================= CREATE ACCOUNT ==========================================
	public void addAccount(Accounts account) {
		String sql3 = "select * from Accounts where account_number = ?";
		String sql = "insert into Accounts(account_number, customer_id, account_type, balance) values(?,?,?,?)";
		String sql2 = "select customer_id from Customers where customer_id = ?";
		int isAdded = 0;
		try(PreparedStatement preparedStatement3 = connection.prepareStatement(sql3)){
			preparedStatement3.setInt(1, account.getAccountNumber());
			ResultSet rs2 = preparedStatement3.executeQuery();
			if(rs2.next()) {
				System.out.println("User Already Exists With That Account Number");
			} else {
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql2))
				{
					preparedStatement.setInt(1, account.getCustomerID());
					try(ResultSet rs = preparedStatement.executeQuery()){
						while(rs.next()) {
							if(account.getCustomerID() == rs.getInt("customer_id")) {
								try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql)){
									preparedStatement2.setInt(1, account.getAccountNumber());
									preparedStatement2.setInt(2, account.getCustomerID());
									preparedStatement2.setString(3, account.getAccountType());
									preparedStatement2.setInt(4, account.getBalance());
									isAdded = preparedStatement2.executeUpdate();
								}
							}
						} if(isAdded > 0) System.out.println("New Account Created");
						else {
							System.out.println("Customer not found. \nPlease create a customer first.");
						}
					}
				}
			}
			}
		 catch (SQLException e) {
			System.err.println("Database error occurred: " + e.getMessage());
		}
	}
	
	//======================================= VIEW ACCOUNT ==========================================
	public void viewAccount(int id) {
		String sql = "select * from Accounts where account_number = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					int acc_no = rs.getInt("account_number");
					int cus_no = rs.getInt("customer_id");
					String account_type = rs.getString("account_type");
					int balance = rs.getInt("balance");
					Accounts accounts = new Accounts(acc_no, cus_no, account_type, balance);
					System.out.println(accounts.toString());
				} else {
					System.out.println("Account Not Found");
				}
			} 
		} catch (SQLException e) {
			System.err.println("Database error occurred: " + e.getMessage());
		}
	}
	
	//======================================= CHECK BALANCE ============================================
	public void checkBalance(int id) {
		String sql = "select balance from accounts where account_number = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setInt(1, id);
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					System.out.println("The Balance Amount Is: " + rs.getInt("balance"));
				} else {
					System.out.println("Account Not Found");
				}
			}
		} catch (SQLException e) {
			System.err.println("Database error occurred: " + e.getMessage());
		}
	}
	
	
	//======================================= CLOSE ACCOUNT ==============================================
	public void closeAccount(int id) {
		String sql = "select balance from accounts where account_number = ?";
		String sql2 = "delete from accounts where id = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setInt(1, id);
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					if(rs.getInt("balance") > 0) {
						System.out.println("Please withdraw or transfer the remaining balance first.");
					} else {
						try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)){
							preparedStatement2.setInt(1, id);
							int isDeleted = preparedStatement2.executeUpdate();
							if(isDeleted > 0) {
								System.out.println("Account Deleted Successfully");
							} else {
								System.out.println("Could'nt Delete Account");
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Database error occurred: " + e.getMessage());
		}
	}
}
