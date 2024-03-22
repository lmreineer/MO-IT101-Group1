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

    @Test
    public void calculateWithholdingTax_IsNotEligibleForWithholdingTax() {
        // Create a sample gross wage that is not eligible to get a withholding tax
        double grossWage = 20000.0;

        WithholdingTaxCalculation withholdingTaxCalculation = new WithholdingTaxCalculation(new SSSDeduction(), new HealthInsurancesDeduction());
        double withholdingTax = withholdingTaxCalculation.calculateWithholdingTax(grossWage);

        // Assert that the withholding tax calculated is zero
        assertEquals(0.0, withholdingTax);
    }

    @Test
    public void calculateWithholdingTax_IsEligibleForWithholdingTax() {
        // Create a sample gross wage that is assigned to the maximum taxable income range eligible for deductions
        double grossWage = 80000.0;

        WithholdingTaxCalculation withholdingTaxCalculation = new WithholdingTaxCalculation(new SSSDeduction(), new HealthInsurancesDeduction());
        double withholdingTax = withholdingTaxCalculation.calculateWithholdingTax(grossWage);

        // Assert that the withholding tax calculated is a thing
        assertTrue(withholdingTax > 0.0);
    }
}
