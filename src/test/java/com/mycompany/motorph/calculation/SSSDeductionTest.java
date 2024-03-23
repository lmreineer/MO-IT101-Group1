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
public class SSSDeductionTest {

    // Margin of error for comparisons with double
    private static final double DELTA = 0.001;

    private static final double MINIMUM_GROSS_WAGE = 3000.0;
    private static final double MAXIMUM_GROSS_WAGE = 25000.0;
    private static final double MINIMUM_DEDUCTION = 135.0;
    private static final double MAXIMUM_DEDUCTION = 1125.0;
    private static final double STANDARD_DEDUCTION = 450.0;

    @Test
    public void calculateSssDeduction_ReturnsCorrectMinimumDeduction() {
        SSSDeduction sssDeduction = new SSSDeduction();
        double deduction = sssDeduction.calculateSssDeduction(MINIMUM_GROSS_WAGE);
        assertEquals(MINIMUM_DEDUCTION, deduction, DELTA, "SSS deduction should be correct for minimum wage");
    }

    @Test
    public void calculateSssDeduction_DoesNotExceedMaximumDeduction() {
        SSSDeduction sssDeduction = new SSSDeduction();
        double deduction = sssDeduction.calculateSssDeduction(MAXIMUM_GROSS_WAGE);
        assertEquals(MAXIMUM_DEDUCTION, deduction, DELTA, "SSS deduction should not exceed maximum for maximum wage");
    }

    @Test
    public void calculateSssDeduction_CalculatesCorrectDeductionWithinRange() {
        SSSDeduction sssDeduction = new SSSDeduction();
        double deduction = sssDeduction.calculateSssDeduction(10000.0);
        assertEquals(STANDARD_DEDUCTION, deduction, DELTA, "SSS deduction should be correct within range");
    }
}
