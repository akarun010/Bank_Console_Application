package model;

public class Student {
	
	// ========================================================== FIELDS ==========================================================
	private int id;
	private String name;
	private int age;
	private String department;
	private int marks;
	
	// ========================================================== CONSTRUCTOR ==========================================================
	public Student(String name, int age, String department, int marks){
		
		if(age <= 0 || age > 120) {
			throw new IllegalArgumentException("Age Must Be Valid...");
		} else {
			this.age = age;
		}
		if(marks < 0 || marks > 100) {
			throw new IllegalArgumentException("Marks Out Of Scope...");
		} else {
			this.marks = marks;
		}
		
		this.name = name;
		this.department = department;		
	}
	
	// ========================================================== GETTERS ==========================================================
	public int getId() {
		return this.id;
	}
	
	public int getMarks() {
		return this.marks;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
	// ========================================================== SETTERS ==========================================================
	public void setId(int id) {
		this.id = id;
	}
	
	public void setMarks(int marks) {
		if(marks < 0 || marks > 100) {
			throw new IllegalArgumentException("Marks Out Of Scope...");
		} 
		this.marks = marks;
	}
	
	public void setAge(int age) {
		if(age <= 0 || age > 120) {
			throw new IllegalArgumentException("Age Must Be Valid...");
		}
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDepartment(String department) {
	   this.department = department;
	}
	
	// ========================================================== OVERRIDE METHODS ==========================================================
	@Override
	public String toString() {
		return "Student{id= " + id + "," + "name= " + name + "," + "age= " + age + "," + "department= " + department + "," + "marks= " + marks + "}";
	}
}
