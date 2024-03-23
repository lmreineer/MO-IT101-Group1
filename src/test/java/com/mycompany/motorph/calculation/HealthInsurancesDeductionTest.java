/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class HealthInsurancesDeductionTest {

    // Constants for test data
    private static final double PHILHEALTH_MIN_SALARY = 10000.0;
    private static final double MAX_PAGIBIG_DEDUCTION = 100;
    // Margin of error for comparisons with double
    private static final double DELTA = 0.001;

    @Test
    public void calculatePhilHealthDeduction_CalculatesWithinExpectedRange() {
        double grossWage = PHILHEALTH_MIN_SALARY;
        HealthInsurancesDeduction healthInsurancesDeduction = new HealthInsurancesDeduction();

        double philHealthDeduction = healthInsurancesDeduction.calculatePhilHealthDeduction(grossWage);

        assertTrue(philHealthDeduction >= 150.0 && philHealthDeduction <= 900.0,
                "PhilHealth deduction should be between PHP 150.00 and PHP 900.00");
    }

    @Test
    public void calculatePagIbigDeduction_CalculatesCorrectly() {
        double grossWage = 1200.0;
        HealthInsurancesDeduction healthInsurancesDeduction = new HealthInsurancesDeduction();

        double expectedDeduction = 36.0;
        double pagIbigDeduction = healthInsurancesDeduction.calculatePagIbigDeduction(grossWage);

        assertEquals(expectedDeduction, pagIbigDeduction, DELTA,
                "Pag-IBIG deduction should be PHP 36.00 for a gross wage of PHP 1200.00");
    }

    @Test
    public void calculatePagIbigDeduction_DoesNotExceedMaximum() {
        // Create a sample gross wage above the range
        double grossWage = 1600.0;
        HealthInsurancesDeduction healthInsurancesDeduction = new HealthInsurancesDeduction();

        double pagIbigDeduction = healthInsurancesDeduction.calculatePagIbigDeduction(grossWage);

        assertTrue(pagIbigDeduction <= MAX_PAGIBIG_DEDUCTION,
                "Pag-IBIG deduction should not exceed maximum limit of PHP 100.00");
    }
}
