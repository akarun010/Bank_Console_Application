package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Student;
import util.DBConnection;

public class StudentDAO {
	// ========================================================== FIELDS ==========================================================
	private DBConnection dbConnection;
	private Connection connection;
	
	// ========================================================== CONSTRUCTOR ==========================================================
	public StudentDAO(){
		dbConnection = new DBConnection();
		connection = dbConnection.getConnection();
	}
	
	// ========================================================== ADD METHOD ==========================================================
	public void addStudent(Student student) {
		String sql = "INSERT INTO students(name, age, department, marks)\r\n"
				+ "VALUES (?, ?, ?, ?);";
		try(PreparedStatement pstmt = connection.prepareStatement(sql);){
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			pstmt.setString(3, student.getDepartment());
			pstmt.setInt(4, student.getMarks());
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("New Student Added Successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ========================================================== VIEW METHOD ==========================================================
	public void viewStudents() {
		String sql = "SELECT * FROM students";
		try(
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();)
			{
			while(rs.next()) {
				System.out.println("{ Name: " + rs.getString("name") + " , Id: " + rs.getInt("id") + " , Age: " + rs.getInt("age") + " , Department: " + rs.getString("department") + " , Marks: " + rs.getInt("marks") + " }");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ========================================================== SEARCH METHOD ==========================================================
	public void searchStudent(int id) {
		String sql = "SELECT * FROM students\r\n"
				+ "WHERE id = ?;";
		try (	
			PreparedStatement pstmt = connection.prepareStatement(sql);)
		{	
			pstmt.setInt(1, id);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					System.out.println("{ Name: " + rs.getString("name") + " , Id: " + rs.getInt("id") + " , Age: " + rs.getInt("age") + " , Department: " + rs.getString("department") + " , Marks: " + rs.getInt("marks") + " }");
				} else {
					System.out.println("Student not found.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ========================================================== UPDATE METHOD ==========================================================
	public void updateStudent(Student student) {
		String sql = "UPDATE students SET name = ?, age = ?, department = ?, marks = ? WHERE id = ?";
		try(PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			pstmt.setString(3, student.getDepartment());
			pstmt.setInt(4, student.getMarks());
			pstmt.setInt(5, student.getId());
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Student Data Updated");
			} else {
			    System.out.println("Student not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ========================================================== DELETE METHOD ==========================================================
	public void deleteStudent(int id) {
		String sql = "DELETE FROM students\r\n"
				+ "WHERE id = ?;";
		try(PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Student Data Deleted");
			} else {
			    System.out.println("Student not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
