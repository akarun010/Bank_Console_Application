package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {
	private final static String url = "jdbc:mysql://localhost:3306/Bank_Management";
	private final static String password = "root";
	private final static String user = "root";
	
	 public Connection getConnection(){
		 try{
			 return DriverManager.getConnection(url, user, password);
		 } catch(SQLException e) {
			throw new RuntimeException("Database Problem Occured...", e);
		 }
	 }
}
