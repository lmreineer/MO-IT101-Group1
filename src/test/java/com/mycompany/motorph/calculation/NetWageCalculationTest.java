/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class NetWageCalculationTest {

    /**
     * Test to verify that late arrival deduction is calculated correctly.
     *
     * @throws Exception If there is an error in date parsing
     */
    @Test
    public void calculateLateArrivalDeduction_CalculatesCorrectDeduction() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

        // Create an empty attendance data list
        List<String> attendanceDataList = new ArrayList<>();

        // Create a sample late arrival attendance
        attendanceDataList.add("123|John|Doe|01/01|9:11|17:00");
        // Create a sample on time arrival attendance
        attendanceDataList.add("456|Jane|Doe|01/01|08:00|17:00");

        NetWageCalculation netWageCalculation = new NetWageCalculation(
                123, "Doe", "John", "01/01/1990",
                new SSSDeduction(), new HealthInsurancesDeduction(), new WithholdingTaxCalculation(new SSSDeduction(), new HealthInsurancesDeduction())
        );

        // Create a sample date range
        DateRange dateRange = new DateRange(dateFormat.parse("01/01"), dateFormat.parse("01/01"));

        double lateArrivalDeduction = netWageCalculation.calculateLateArrivalDeduction(attendanceDataList, 123, dateRange);

        // Assert that the late arrival deduction calculated with the specified information will be 99.6
        assertEquals(99.6, lateArrivalDeduction);
    }

    /**
     * Test to verify that net wage is calculated correctly.
     */
    @Test
    public void calculateWage_CalculatesCorrectNetWage() {
        // Create sample information needed for gross wage calculation
        double hourlyRate = 10.0;
        double hoursWorked = 40.0;
        double lateArrivalDeduction = 0.0;
        NetWageCalculation netWageCalculation = new NetWageCalculation(
                123, "Doe", "John", "01/01/1990",
                new SSSDeduction(), new HealthInsurancesDeduction(), new WithholdingTaxCalculation(new SSSDeduction(), new HealthInsurancesDeduction())
        );

        double netWage = netWageCalculation.calculateWage(hourlyRate, hoursWorked, lateArrivalDeduction);

        // Assert that the calculated net wage is as expected
        assertEquals(380.0, netWage);
    }
}
