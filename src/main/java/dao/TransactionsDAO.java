package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transactions;
import util.DBConnection;

public class TransactionsDAO {
	DBConnection dbConnection = new DBConnection();
	
	//======================================== DEPOSIT AMOUNT ================================================
	public void depositMoney(Transactions transactions) {
		String sql4 = "select * from transactions where transaction_id = ?";
		String sql3 = "select account_number from accounts where account_number = ?";
		String sql = "insert into transactions(transaction_id,account_number,transaction_type,amount,date) values(?,?,'Deposit',?,?)";
		String sql2 = "update accounts set balance = balance  + ? where account_number = ?";
		try(Connection connection = dbConnection.getConnection()){
			try(PreparedStatement preparedStatement4 = connection.prepareStatement(sql4)){
				preparedStatement4.setInt(1, transactions.getTransactionID());
				ResultSet rs2 = preparedStatement4.executeQuery();
				if(rs2.next()) {
					System.out.println("Transaction ID Already Exists");
				} else {
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
						System.out.println("Couldn't Find The Account");
					}
				}
				} 
				}
			}
			catch (SQLException e) {
			System.err.println("Database error occurred: " + e.getMessage());
		}
	}
	
	//============================================ WITHDRAW MONEY =========================================================
	public void withdrawMoney(Transactions transactions) {
		String sql3 = "select * from accounts where account_number = ?";
		String sql = "insert into transactions(transaction_id,account_number,transaction_type,amount,date) values(?,?,'Withdraw',?,?)";
		String sql2 = "update accounts set balance = balance  - ? where account_number = ?";
		try(Connection connection = dbConnection.getConnection()){
			try(PreparedStatement preparedStatement3 = connection.prepareStatement(sql3)){
				preparedStatement3.setInt(1, transactions.getAccountNumber());
				ResultSet rs = preparedStatement3.executeQuery();
				if(rs.next()) {
					if(rs.getInt("balance") - transactions.getAmount() > 0) {
						try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
							preparedStatement.setInt(1, transactions.getTransactionID());
							preparedStatement.setInt(2, transactions.getAccountNumber());
							preparedStatement.setInt(3, transactions.getAmount());
							preparedStatement.setString(4, transactions.getDate());
							int  isWithdrawed = preparedStatement.executeUpdate();
							int isWithdrawed2 = 0;
							try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)){
								preparedStatement2.setInt(1, transactions.getAmount());
								preparedStatement2.setInt(2, transactions.getAccountNumber());
								isWithdrawed2 = preparedStatement2.executeUpdate();
							}
							if(isWithdrawed > 0 && isWithdrawed2 > 0) {
								System.out.println("Amount Withdrawn.....");
							} else {
								System.out.println("Amount Didn't Withdrawn....");
							}
						}
					} else {
						System.out.println("Insufficient Amount....");
					}
					
			} else {
				System.out.println("Couldn't Find The Account");
			}
		}
		} catch (SQLException e) {
			System.err.println("Database error occurred: " + e.getMessage());
		}
	}

	//============================================ TRANSFER MONEY =========================================================
	public void transferMoney(Transactions transactions,Transactions transactions2) {
		String sql3 = "select * from accounts where account_number = ?";
		String sql4 = "select * from accounts where account_number = ?";
		String sql = "insert into transactions(transaction_id,account_number,transaction_type,amount,date) values(?,?,'Transfer_OUT',?,?)";
		String sql6 = "insert into transactions(transaction_id,account_number,transaction_type,amount,date) values(?,?,'Transfer_IN',?,?)";
		String sql2 = "update accounts set balance = balance  - ? where account_number = ?";
		String sql5 = "update accounts set balance = balance  + ? where account_number = ?";
		try(Connection connection = dbConnection.getConnection()){
			connection.setAutoCommit(false);
			try(PreparedStatement preparedStatement3 = connection.prepareStatement(sql3)){
				preparedStatement3.setInt(1, transactions.getAccountNumber());
				ResultSet rs = preparedStatement3.executeQuery();
				try(PreparedStatement preparedStatement4 = connection.prepareStatement(sql4)){
					preparedStatement4.setInt(1, transactions2.getAccountNumber());
					ResultSet rs2 = preparedStatement4.executeQuery();
					if(rs.next() && rs2.next()) {
						if(rs.getInt("balance") - transactions.getAmount() > 0) {
							try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
								preparedStatement.setInt(1, transactions.getTransactionID());
								preparedStatement.setInt(2, transactions.getAccountNumber());
								preparedStatement.setInt(3, transactions.getAmount());
								preparedStatement.setString(4, transactions.getDate());
								int  isTransfered = preparedStatement.executeUpdate();
								int isTransfered2 = 0;
								try(PreparedStatement preparedStatement6 = connection.prepareStatement(sql6)) {
									preparedStatement6.setInt(1, transactions2.getTransactionID());
									preparedStatement6.setInt(2, transactions2.getAccountNumber());
									preparedStatement6.setInt(3, transactions.getAmount());
									preparedStatement6.setString(4, transactions2.getDate());
									preparedStatement6.executeUpdate();
									try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)){
										preparedStatement2.setInt(1, transactions.getAmount());
										preparedStatement2.setInt(2, transactions.getAccountNumber());
										isTransfered2 = preparedStatement2.executeUpdate(); 
										try(PreparedStatement preparedStatement5 = connection.prepareStatement(sql5)){
											preparedStatement5.setInt(1, transactions.getAmount());
											preparedStatement5.setInt(2, transactions2.getAccountNumber());
											preparedStatement5.executeUpdate(); 
										}
									}
									connection.commit();
									if(isTransfered > 0 && isTransfered2 > 0) {
										System.out.println("Amount Transfered.....");
									} else {
										System.out.println("Amount Didn't Transfered....");
									}
								}
							}
						} else {
							System.out.println("Insufficient Amount....");
						}
						
				} else {
					System.out.println("Couldn't Find The Account");
				}
				} catch(SQLException e) {
					try {
						connection.rollback();
					} catch(SQLException e1) {
						System.err.println("Database error occurred: " + e1.getMessage());
					} finally {
						connection.setAutoCommit(true);
					}
				}
		}
		} catch (SQLException e) {
			System.err.println("Database error occurred: " + e.getMessage());
		}
	}
}
