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

    private static final String EMPLOYEES_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_information.txt";
    private static final int EXPECTED_EMPLOYEES_LENGTH = 34;

    @Test
    public void readEmployees_ReturnsTotalNumberOfEmployees() {
        EmployeeDataReader reader = new EmployeeDataReader();

        try {
            List<Employee> employees = reader.readEmployees(EMPLOYEES_DATA_PATH);

            assertNotNull(employees, "List of employees should not be null");
            assertEquals(EXPECTED_EMPLOYEES_LENGTH, employees.size(), "Number of employees read should match expected count");
        } catch (IOException | ParseException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void getEmployeeInfo_EmployeeDoesNotExist() {
        EmployeeDataReader reader = new EmployeeDataReader();
        int nonExistingEmployeeNumber = 123;

        try {
            Employee employee = reader.getEmployeeInfo(nonExistingEmployeeNumber, EMPLOYEES_DATA_PATH);

            assertNull(employee, "Non-existing employee should return null");
        } catch (IOException | ParseException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
