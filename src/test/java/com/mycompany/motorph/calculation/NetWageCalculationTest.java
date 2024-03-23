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

    // Margin of error for comparisons with double
    private static final double DELTA = 0.001;

    @Test
    public void calculateLateArrivalDeduction_CalculatesCorrectDeduction() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

        List<String> attendanceDataList = new ArrayList<>();
        // Late arrival attendance
        attendanceDataList.add("123|John|Doe|01/01|9:11|17:00");
        // On-time arrival attendance
        attendanceDataList.add("456|Jane|Doe|01/01|08:00|17:00");

        NetWageCalculation netWageCalculation = createNetWageCalculation();

        DateRange dateRange = new DateRange(dateFormat.parse("01/01"), dateFormat.parse("01/01"));

        double lateArrivalDeduction = netWageCalculation.calculateLateArrivalDeduction(attendanceDataList, 123, dateRange);

        assertEquals(99.6, lateArrivalDeduction, DELTA, "Late arrival deduction should be 99.6");
    }

    @Test
    public void calculateWage_CalculatesCorrectNetWage() {
        double hourlyRate = 10.0;
        double hoursWorked = 40.0;
        double lateArrivalDeduction = 0.0;
        NetWageCalculation netWageCalculation = createNetWageCalculation();

        double netWage = netWageCalculation.calculateWage(hourlyRate, hoursWorked, lateArrivalDeduction);

        assertEquals(115.0, netWage, DELTA, "Net wage should be 115.0");
    }

    private NetWageCalculation createNetWageCalculation() {
        return new NetWageCalculation(
                123, "Doe", "John", "01/01/1990",
                new SSSDeduction(), new HealthInsurancesDeduction(), new WithholdingTaxCalculation(new SSSDeduction(), new HealthInsurancesDeduction())
        );
    }
}
