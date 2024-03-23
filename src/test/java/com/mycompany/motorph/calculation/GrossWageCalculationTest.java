/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class GrossWageCalculationTest {

    @Test
    public void calculateWage_CalculatesCorrectGrossWage() {
        // Test data
        double hourlyRate = 10.0;
        double hoursWorked = 40.0;
        double lateArrivalDeduction = 0.0;

        // Create instance of GrossWageCalculation
        GrossWageCalculation grossWageCalculation = new GrossWageCalculation(123, "Doe", "John", "01/01/1990");

        double grossWage = grossWageCalculation.calculateWage(hourlyRate, hoursWorked, lateArrivalDeduction);

        assertEquals(400.0, grossWage);
    }

    @Test
    public void displayWage_DisplaysCorrectFormatAndValues() {
        // Test data
        double hourlyRate = 10.0;
        double hoursWorked = 40.0;
        double lateArrivalDeduction = 0.0;

        // Create instance of GrossWageCalculation
        GrossWageCalculation grossWageCalculation = new GrossWageCalculation(123, "Doe", "John", "01/01/1990");

        // Capture console output when displaying the wage information
        String[] lines = captureConsoleOutput(() -> grossWageCalculation.displayWage(123, hourlyRate, hoursWorked, lateArrivalDeduction));

        assertEquals("================================", lines[0].trim());
        assertEquals("Employee #: 123", lines[1].trim());
        assertEquals("Employee Name: John Doe", lines[2].trim());
        assertEquals("Birthdate: 01/01/1990", lines[3].trim());
        assertEquals("--------------------------------", lines[4].trim());
        // Assume that CurrencyUtil is working correctly
        assertEquals("Gross Wage: PHP 400.00", lines[5].trim());
        assertEquals("================================", lines[6].trim());
    }

    @Test
    public void calculateLateArrivalDeduction_IsNotSupported() {
        // Test data
        List<String> attendanceDataList = new ArrayList<>();
        int employeeNumber = 123;
        DateRange dateRange = new DateRange(new Date(), new Date());

        // Create instance of GrossWageCalculation
        GrossWageCalculation grossWageCalculation = new GrossWageCalculation(123, "Doe", "John", "01/01/1990");

        assertEquals(0.0, grossWageCalculation.calculateLateArrivalDeduction(attendanceDataList, employeeNumber, dateRange));
    }

    // Helper method to capture console output
    private String[] captureConsoleOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        runnable.run();
        System.setOut(originalOut);
        return outputStream.toString().split(System.lineSeparator());
    }
}
