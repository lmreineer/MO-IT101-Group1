/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

/**
 * A class that calculates and displays gross wage.
 *
 * Extends the abstract class WageCalculation.
 *
 * @author Lance
 */
public class GrossWageCalculation extends WageCalculation {

    private final int employeeNumber;
    private final String lastName;
    private final String firstName;
    private final String birthdate;

    /**
     * Constructor for GrossWageCalculation.
     *
     * @param employeeNumber Employee number
     * @param lastName Last name of the employee
     * @param firstName First name of the employee
     * @param birthdate Birthdate of the employee
     */
    public GrossWageCalculation(int employeeNumber, String lastName, String firstName, String birthdate) {
        // Initialize employee details
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }

    /**
     * Formats a currency value.
     *
     * @param value Currency value to be formatted
     * @return Formatted currency value in String
     */
    private String formatCurrency(double value) {
        // Format the value with commas for thousands separator and two decimal places
        return String.format("%,.2f", value);
    }

    /**
     * Calculates gross wage based on hourly rate and hours worked.
     *
     * @param hourlyRate Hourly rate of the employee
     * @param hoursWorked Hours worked by the employee
     * @return Calculated gross wage
     */
    @Override
    protected double calculateWage(double hourlyRate, double hoursWorked) {
        // Calculate gross wage
        return hourlyRate * hoursWorked;
    }

    /**
     * Displays the employee's gross wage with other information.
     *
     * @param employeeNumber Employee number
     * @param wage Calculated gross wage
     */
    @Override
    protected void displayWage(int employeeNumber, double wage) {
        System.out.println("================================");
        System.out.println("Employee #: " + employeeNumber);
        System.out.println("Employee Name: " + firstName + " " + lastName);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("--------------------------------");
        System.out.println("Gross Wage: " + formatCurrency(wage));
        System.out.println("================================");
    }
}
