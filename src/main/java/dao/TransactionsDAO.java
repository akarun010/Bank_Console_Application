package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transactions;
import util.DBConnection;

public class TransactionsDAO {
	DBConnection dbConnection = new DBConnection();
	Connection connection = dbConnection.getConnection();
	
	//======================================== DEPOSIT AMOUNT ================================================
	public void depositMoney(Transactions transactions) {
		String sql3 = "select account_number from accounts where account_number = ?";
		String sql = "insert into transactions(transaction_id,account_number,transaction_type,amount,date) values(?,?,'Deposit',?,?)";
		String sql2 = "update accounts set balance = balance  + ? where account_number = ?";
		try(PreparedStatement preparedStatement3 = connection.prepareStatement(sql3)){
			preparedStatement3.setInt(1, transactions.getAccountNumber());
			ResultSet rs = preparedStatement3.executeQuery();
			if(rs.next()) {
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setInt(1, transactions.getTransactionID());
					preparedStatement.setInt(2, transactions.getAccountNumber());
					preparedStatement.setInt(3, transactions.getAmount());
					preparedStatement.setString(4, transactions.getDate());
					int  isDeposited = preparedStatement.executeUpdate();
					int isDeposited2 = 0;
					try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)){
						preparedStatement2.setInt(1, transactions.getAmount());
						preparedStatement2.setInt(2, transactions.getAccountNumber());
						isDeposited2 = preparedStatement2.executeUpdate();
					}
					if(isDeposited > 0 && isDeposited2 > 0) {
						System.out.println("Amount Deposited.....");
					} else {
						System.out.println("Amount Didn't Deposited....");
					}
			}
		} else {
			System.out.println("Could'nt Find The Account");
		}
		} catch (SQLException e) {
			System.err.println("Database error occurred: " + e.getMessage());
		}
	}
}
