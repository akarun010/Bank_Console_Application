package service;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.StudentDAO;
import model.Student;

public class StudentService {
	private int readInt(Scanner scanner,String message) {
		while(true) {
			System.out.print(message);
			try {
				int value = scanner.nextInt();
				scanner.nextLine();
				return value;
			} catch(InputMismatchException e) {
				System.out.println("Please enter a valid number.");
				scanner.nextLine();
			}
		}
	}
	public void start() {
		
		// ========================================================== FIELDS ==========================================================
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		StudentDAO studentDao = new StudentDAO();
		
		// ========================================================== MAIN FUNCTION ==========================================================
		while(true) {
			System.out.println("===== Student Management System =====");
			System.out.println("1. Add Student");
			System.out.println("2. View Students");
			System.out.println("3. Search Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Exit");
			System.out.println();
			option = readInt(scanner,"Choose Your Option: ");
			
			// ========================================================== ADD STUDENT ==========================================================
			if(option == 1) {
				System.out.println("===== Add Student =====");
				System.out.print("Enter The Name Of The Student: ");
				String name = scanner.nextLine();
				System.out.print("Enter The Department Of The Student: ");
				String department = scanner.nextLine();
				int age = readInt(scanner, "Enter The Age Of The Student: ");
				int marks = readInt(scanner, "Enter The Marks Of The Student: ");
				try {
					Student student = new Student(name, age, department, marks);
					studentDao.addStudent(student);
				} catch(IllegalArgumentException e) {
					System.err.println(e.getMessage());
				}
			}
			
			// ========================================================== VIEW STUDENT ==========================================================
			else if(option == 2) {
				System.out.println("===== View Students =====");
				studentDao.viewStudents();
			}
			
			// ========================================================== SEARCH STUDENT ==========================================================
			else if(option == 3) {
				System.out.println("===== Search Student =====");
				int id = readInt(scanner, "Enter The ID Of Student You Want To Search: ");
				studentDao.searchStudent(id);
			}
			
			// ========================================================== UPDATE STUDENT ==========================================================
			else if(option == 4) {
				System.out.println("===== Update Student =====");
				System.out.print("Enter The Name Of The Student You Want To Update: ");
				String name = scanner.nextLine();
				System.out.print("Enter The Department Of The Student You Want To Updatet: ");
				String department = scanner.nextLine();
				int age = readInt(scanner, "Enter The Age Of The Student You Want To Update: ");
				int marks = readInt(scanner, "Enter The Marks Of The Student You Want To Update: ");
				int id = readInt(scanner, "Enter The ID Of The Student You Want To Update: ");
				try {
					Student student = new Student(name, age, department, marks);
					studentDao.updateStudent(student,id);
				} catch(IllegalArgumentException e) {
					System.err.println(e.getMessage());
				}
			}
			
			// ========================================================== DELETE STUDENT ==========================================================
			else if(option == 5) {
				System.out.println("===== Delete Student =====");
				int id = readInt(scanner, "Enter The ID Of Student You Want To Delete: ");
				studentDao.deleteStudent(id);	
			}
			
			// ========================================================== EXIT ==========================================================
			if(option == 6) {
				System.out.println("Exiting Student Management System...");
				break;
			}
			else if(option > 6 || option < 1) {
				System.out.println("Invalid Option");
			}
		}
	}
}






