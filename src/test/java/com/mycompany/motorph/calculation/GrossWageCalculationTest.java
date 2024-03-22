/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import com.mycompany.motorph.util.CurrencyUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class GrossWageCalculationTest {

    /**
     * Test to verify that the gross wage is calculated correctly.
     */
    @Test
    public void calculateWage_CalculatesCorrectGrossWage() {
        // Create sample information needed for gross wage calculation
        double hourlyRate = 10.0;
        double hoursWorked = 40.0;
        double lateArrivalDeduction = 0.0;

        // Create instance of GrossWageCalculation with sample employee information
        GrossWageCalculation grossWageCalculation = new GrossWageCalculation(123, "Doe", "John", "01/01/1990");

        double grossWage = grossWageCalculation.calculateWage(hourlyRate, hoursWorked, lateArrivalDeduction);

        // Assert that the calculated gross wage is as expected
        assertEquals(400.0, grossWage);
    }

    /**
     * Test to verify that the displayed wage information has the correct format
     * and values.
     */
    @Test
    public void displayWage_DisplaysCorrectFormatAndValues() {
        // Create sample information needed for gross wage calculation
        double hourlyRate = 10.0;
        double hoursWorked = 40.0;
        double lateArrivalDeduction = 0.0;

        // Create an instance of GrossWageCalculation with a sample employee information
        GrossWageCalculation grossWageCalculation = new GrossWageCalculation(123, "Doe", "John", "01/01/1990");
        double expectedGrossWage = hourlyRate * hoursWorked;

        // Capture console output when displaying the wage information
        String[] lines = captureConsoleOutput(() -> grossWageCalculation.displayWage(123, hourlyRate, hoursWorked, lateArrivalDeduction));

        // Assert that the displayed wage information matches the expected format and values
        assertEquals("================================", lines[0].trim());
        assertEquals("Employee #: 123", lines[1].trim());
        assertEquals("Employee Name: John Doe", lines[2].trim());
        assertEquals("Birthdate: 01/01/1990", lines[3].trim());
        assertEquals("--------------------------------", lines[4].trim());
        assertEquals("Gross Wage: PHP " + CurrencyUtil.formatCurrency(expectedGrossWage), lines[5].trim());
        assertEquals("================================", lines[6].trim());
    }

    /**
     * Test to verify that late arrival deduction is not supported for gross
     * wage calculation.
     *
     * @throws ParseException If parsing error happen while parsing
     */
    @Test
    public void calculateLateArrivalDeduction_IsNotSupported() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

        // Create an empty attendance data list
        List<String> attendanceDataList = new ArrayList<>();

        // Create a sample employee number
        int employeeNumber = 123;

        // Create date range with sample start and end dates
        DateRange dateRange = new DateRange(dateFormat.parse("01/10"), dateFormat.parse("01/15"));

        // Create an instance of GrossWageCalculation with a sample employee information
        GrossWageCalculation grossWageCalculation = new GrossWageCalculation(123, "Doe", "John", "01/01/1990");

        // Assert that late arrival deduction is always 0.0 for gross wage calculation
        assertEquals(0.0, grossWageCalculation.calculateLateArrivalDeduction(attendanceDataList, employeeNumber, dateRange));
    }

    // Helper method to capture console output
    private String[] captureConsoleOutput(Runnable runnable) {
        // Create ByteArrayOutputStream to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Replace System.out with outputStream for capturing output
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Execute the provided Runnable (e.g., displayWage) to generate output
        runnable.run();

        // Restore original System.out
        System.setOut(originalOut);

        // Split captured output by newline character and return as array of strings
        return outputStream.toString().split(System.lineSeparator());
    }
}
