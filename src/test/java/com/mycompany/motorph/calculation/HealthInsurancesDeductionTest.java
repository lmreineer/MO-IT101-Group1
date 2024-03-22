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

    private static final double PHILHEALTH_MIN_SALARY = 10000.0;
    private static final double MAX_PAGIBIG_DEDUCTION = 100;

    @Test
    public void calculatePhilHealthDeduction_CalculatesCorrectPhilHealthDeduction() {
        // Create a sample gross wage
        double grossWage = PHILHEALTH_MIN_SALARY;
        HealthInsurancesDeduction healthInsurancesDeduction = new HealthInsurancesDeduction();

        double philHealthDeduction = healthInsurancesDeduction.calculatePhilHealthDeduction(grossWage);

        // Assert that the calculated PhilHealth deduction is within the expected range
        assertTrue(philHealthDeduction >= 150.0 && philHealthDeduction <= 900.0);
    }

    @Test
    public void calculatePagIbigDeduction_CalculatesCorrectPagIbigDeduction() {
        // Create a sample gross wage
        double grossWage = 1200.0;
        HealthInsurancesDeduction healthInsurancesDeduction = new HealthInsurancesDeduction();

        double pagIbigDeduction = healthInsurancesDeduction.calculatePagIbigDeduction(grossWage);

        // Assert that the calculated Pag-IBIG deduction matches the expected value
        assertEquals(36.0, pagIbigDeduction);
    }

    @Test
    public void calculatePagIbigDeduction_DoesNotExceedMaximumPagIbigDeduction() {
        double grossWage = 1600.0; // Sample gross wage above the range
        HealthInsurancesDeduction healthInsurancesDeduction = new HealthInsurancesDeduction();

        double pagIbigDeduction = healthInsurancesDeduction.calculatePagIbigDeduction(grossWage);

        // Assert that the calculated Pag-IBIG deduction does not exceed the maximum deduction amount
        assertTrue(pagIbigDeduction <= MAX_PAGIBIG_DEDUCTION);
    }
}
