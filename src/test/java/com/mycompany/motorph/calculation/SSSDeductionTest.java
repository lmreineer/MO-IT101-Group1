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

    @Test
    public void calculateSssDeduction_ReturnsCorrectMinimumSssDeduction() {
        // Create a sample gross wage that is the minimum compensation range
        double grossWage = 3000.0;

        SSSDeduction sssDeduction = new SSSDeduction();

        double deduction = sssDeduction.calculateSssDeduction(grossWage);

        // Assert that the calculated SSS deduction is the minimum deduction value
        assertEquals(135.0, deduction);
    }

    @Test
    public void calculateSssDeduction_DoesNotExceedMaximumSssDeduction() {
        // Create a sample gross wage that is the maximum compensation range
        double grossWage = 25000.0;

        SSSDeduction sssDeduction = new SSSDeduction();

        double deduction = sssDeduction.calculateSssDeduction(grossWage);

        // Assert that the calculated SSS deduction is the maximum deduction value
        assertEquals(1125.0, deduction);
    }

    @Test
    public void calculateSssDeduction_CalculatesCorrectSssDeduction() {
        // Create a sample gross wage that is within the min and max compensation range
        double grossWage = 10000.0;

        SSSDeduction sssDeduction = new SSSDeduction();

        double deduction = sssDeduction.calculateSssDeduction(grossWage);

        // Assert that the calculated SSS deduction matches the expected value
        assertEquals(450.0, deduction);
    }
}
