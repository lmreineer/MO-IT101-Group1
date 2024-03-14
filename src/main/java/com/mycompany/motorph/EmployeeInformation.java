/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * A class for managing employee information.
 */
public class EmployeeInformation {

    private static final String EMPLOYEES_DATA_PATH = "/home/lance/projects/java/MotorPH/src/main/resources/data/employee_information";

    /**
     * Displays employee information with the inputted employee number.
     *
     * @param employeeNumber Employee number inputted
     * @throws IOException If an I/O error happen
     * @throws ParseException If a parsing error happen
     */
    public void showEmployeeInformation(int employeeNumber) throws IOException, ParseException {
        // Create instance of EmployeeDataReader
        EmployeeDataReader employeeDataReader = new EmployeeDataReader();

        // Read the list of employees from the data file
        List<Employee> employees = employeeDataReader.readEmployees(EMPLOYEES_DATA_PATH);

        // Find the employee with the inputted employee number
        Employee foundEmployee = findEmployeeByNumber(employees, employeeNumber);

        // If employee is found
        if (foundEmployee != null) {
            // Display information of the employee 
            foundEmployee.displayEmployeeInformation();

            // Else
        } else {
            // Print employee is not found
            System.out.println("Employee not found.");
        }
    }

    /**
     * Finds an employee using inputted employee number.
     *
     * @param employees List of employees
     * @param employeeNumber Inputted employee number to search for in the data
     * file
     * @return Found employee. If not found, return null
     */
    private Employee findEmployeeByNumber(List<Employee> employees, int employeeNumber) {
        // Loop/iterate through the list of employees
        for (Employee employee : employees) {
            // If the employee's employee number from the data file matches the inputted employee number by the user
            if (employee.getEmployeeNumber() == employeeNumber) {
                // Return found employee
                return employee;
            }
        }
        // Return null if employee is not found
        return null;
    }
}
