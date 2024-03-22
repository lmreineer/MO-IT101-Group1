/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

/**
 * A class that calculates Pag-IBIG and PhilHealth deductions/contributions.
 *
 * @author Lance1
 */
public class HealthInsurancesDeduction {

    // Constants for PhilHealth calculation
    private static final double MIN_PHILHEALTH_DEDUCTION = 300;
    private static final double MAX_PHILHEALTH_DEDUCTION = 1800;
    private static final double PHILHEALTH_EMPLOYEE_SHARE = 0.50;
    private static final double PHILHEALTH_MIN_SALARY = 10000;
    private static final double PHILHEALTH_MAX_SALARY = 60000;
    private static final double PHILHEALTH_PREMIUM_RATE = 0.03;

    // Constants for Pag-IBIG calculation
    private static final double PAGIBIG_MIN_SALARY = 1000;
    private static final double PAGIBIG_MAX_SALARY = 1500;
    private static final double PAGIBIG_RATE_1000_TO_1500 = 0.03;
    private static final double PAGIBIG_RATE_ABOVE_1500 = 0.04;
    private static final double MAX_PAGIBIG_DEDUCTION = 100;

    /**
     * Calculates the PhilHealth deduction.
     *
     * @param grossWage The employee's gross wage
     * @return The calculated PhilHealth deduction amount
     */
    double calculatePhilHealthDeduction(double grossWage) {
        // Calculate monthly premium
        double monthlyPremium = calculatePhilHealthPremium(grossWage);
        // Calculate employee share as 50% of the monthly premium
        return monthlyPremium * PHILHEALTH_EMPLOYEE_SHARE;
    }

    /**
     * Calculates the Pag-IBIG deduction.
     *
     * @param grossWage The employee's gross wage
     * @return The calculated Pag-IBIG deduction amount
     */
    double calculatePagIbigDeduction(double grossWage) {
        double pagIbigDeduction = 0;
        // If the gross wage falls within the minimum salary range to be eligible for contributing
        if (grossWage >= PAGIBIG_MIN_SALARY && grossWage <= PAGIBIG_MAX_SALARY) {
            // Calculate Pag-IBIG deduction by getting 3% of the gross wage
            pagIbigDeduction = grossWage * PAGIBIG_RATE_1000_TO_1500;

            // Else if it is over 1500
        } else if (grossWage > PAGIBIG_MAX_SALARY) {
            // Calculate Pag-IBIG deduction by getting 4% of the gross wage
            double totalContribution = grossWage * PAGIBIG_RATE_ABOVE_1500;
            // Limit the contribution/deduction to the maximum contribution amount of 100
            pagIbigDeduction = Math.min(totalContribution, MAX_PAGIBIG_DEDUCTION);
        }
        return pagIbigDeduction;
    }

    /**
     * Calculates PhilHealth monthly premium.
     *
     * @param grossWage The employee's gross wage
     * @return The PhilHealth premium amount
     */
    private double calculatePhilHealthPremium(double grossWage) {
        // If the gross wage is less than 10000
        if (grossWage < PHILHEALTH_MIN_SALARY) {
            // Return the minimum PhilHealth deduction as default
            return MIN_PHILHEALTH_DEDUCTION;
        } // Else If the gross wage is more than 60000        
        else if (grossWage > PHILHEALTH_MAX_SALARY) {
            // Return the maximum PhilHealth deduction as default
            return MAX_PHILHEALTH_DEDUCTION;
        } // Else
        else {
            // Return monthly premium as 3% of the gross wage
            return grossWage * PHILHEALTH_PREMIUM_RATE;
        }
    }
}
