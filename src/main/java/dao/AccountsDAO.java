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
		String sql = "insert into Accounts(account_number, customer_id, account_type, balance) values(?,?,?,?)";
		String sql2 = "select b.customer_id from Customers as a inner join Accounts as b on a.customer_id = b.customer_id";
		int isAdded = 0;
		try (
				PreparedStatement preparedStatement = connection.prepareStatement(sql2);
				ResultSet rs = preparedStatement.executeQuery())
		{
			while(rs.next()) {
				if(account.getCustomerID() == rs.getInt("customer_id")) {
					try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql)){
						preparedStatement2.setInt(1, account.getAccountNumber());
						preparedStatement2.setInt(2, account.getCustomerID());
						preparedStatement2.setString(3, account.getAccountType());
						preparedStatement2.setInt(4, account.getBalance());
						isAdded = preparedStatement.executeUpdate();
					}
				}
			} if(isAdded > 0) System.out.println("New Account Created");
			else {
				System.out.println("Customer not found./nPlease create a customer first.");
			}
		} catch (SQLException e) {
			System.err.println("Some Problem Occured In Database");
		}
	}
	
	//======================================= VIEW ACCOUNT ==========================================
	public void viewAccount(int id) {
		String sql = "select * from Accounts where id = ?";
		boolean isAvaliable = false;
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					System.out.println(rs.toString());
					isAvaliable = true;
				}
			} if(!isAvaliable) {
				System.out.println("Account Not Found");
			}
		} catch (SQLException e) {
			System.err.println("Some Problem Occured In Database");
		}
	}
	
	
	//======================================= CLOSE ACCOUNT ==============================================
	public void closeAccount(int id) {
		String sql = "select balance from accounts where id = ?";
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
			System.err.println("Some Problem Occured In Database");
		}
	}
}
