/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import com.mycompany.motorph.util.CurrencyUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A class that calculates and displays net wage.
 * <p>
 * Extends the abstract class WageCalculation.
 * <p>
 * This class calculates the net wage by subtracting total deductions from gross
 * wage and late arrival deduction and displays the employee's information along
 * with the calculated wage
 *
 * @author Lance1
 */
public class NetWageCalculation extends WageCalculation {

    private final String lastName;
    private final String firstName;
    private final String birthdate;
    private final SSSDeduction sssDeduction;
    private final HealthInsurancesDeduction healthInsurancesDeduction;
    private final WithholdingTaxCalculation withholdingTaxCalculation;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");

    private static final int ATTENDANCE_DATA_EXPECTED_LENGTH = 6;
    private static final double LATE_ARRIVAL_DEDUCTION_PER_MINUTE = 1.66;
    private static final int LATE_HOUR_START = 8;
    private static final int LATE_MINUTE_START = 11;
    private static final int MINUTES_IN_HOUR = 60;

    /**
     * Constructor for NetWageCalculation.
     *
     * @param employeeNumber Employee number
     * @param lastName Last name of the employee
     * @param firstName First name of the employee
     * @param birthdate Birthdate of the employee
     * @param sssDeduction SSS deduction instance
     * @param healthInsurancesDeduction Health insurances deduction instance
     * @param withholdingTaxCalculation Withholding tax calculation instance
     */
    public NetWageCalculation(int employeeNumber, String lastName, String firstName, String birthdate,
            SSSDeduction sssDeduction, HealthInsurancesDeduction healthInsurancesDeduction,
            WithholdingTaxCalculation withholdingTaxCalculation) {
        // Initialize employee details
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        // Initialize dependencies
        this.sssDeduction = sssDeduction;
        this.healthInsurancesDeduction = healthInsurancesDeduction;
        this.withholdingTaxCalculation = withholdingTaxCalculation;
    }

    /**
     * Calculates late arrival deduction for an employee.
     *
     * @param attendanceDataList Attendance data
     * @param employeeNumber Employee number
     * @param dateRange Date range
     * @return Late arrival deduction amount
     * @throws ParseException If a date parsing error occurs
     */
    @Override
    protected double calculateLateArrivalDeduction(List<String> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        double totalLateArrivalDeduction = 0.0;

        // Iterate through each line of attendance data
        for (String attendanceLine : attendanceDataList) {
            // Split the line into attendance data using "|" as delimiter
            String[] attendanceData = attendanceLine.split("\\|");

            // If the line matches the expected format and employee number
            if (attendanceData.length == ATTENDANCE_DATA_EXPECTED_LENGTH && Integer.parseInt(attendanceData[0]) == employeeNumber) {
                // Parse attendance date, time in, and time out from the data
                Date attendanceDate = DATE_FORMAT.parse(attendanceData[3]);
                Date attendanceTimeIn = TIME_FORMAT.parse(attendanceData[4]);

                // If the attendance date is within the inputted date range
                if (dateRange.isWithinDateRange(attendanceDate) && arrivedLate(attendanceTimeIn)) {
                    // Calculate late arrival duration in minutes
                    int lateArrivalMinutes = calculateLateArrivalMinutes(attendanceTimeIn);
                    // Calculate late arrival deduction
                    double lateArrivalDeduction = lateArrivalMinutes * LATE_ARRIVAL_DEDUCTION_PER_MINUTE;
                    // Add late arrival deduction to the total
                    totalLateArrivalDeduction += lateArrivalDeduction;
                }
            }
        }
        return totalLateArrivalDeduction;
    }

    /**
     * Calculates net wage by subtracting total deductions from gross wage.
     *
     * @param hourlyRate Hourly rate for the employee
     * @param hoursWorked Hours worked by the employee
     * @return Calculated net wage
     */
    @Override
    protected double calculateWage(double hourlyRate, double hoursWorked, double lateArrivalDeduction) {
        // Calculate gross wage
        double grossWage = hourlyRate * hoursWorked;

        // Calculate total deductions
        double totalDeductions = calculateTotalDeductions(grossWage, lateArrivalDeduction);

        // Calculate net wage by subtracting total deductions from the gross wage
        return grossWage - totalDeductions;
    }

