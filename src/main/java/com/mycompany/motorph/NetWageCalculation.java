/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

/**
 * A class that calculates and displays net wage.
 *
 * Extends the abstract class WageCalculation.
 *
 * @author Lance
 */
public class NetWageCalculation extends WageCalculation {

    private final int employeeNumber;
    private final String lastName;
    private final String firstName;
    private final String birthdate;
    private final SSSDeduction sssDeduction;
    private final HealthInsurancesDeduction healthInsurancesDeduction;
    private final WithholdingTaxCalculation withholdingTaxCalculation;

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
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;

        // Initialize deductions used for net wage calculation
        this.sssDeduction = sssDeduction;
        this.healthInsurancesDeduction = healthInsurancesDeduction;
        this.withholdingTaxCalculation = withholdingTaxCalculation;
    }

    /**
     * Formats a currency value.
     *
     * @param value Currency value to be formatted
     * @return Formatted currency in String
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
    private double calculateGrossWage(double hourlyRate, double hoursWorked) {
        // Calculate gross wage
        return hourlyRate * hoursWorked;
    }

    /**
     * Calculates total deductions.
     *
     * @param grossWage Gross wage before deductions
     * @return Total deductions
     */
    private double calculateTotalDeductions(double grossWage) {
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
            return sssDeductions + philHealthDeductions + pagIbigDeductions + withholdingTax;
        } catch (Exception e) {
            // Catch an exception if an error happens during the calculation and display error message
            System.err.println("There was an error in calculating deductions: " + e.getMessage());
            // Return 0.0
            return 0.0;
        }
    }

    /**
     * Calculates net wage by subtracting total deductions from gross wage.
     *
     * @param hourlyRate Hourly rate for the employee
     * @param hoursWorked Hours worked by the employee
     * @return Calculated net wage
     */
    @Override
    protected double calculateWage(double hourlyRate, double hoursWorked) {
        // Calculate gross wage
        double grossWage = calculateGrossWage(hourlyRate, hoursWorked);

        // Calculate total deductions
        double totalDeductions = calculateTotalDeductions(grossWage);

        // Calculate net wage by subtracting total deductions from the gross wage
        return grossWage - totalDeductions;
    }

    /**
     * Displays the employee's net wage with other information.
     *
     * @param employeeNumber Employee number
     * @param wage Calculated net wage
     */
    @Override
    protected void displayWage(int employeeNumber, double wage) {
        System.out.println("================================");
        System.out.println("Employee #: " + employeeNumber);
        System.out.println("Employee Name: " + firstName + " " + lastName);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("--------------------------------");
        System.out.println("SSS Deduction: PHP " + formatCurrency(sssDeduction.calculateSssDeduction(wage)));
        System.out.println("Philhealth Deduction: PHP " + formatCurrency(healthInsurancesDeduction.calculatePhilHealthDeduction(wage)));
        System.out.println("Pag-IBIG Deduction: PHP " + formatCurrency(healthInsurancesDeduction.calculatePagIbigDeduction(wage)));
        System.out.println("Withholding Tax: PHP " + formatCurrency(withholdingTaxCalculation.calculateWithholdingTax(wage)));
        System.out.println("Total Deductions: PHP " + formatCurrency(calculateTotalDeductions(wage)));
        System.out.println("--------------------------------");
        System.out.println("Net Wage: PHP " + formatCurrency(wage));
    }
}
