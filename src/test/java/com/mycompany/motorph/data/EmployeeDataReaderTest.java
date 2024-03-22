/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.data;

import com.mycompany.motorph.model.Employee;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class EmployeeDataReaderTest {

    // Path to the employee data file
    private static final String EMPLOYEES_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_information.txt";
    private static final int EXPECTED_EMPLOYEES_LENGTH = 34;

    @Test
    public void readEmployees_ReturnsTotalNumberOfEmployees() throws IOException, ParseException {
        EmployeeDataReader reader = new EmployeeDataReader();

        List<Employee> employees = reader.readEmployees(EMPLOYEES_DATA_PATH);

        // Assert employees are read from the file
        assertNotNull(employees);
        // Assert the size of the list is the same as the expected total employee length
        assertEquals(EXPECTED_EMPLOYEES_LENGTH, employees.size());
    }

    @Test
    public void getEmployeeInfo_EmployeeDoesNotExist() throws IOException, ParseException {
        EmployeeDataReader reader = new EmployeeDataReader();
        int nonExistingEmployeeNumber = 123;

        Employee employee = reader.getEmployeeInfo(nonExistingEmployeeNumber, EMPLOYEES_DATA_PATH);

        // Assert that the employee does not exist in the file
        assertNull(employee);
    }
}