    /**
     * Displays the employee's net wage with other information.
     *
     * @param employeeNumber Employee number
     */
    @Override
    protected void displayWage(int employeeNumber, double hourlyRate, double hoursWorked, double lateArrivalDeduction) {
        // Calculate gross wage
        double grossWage = hourlyRate * hoursWorked;
        // Calculate net wage
        double netWage = calculateWage(hourlyRate, hoursWorked, lateArrivalDeduction);

        System.out.println("================================");
        System.out.println("Employee #: " + employeeNumber);
        System.out.println("Employee Name: " + firstName + " " + lastName);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("--------------------------------");
        System.out.println("Gross Wage: PHP " + CurrencyUtil.formatCurrency(grossWage));
        System.out.println("SSS Deduction: PHP " + CurrencyUtil.formatCurrency(sssDeduction.calculateSssDeduction(grossWage)));
        System.out.println("PhilHealth Deduction: PHP " + CurrencyUtil.formatCurrency(healthInsurancesDeduction.calculatePhilHealthDeduction(grossWage)));
        System.out.println("Pag-IBIG Deduction: PHP " + CurrencyUtil.formatCurrency(healthInsurancesDeduction.calculatePagIbigDeduction(grossWage)));
        System.out.println("Withholding Tax: PHP " + CurrencyUtil.formatCurrency(withholdingTaxCalculation.calculateWithholdingTax(grossWage)));
        System.out.println("Late Arrival Deduction: PHP " + CurrencyUtil.formatCurrency(lateArrivalDeduction));
        System.out.println("Total Deductions: PHP " + CurrencyUtil.formatCurrency(calculateTotalDeductions(grossWage, lateArrivalDeduction)));
        System.out.println("--------------------------------");
        System.out.println("Net Wage: PHP " + CurrencyUtil.formatCurrency(netWage));
        System.out.println("================================");
    }

    /**
     * Checks if the given time indicates a late arrival for the employee.
     *
     * @param timeIn Time of arrival
     * @return True if the arrival is considered late. Otherwise, false
     */
    private boolean arrivedLate(Date timeIn) {
        // Create a Calendar instance and set it to the specified arrival time
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeIn);

        // Extract the hour and minute from the arrival time
        int arrivalHour = cal.get(Calendar.HOUR_OF_DAY);
        int arrivalMinute = cal.get(Calendar.MINUTE);

        // Check if the arrival hour is after 8 AM, or if it's 8 AM and the minutes are 11 or later
        return arrivalHour > LATE_HOUR_START || (arrivalHour == LATE_HOUR_START && arrivalMinute >= LATE_MINUTE_START);
    }

    /**
     * Calculates the duration of late arrival in minutes based on the time in
     * time.
     *
     * @param timeIn Time of arrival
     * @return Duration of late arrival in minutes
     */
    private int calculateLateArrivalMinutes(Date timeIn) {
        // Create a Calendar instance and set it to the specified arrival time
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeIn);

        // Extract the hour and minute from the arrival time
        int arrivalHour = cal.get(Calendar.HOUR_OF_DAY);
        int arrivalMinute = cal.get(Calendar.MINUTE);

        // Get the total minutes of late arrival by calculating the difference from the late start time
        int lateArrivalMinutes = (arrivalHour - LATE_HOUR_START) * MINUTES_IN_HOUR;
        lateArrivalMinutes += arrivalMinute - (LATE_MINUTE_START);
        return lateArrivalMinutes;
    }

    /**
     * Calculates total deductions.
     *
     * @param grossWage Gross wage before deductions
     * @return Total deductions
     */
    private double calculateTotalDeductions(double grossWage, double lateArrivalDeduction) {
        try {
            // Calculate SSS deductions
            double sssDeductions = sssDeduction.calculateSssDeduction(grossWage);

            // Calculate PhilHealth deductions
            double philHealthDeductions = healthInsurancesDeduction.calculatePhilHealthDeduction(grossWage);

            // Calculate Pag-IBIG deductions
            double pagIbigDeductions = healthInsurancesDeduction.calculatePagIbigDeduction(grossWage);

            // Calculate withholding tax
            double withholdingTax = withholdingTaxCalculation.calculateWithholdingTax(grossWage);

            // Return the sum of all deductions
            return sssDeductions + philHealthDeductions + pagIbigDeductions + withholdingTax + lateArrivalDeduction;
        } catch (Exception e) {
            // Catch an exception if an error happens during the calculation and display error message
            System.err.println("There was an error in calculating deductions: " + e.getMessage());
            // Return 0.0
            return 0.0;
        }
    }
}
