package com.cognixia.jump.finalProject;

import com.cognixia.jump.finalProject.Employee.Department;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class EmployeeDriver {

	public static void main(String[] args) {
		// Create a record of employees		)
		List<Employee> employees = new ArrayList<Employee>();
		try {
			employees.add(new Employee("Doris", Department.PRODUCTION, 80000));
			employees.add(new Employee("Sydney", Department.IT, 120000));
			employees.add(new Employee("John", Department.HR, 95000));
			employees.add(new Employee("Doris", Department.FINANCE, 87000));
			employees.add(new Employee("Alec", Department.IT, 120000));
			employees.add(new Employee("Olivia", Department.MARKETING, 85000));
			employees.add(new Employee("Lucas", Department.SALES, 100000));
			employees.add(new Employee("Alemia", Department.IT, 130000));
			employees.add(new Employee("Vincet", Department.HR, 90000));
			employees.add(new Employee("Sophia", Department.FINANCE, 100000));
			employees.add(new Employee("John", Department.IT, 130000));
		}catch(MinimumWageException e) {
			e.getMessage();
		}
		
		// -- File IO --
		File file = new File("resources/employees.txt");
		try {	
			file.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		performWriteIO(employees, file);
		performReadIO(file);
		int salary;
		
		try {
			int choice = 0;
			char ans;
			Scanner input;
			
			do {
				input = new Scanner(System.in);
				System.out.println("\nPlease choose one of the options below: ");
				System.out.println("\n1. Add employee");
				System.out.println("\n2. Remove employee");
				System.out.println("\n3. List employees");
				System.out.println("\n4. Update an employee");
				System.out.println("\n5. Sort names of employee");
				System.out.println("\n6. Keep track of departments");
				choice = input.nextInt();
				
				switch(choice) {
					case 1: 
						System.out.println("\nEnter name: ");
						String name = input.next();
						System.out.println("\nEnter department. Please enter numbers only.\n"
						+ "	 [HR = 0, IT = 1, FINANCE = 2, MARKETING = 3, PRODUCTION = 4, SALE = 5]\n");
						int dept = input.nextInt();
						System.out.println("\nEnter salary: ");
						salary = input.nextInt();
						employees.add(new Employee(name, Department.values()[dept], salary));
						break;
					case 2:
						System.out.println("\nEnter the id of employee you want to remove: ");
						int id = input.nextInt();
						employees.remove(id-1); 
						break;
					case 3:
						System.out.println("\nList employees: ");
						for(Employee employee:employees) {
							System.out.println(employee.toString());
						}
						break;
					case 4:
						System.out.println("\nUpdate an employee: ");
						System.out.println("\nEnter the id of employee you want to update: ");
						int Secid = input.nextInt();
						System.out.println("\nEnter new department. Please enter number only.\n"
							+ "	 [HR = 0, IT = 1, FINANCE = 2, MARKETING = 3, PRODUCTION = 4, SALE = 5]\n");
						int Ndept = input.nextInt();
						System.out.println("\nEnter new salary: ");
						salary = input.nextInt();
						employees.get(Secid-1).setDept(Department.values()[Ndept]);
						employees.get(Secid-1).setSalary(salary);
						break;
					case 5:
						// --- Stream ---
						System.out.println("\nSort names of employees: ");
						System.out.println("\nSORT NAMES IN ASCENDING ORDER\n");
						employees.stream()
								.sorted(Comparator.comparing(Employee::getName))
								.forEach(System.out::println);
						break;
					
					case 6: 
						// -- Stream: Keep track of all departments and which employees are within them --
						Map<Department, List<Employee>> departments = employees.stream()
														.collect(Collectors.groupingBy(Employee::getDept));
						
						System.out.println("\nDEPARTMENTS\n");
						departments.forEach((k, v) -> {
							System.out.println("\n" + k + ": ");
							v.forEach(EmployeeDriver::printName);
							
						});
						break;
						
					default:
						System.out.println("Please enter only integers");
					}
					
					System.out.println("\nContinue? Y or N? ");
					ans = input.next().charAt(0);
			} while ((ans == 'y') || (ans == 'Y'));
			input.close();
			performWriteIO(employees, file);
			performReadIO(file);
		}catch(MinimumWageException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter an integer");
		} 
			
	}
	public static void appendToFile(BufferedWriter buffWriter, String str) throws IOException{
		buffWriter.append(str);
	}
	public static void printName(Employee e) {
		
		System.out.println("Name: " + e.getName() );
		
	}
	public static void performWriteIO(List<Employee> employees, File file) {
		// Write to a text file
				FileWriter writer = null;
				BufferedWriter buffWriter = null;
				try {
					writer = new FileWriter(file);
					buffWriter = new BufferedWriter(writer);
					// helper methods
					for(Employee employee:employees) {
						appendToFile(buffWriter, employee.toString());
					}

				}catch(IOException e){
					e.printStackTrace();
				}finally {
					
					if(buffWriter != null) {
						try {
							buffWriter.close();
						} catch(IOException e) {
							e.printStackTrace();
						}	
					}
					
					if(writer != null ) {
						try {
							writer.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
					}
				}
				
	}
	public static void performReadIO(File file) {
		// Read from employee.txt
				FileReader reader = null;
				BufferedReader buffReader = null;
				
				try {
					
					reader = new FileReader(file);
					buffReader = new BufferedReader(reader);
					
					System.out.println("** CONTENTS of 'employees.txt **");
					
					String line;
					
					while ( (line = buffReader.readLine()) != null ) {
						System.out.println(line);
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
					try {
						reader.close();
						buffReader.close();
						System.out.println("SUCCESS: Closed both FileReader & BufferedReader");
						System.out.println("** End content of 'employees.txt'**\n");
					} catch(IOException e) {
						System.out.println("ERR: Failed to close FileReader & BufferedReader");
					}
					
				}
	}
}
