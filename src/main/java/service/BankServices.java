package service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.AccountsDAO;
import dao.CustomersDAO;
import dao.TransactionsDAO;
import model.Accounts;
import model.Customers;
import model.Transactions;

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
				System.out.println();
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
				System.out.println();
				CustomersDAO customersDAO = new CustomersDAO();
				customersDAO.viewCustomers();
				System.out.println();
			}
			
			//================================================== SEARCH CUSTOMER ======================================================
			else if(choice == 3) {
				System.out.println();
				System.out.println("//========================= SEARCH CUSTOMER =========================");
				System.out.println();
				int id = toInt(scanner, "Enter The Customer ID You Want To Search: ");
				CustomersDAO customersDAO = new CustomersDAO();
				customersDAO.searchCustomer(id);
				System.out.println();
			}
			
			//================================================== UPDATE CUSTOMER ======================================================
			else if(choice == 4) {
				System.out.println();
				System.out.println("//========================= UPDATE CUSTOMER =========================");
				System.out.println();
				int customer_id = toInt(scanner, "Enter The Customer ID You Want To Update: ");
				System.out.print("Enter The New Name Of Customer: ");
				String name = scanner.nextLine();
				int age = toInt(scanner, "Enter The New Age Of Customer: ");
				System.out.print("Enter The New Phone Number Of Customer: ");
				String phone = scanner.nextLine();
				System.out.print("Enter The New Address Of Customer: ");
				String address = scanner.nextLine();
				Customers customer = new Customers(customer_id, name, age, phone, address);
				CustomersDAO customersDAO = new CustomersDAO();
				customersDAO.updateCustomer(customer);
				System.out.println();
			}
			
			//================================================== DELETE CUSTOMER ======================================================
			else if(choice == 5) {
				System.out.println();
				System.out.println("//========================= DELETE CUSTOMER =========================");
				System.out.println();
				int id = toInt(scanner, "Enter The Customer ID You Want To Delete: ");
				CustomersDAO customersDAO = new CustomersDAO();
				customersDAO.deleteCustomer(id);
				System.out.println();
			}
			
			//================================================== CREATE ACCOUNT ======================================================
			else if(choice == 6) {
				System.out.println();
				System.out.println("//========================= CREATE ACCOUNT =========================");
				System.out.println();
				int acc_no = toInt(scanner, "Enter The Account Number You Want: ");
				int cus_id = toInt(scanner, "Enter Your Customer ID You Have: ");
				System.out.print("Enter The Account Type: ");
				String acc_type = scanner.nextLine();
				int balance = toInt(scanner, "Enter Your Balance Amount: ");
				Accounts account = new Accounts(acc_no, cus_id, acc_type, balance);
				AccountsDAO accountsDAO = new AccountsDAO();
				accountsDAO.addAccount(account);
				System.out.println();
			}
			
			//================================================== VIEW ACCOUNT ======================================================
			else if(choice == 7) {
				System.out.println();
				System.out.println("//========================= VIEW ACCOUNT =========================");
				System.out.println();
				int id = toInt(scanner, "Enter The Account Number You Want To Search: ");
				AccountsDAO accountsDAO = new AccountsDAO();
				accountsDAO.viewAccount(id);
				System.out.println();
			}

			//================================================== DEPOSIT MONEY ======================================================
			else if(choice == 8) {
				System.out.println();
				System.out.println("//========================= DEPOSIT MONEY =========================");
				System.out.println();
				int transaction_id = toInt(scanner, "Enter The Transactions ID You Want: ");
				int acc_no = toInt(scanner, "Enter The Account Number You Like To Deposit Money: ");
				int amount = toInt(scanner, "Enter The Amount You Want Deposit: ");
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String mysqlDate = formatter.format(date);
				Transactions transactions = new Transactions(transaction_id, acc_no, amount, mysqlDate);
				TransactionsDAO transactionsDAO = new TransactionsDAO();
				transactionsDAO.depositMoney(transactions);
			}
			
			//================================================== WITHDRAW MONEY ======================================================
			else if(choice == 9) {
				System.out.println();
				System.out.println("//========================= WITHDRAW MONEY =========================");
				System.out.println();
				int transaction_id = toInt(scanner, "Enter The Transactions ID You Want: ");
				int acc_no = toInt(scanner, "Enter The Account Number You Like To Withdraw Money: ");
				int amount = toInt(scanner, "Enter The Amount You Want Withdraw: ");
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String mysqlDate = formatter.format(date);
				Transactions transactions = new Transactions(transaction_id, acc_no, amount, mysqlDate);
				TransactionsDAO transactionsDAO = new TransactionsDAO();
				transactionsDAO.withdrawMoney(transactions);
			}
			
			//================================================== TRANSFER MONEY ======================================================
			else if(choice == 10) {
				System.out.println();
				System.out.println("//========================= TRANSFER	 MONEY =========================");
				System.out.println();
				int transaction_id = toInt(scanner, "Enter The Transactions ID You Want: ");
				int acc_no = toInt(scanner, "Enter The Account Number You Have: ");
				int amount = toInt(scanner, "Enter The Amount You Want Transfer: ");
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String mysqlDate = formatter.format(date);
				Transactions transactions = new Transactions(transaction_id, acc_no, amount, mysqlDate);
				int transaction_id2 = toInt(scanner, "Enter The Transactions ID You Want To Transfer Money: ");
				int acc_no2 = toInt(scanner, "Enter The Account Number You Like To Transfer Money: ");
				String mysqlDate2 = formatter.format(date);
				Transactions transactions2 = new Transactions(transaction_id2, acc_no2, 0, mysqlDate2);
				TransactionsDAO transactionsDAO = new TransactionsDAO();
				transactionsDAO.transferMoney(transactions, transactions2);
			}
			
			//================================================== VIEW BALANCE ======================================================
			else if(choice == 11) {
				System.out.println();
				System.out.println("//========================= VIEW BALANCE =========================");
				System.out.println();
				int id = toInt(scanner, "Enter The Account Number You Want To Check Balance : ");
				AccountsDAO accountsDAO = new AccountsDAO();
				accountsDAO.checkBalance(id);
				System.out.println();
			}
			
			//================================================== CLOSE ACCOUNT ======================================================
			else if(choice == 12) {
				System.out.println();
				System.out.println("//========================= CLOSE ACCOUNT =========================");
				System.out.println();
				int id = toInt(scanner, "Enter The Account Number You Want To Close: ");
				AccountsDAO accountsDAO = new AccountsDAO();
				accountsDAO.closeAccount(id);
				System.out.println();
			}
			
			//================================================== EXIT ======================================================
			else if(choice == 13) {
				System.out.println("Exiting Bank Management System...");
			}
		}
	}
}











