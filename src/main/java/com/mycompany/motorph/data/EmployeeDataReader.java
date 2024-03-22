/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.data;

import com.mycompany.motorph.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that reads employee data from the data file.
 *
 * @author Lance1
 */
public class EmployeeDataReader {

    private static final SimpleDateFormat BIRTHDATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    // Expected total number of values per row from the data
    private static final int EXPECTED_ROW_LENGTH = 19;

    /**
     * Reads employee data from the data file and returns list of employees.
     *
     * @param filePath Path to the employee_information data file
     * @return List of employees read from the file
     * @throws IOException If I/O error happen while reading the file
     * @throws ParseException If parsing error happen while parsing
     */
    public List<Employee> readEmployees(String filePath) throws IOException, ParseException {
        List<Employee> employees = new ArrayList<>();

        // Open the file for reading
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line into employee data using "|" as delimiter
                String[] employeeData = line.split("\\|");
                // If the line has the expected length
                if (employeeData.length >= EXPECTED_ROW_LENGTH) {
                    // Create an employee object from the data and add it to the list
                    employees.add(createEmployeeFromData(employeeData));
                }
            }
        }

        // Return the list of employees
        return employees;
    }

    /**
     * Gets information for the employee with the inputted employee number.
     *
     * @param employeeNumber Employee number to search for in the data file
     * @param filePath Path to the employee_information data file
     * @return Employee object containing the information of the employee with
     * the inputted employee number. Null if no employee is found
     * @throws IOException If an I/O error occurs while reading the file
     * @throws ParseException If a parsing error occurs
     */
    public Employee getEmployeeInfo(int employeeNumber, String filePath) throws IOException, ParseException {
        // Open the file for reading
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line into employee data using "|" as delimiter
                String[] employeeData = line.split("\\|");
                // Check if the line has the expected length and matches the inputted employee number
                if (employeeData.length == EXPECTED_ROW_LENGTH && Integer.parseInt(employeeData[0]) == employeeNumber) {
                    // Create an employee object from the data and return it
                    return createEmployeeFromData(employeeData);
                }
            }
        }

        // Return null if employee is not found
        return null;
    }

    /**
     * Creates an Employee object from an array of employee data.
     *
     * @param employeeData Array containing employee data
     * @return Employee object created with the data
     * @throws ParseException If a parsing error occurs
     */
    private Employee createEmployeeFromData(String[] employeeData) throws ParseException {
        Employee employee = new Employee();
        employee.setEmployeeNumber(Integer.parseInt(employeeData[0]));
        employee.setLastName(employeeData[1]);
        employee.setFirstName(employeeData[2]);
        employee.setBirthdate(BIRTHDATE_FORMAT.parse(employeeData[3]));
        employee.setAddress(employeeData[4]);
        employee.setPhoneNumber(employeeData[5]);
        employee.setSssNumber(employeeData[6]);
        employee.setPhilHealthNumber(employeeData[7]);
        employee.setTinNumber(employeeData[8]);
        employee.setPagIbigNumber(employeeData[9]);
        employee.setStatus(employeeData[10]);
        employee.setPosition(employeeData[11]);
        employee.setImmediateSupervisor(employeeData[12]);
        employee.setBasicSalary(Double.parseDouble(employeeData[13]));
        employee.setRiceSubsidy(Double.parseDouble(employeeData[14]));
        employee.setPhoneAllowance(Double.parseDouble(employeeData[15]));
        employee.setClothingAllowance(Double.parseDouble(employeeData[16]));
        employee.setGrossSemimonthlyRate(Double.parseDouble(employeeData[17]));
        employee.setHourlyRate(Double.parseDouble(employeeData[18]));

        return employee;
    }
}
