package service;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.CustomersDAO;
import model.Customers;

public class BankServices {
	public int toInt(Scanner scanner,String prompt) {
		while(true) {
			try {
				System.out.print(prompt);
				int value = scanner.nextInt();
				scanner.nextLine();
				return value;
			} catch(InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
				scanner.nextLine();
			} 
		}
	}
	
	public void start() {
		int choice = 0;
		Scanner scanner = new Scanner(System.in);
		while(choice != 13) {
			System.out.println("1. Create Customer");
			System.out.println("2. View Customers");
			System.out.println("3. Search Customer");
			System.out.println("4. Update Customer");
			System.out.println("5. Delete Customer");
			System.out.println();
			System.out.println();
			System.out.println("6. Create Account");
			System.out.println("7. View Accounts");
			System.out.println("8. Deposit Money");
			System.out.println("9. Withdraw Money");
			System.out.println("10. Transfer Money");
			System.out.println("11. Check Balance");
			System.out.println("12. Close Account   ← Deletes the account");
			System.out.println("13. Exit");
			System.out.println();
			choice = toInt(scanner,"Choose Your Option: ");
			
			//================================================== CREATE CUSTOMER ======================================================
			if(choice == 1) {
				System.out.println();
				System.out.println("//========================= CREATE CUSTOMER =========================");
				System.out.print("Enter Your Name: ");
				String name = scanner.nextLine();
				int customer_id = toInt(scanner, "Enter Your Customer ID: ");
				int age = toInt(scanner, "Enter Your Age: ");
				System.out.print("Enter Your Phone Number: ");
				String phone = scanner.nextLine();
				System.out.print("Enter Your Address: ");
				String address = scanner.nextLine();
				Customers customer = new Customers(customer_id, name, age, phone, address);
				CustomersDAO customersDAO = new CustomersDAO();
				customersDAO.addCustomer(customer);
				System.out.println();
			}
			
			//================================================== VIEW CUSTOMER ======================================================
			else if(choice == 2) {
				System.out.println();
				System.out.println("//========================= VIEW CUSTOMER =========================");
				CustomersDAO customersDAO = new CustomersDAO();
				customersDAO.viewCustomers();
				System.out.println();
			}
			
			//================================================== SEARCH CUSTOMER ======================================================
			else if(choice == 3) {
				System.out.println();
				System.out.println("//========================= SEARCH CUSTOMER =========================");
				int id = toInt(scanner, "Enter The Customer ID You Want To Search: ");
				CustomersDAO customersDAO = new CustomersDAO();
				customersDAO.searchCustomer(id);
				System.out.println();
			}
		}
	}
}











