/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class CurrencyUtilTest {

    @Test
    void testCurrencyFormattingWithPositiveValue() {
        double currencyValue = 1234.567;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("1,234.57", formattedCurrency, "Formatting positive value should produce correct result");
    }

    @Test
    void testCurrencyFormattingWithNegativeValue() {
        double currencyValue = -5678.901;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("-5,678.90", formattedCurrency, "Formatting negative value should produce correct result");
    }

    @Test
    void testCurrencyFormattingWithZeroValue() {
        double currencyValue = 0.0;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("0.00", formattedCurrency, "Formatting zero value should produce correct result");
    }

    @Test
    void testCurrencyFormattingWithLargeValue() {
        double currencyValue = 1234567890.123;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("1,234,567,890.12", formattedCurrency, "Formatting large value should produce correct result");
    }

    @Test
    void testCurrencyFormattingWithSmallValue() {
        double currencyValue = 0.12345;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("0.12", formattedCurrency, "Formatting small value should produce correct result");
    }
}
