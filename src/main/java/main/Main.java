package main;

import service.BankServices;

public class Main {

	public static void main(String[] args) {
		BankServices bankService = new BankServices();
		bankService.start();
	}

}
