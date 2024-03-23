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
public class WithholdingTaxCalculationTest {

    // Margin of error for comparisons with double
    private static final double DELTA = 0.001;

    private static final double MAX_TAXABLE_INCOME = 80000.0;

    @Test
    public void calculateWithholdingTax_NotEligibleForWithholdingTax() {
        // Create a sample gross wage that is not eligible to get a withholding tax
        double grossWage = 20000.0;

        WithholdingTaxCalculation withholdingTaxCalculation = new WithholdingTaxCalculation(new SSSDeduction(), new HealthInsurancesDeduction());
        double withholdingTax = withholdingTaxCalculation.calculateWithholdingTax(grossWage);

        // Assert that the withholding tax calculated is zero
        assertEquals(0.0, withholdingTax, DELTA, "Withholding tax should be zero for gross wage below taxable income");
    }

    @Test
    public void calculateWithholdingTax_EligibleForWithholdingTax() {
        // Create a sample gross wage that is assigned to the maximum taxable income range eligible for deductions
        double grossWage = MAX_TAXABLE_INCOME;

        WithholdingTaxCalculation withholdingTaxCalculation = new WithholdingTaxCalculation(new SSSDeduction(), new HealthInsurancesDeduction());
        double withholdingTax = withholdingTaxCalculation.calculateWithholdingTax(grossWage);

        // Assert that the withholding tax calculated is greater than zero
        assertTrue(withholdingTax > 0.0, "Withholding tax should be greater than zero for gross wage equal to maximum taxable income");
    }
}
