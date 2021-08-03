package com.cognixia.jump.finalProject;

public class Employee{
	
	// -- Nested Enum  --
	enum Department {
	    HR, IT, FINANCE, MARKETING, PRODUCTION, SALES;
	 }
	
	private static int idCounter = 1;
	
	private int id;
	private String name;
	private Department dept;
	private int salary;
	
	public Employee(String name, Department dept, int salary) throws MinimumWageException {
		super();
		this.id = idCounter++;
		this.name = name;
		this.dept = dept;
		if(salary < 14400) {
			throw new MinimumWageException(salary);
		}
		else {
			this.salary = salary;

		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) throws MinimumWageException{
		if(salary < 14400) {
			throw new MinimumWageException(salary);
		}
		else {
			this.salary = salary;

		}
		
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + ", salary=" + salary + "]\n";
	}
	
	
	
}
