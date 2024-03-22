/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import com.mycompany.motorph.util.CurrencyUtil;

import java.util.List;

/**
 * A class that calculates and displays gross wage.
 * <p>
 * Extends the abstract class WageCalculation.
 * <p>
 * This class calculates the gross wage based on hourly rate and hours worked,
 * and displays the employee's information along with the calculated wage
 *
 * @author Lance1
 */
public class GrossWageCalculation extends WageCalculation {

    private final String lastName;
    private final String firstName;
    private final String birthdate;

    /**
     * Constructor for GrossWageCalculation.
     *
     * @param employeeNumber The employee number
     * @param lastName The last name of the employee
     * @param firstName The first name of the employee
     * @param birthdate The birthdate of the employee
     */
    public GrossWageCalculation(int employeeNumber, String lastName, String firstName, String birthdate) {
        // Initialize employee details
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }

    /**
     * Mock-up implementation for late arrival deduction calculation, as it is
     * not supported for gross wage calculation.
     *
     * @param attendanceDataList Attendance data
     * @param employeeNumber Employee number
     * @param dateRange Date range
     * @return Always 0.0 as it is not supported for gross wage calculation
     */
    @Override
    protected double calculateLateArrivalDeduction(List<String> attendanceDataList, int employeeNumber, DateRange dateRange) {
        return 0.0;
    }

    /**
     * Calculates gross wage based on hourly rate and hours worked.
     *
     * @param hourlyRate The hourly rate of the employee
     * @param hoursWorked The hours worked by the employee
     * @param lateArrivalDeduction Late arrival deduction (not used in gross
     * wage calculation)
     * @return The calculated gross wage
     */
    @Override
    protected double calculateWage(double hourlyRate, double hoursWorked, double lateArrivalDeduction) {
        // Calculate gross wage
        return hourlyRate * hoursWorked;
    }

    /**
     * Displays the employee's gross wage along with other information.
     *
     * @param employeeNumber The employee number
     */
    @Override
    protected void displayWage(int employeeNumber, double hourlyRate, double hoursWorked, double lateArrivalDeduction) {
        // Calculate gross wage
        double grossWage = calculateWage(hourlyRate, hoursWorked, lateArrivalDeduction);

        System.out.println("================================");
        System.out.println("Employee #: " + employeeNumber);
        System.out.println("Employee Name: " + firstName + " " + lastName);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("--------------------------------");
        System.out.println("Gross Wage: PHP " + CurrencyUtil.formatCurrency(grossWage));
        System.out.println("================================");
    }
}
