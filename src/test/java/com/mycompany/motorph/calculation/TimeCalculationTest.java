/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class TimeCalculationTest {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

    @Test
    public void calculateTotalHoursWorked_ReturnsCorrectTotalHours() throws ParseException {
        // Create an empty attendance data list
        List<String> attendanceDataList = new ArrayList<>();

        // Create a sample two days straight attendance with a total of 17 hours of work
        attendanceDataList.add("123|John|Doe|01/01|08:00|17:00");
        attendanceDataList.add("123|John|Doe|01/02|09:00|17:00");

        int employeeNumber = 123;

        // Select John Doe's attendance date range
        DateRange dateRange = new DateRange(dateFormat.parse("01/01"), dateFormat.parse("01/02"));

        TimeCalculation timeCalculation = new TimeCalculation();

        double totalHoursWorked = timeCalculation.calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);

        // Assert that the total hours worked within the date range is 17
        assertEquals(17.0, totalHoursWorked);
    }

    @Test
    public void calculateAssumedHoursWorked_ReturnsCorrectAssumedHours() throws ParseException {
        // Create a random date range of 2 days
        DateRange dateRange = new DateRange(dateFormat.parse("02/01"), dateFormat.parse("02/02"));
        TimeCalculation timeCalculation = new TimeCalculation();

        double assumedHoursWorked = timeCalculation.calculateAssumedHoursWorked(dateRange);

        // Assert that the total hours worked assumed is 18 hours
        assertEquals(18.0, assumedHoursWorked);
    }
}
