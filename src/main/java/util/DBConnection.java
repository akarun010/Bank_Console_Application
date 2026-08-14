package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String url = "jdbc:mysql://localhost:3306/student_management";
	private static final String user = "root";
	private static final String password = "root";
	private Connection connection;
	
	public DBConnection() {
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return connection;
	}
}
