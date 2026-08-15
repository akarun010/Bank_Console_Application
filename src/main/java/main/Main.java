package main;

import service.StudentService;

public class Main {

	public static void main(String[] args) {
		StudentService studService = new StudentService();
		studService.start();
	}
}
