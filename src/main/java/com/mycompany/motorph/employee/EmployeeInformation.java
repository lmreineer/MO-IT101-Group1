/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.employee;

import com.mycompany.motorph.model.Employee;
import com.mycompany.motorph.data.EmployeeDataReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * A class for managing employee information.
 * <p>
 * It allows displaying employee information using employee numbers, and
 * provides methods for finding employees.
 *
 * @author Lance1
 */
public class EmployeeInformation {

    // Path to the employee data file
    private static final String EMPLOYEES_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_information.txt";

    /**
     * Displays employee information with the inputted employee number.
     *
     * @param employeeNumber The employee number to search for
     * @throws IOException If an I/O error occurs while reading the file
     * @throws ParseException If a parsing error occurs
     */
    public void showEmployeeInformation(int employeeNumber) throws IOException, ParseException {
        // Create instance of EmployeeDataReader
        EmployeeDataReader employeeDataReader = new EmployeeDataReader();

        // Read the list of employees from the data file
        List<Employee> employees = employeeDataReader.readEmployees(EMPLOYEES_DATA_PATH);

        // Find the employee with the inputted employee number
        Employee foundEmployee = findEmployeeByNumber(employees, employeeNumber);

        // If employee is found with the inputted employee number
        if (foundEmployee != null) {
            // Display information of the employee
            foundEmployee.displayEmployeeInformation();

            // Else
        } else {
            // Throw exception
            throw new RuntimeException("Employee not found.");
        }
    }

    /**
     * Finds an employee by their employee number.
     *
     * @param employees The list of employees to search in
     * @param employeeNumber The employee number to search for
     * @return The found employee. If not found, return null
     */
    private Employee findEmployeeByNumber(List<Employee> employees, int employeeNumber) {
        // Loop through the list of employees
        for (Employee employee : employees) {
            // If the employee's number matches the inputted number
            if (employee.getEmployeeNumber() == employeeNumber) {
                // Return found employee
                return employee;
            }
        }

        // Return null if no matching employee is found
        return null;
    }
}
