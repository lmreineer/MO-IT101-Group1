/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class EmployeeTest {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Test
    public void getBirthdateAsString_ReturnsString() throws ParseException {
        // Create a sample employee information including birthdate
        Employee employee = createSampleEmployee();

        // Get the birthdate as a string
        String actualBirthdateString = employee.getBirthdateAsString();
        String expectedBirthdateString = "01/01/1985";

        // Assert that the actual birthdate string matches the expected birthdate string
        assertEquals(expectedBirthdateString, actualBirthdateString);
    }

    @Test
    public void displayEmployeeInformation_DoesNotThrowExceptions() throws ParseException {
        // Create a sample employee information
        Employee employee = createSampleEmployee();

        // Assert that execution does not throw any kind of exception
        assertDoesNotThrow(() -> employee.displayEmployeeInformation());
    }

    private Employee createSampleEmployee() throws ParseException {
        Employee employee = new Employee();

        // Set a sample employee information
        employee.setEmployeeNumber(123);
        employee.setLastName("Doe");
        employee.setFirstName("John");
        employee.setBirthdate(dateFormat.parse("01/01/1985"));
        employee.setAddress("123 Main Street, Street, City");
        employee.setPhoneNumber("999-123-456");
        employee.setSssNumber("12-3456789-0");
        employee.setPhilHealthNumber("123456789012");
        employee.setTinNumber("123-456-789-012");
        employee.setPagIbigNumber("123456789012");
        employee.setStatus("Status");
        employee.setPosition("Position");
        employee.setImmediateSupervisor("Jane Doe");
        employee.setBasicSalary(90000);
        employee.setRiceSubsidy(1500);
        employee.setPhoneAllowance(2000);
        employee.setClothingAllowance(1000);
        employee.setGrossSemimonthlyRate(45000);
        employee.setHourlyRate(500.50);

        return employee;
    }
}
