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
    // Margin of error for comparisons with double
    private static final double DELTA = 0.001;

    @Test
    public void calculateTotalHoursWorked_ReturnsCorrectTotalHours() throws ParseException {
        List<String> attendanceDataList = new ArrayList<>();
        attendanceDataList.add("123|John|Doe|01/01|08:00|17:00");
        attendanceDataList.add("123|John|Doe|01/02|09:00|17:00");
        int employeeNumber = 123;
        DateRange dateRange = new DateRange(dateFormat.parse("01/01"), dateFormat.parse("01/02"));
        TimeCalculation timeCalculation = new TimeCalculation();

        double totalHoursWorked = timeCalculation.calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);

        assertEquals(17.0, totalHoursWorked, DELTA, "Total hours worked within the date range should be 17.0");
    }

    @Test
    public void calculateAssumedHoursWorked_ReturnsCorrectAssumedHours() throws ParseException {
        DateRange dateRange = new DateRange(dateFormat.parse("02/01"), dateFormat.parse("02/02"));
        TimeCalculation timeCalculation = new TimeCalculation();

        double assumedHoursWorked = timeCalculation.calculateAssumedHoursWorked(dateRange);

        assertEquals(18.0, assumedHoursWorked, DELTA, "Assumed hours worked within the date range should be 18.0");
    }
}
