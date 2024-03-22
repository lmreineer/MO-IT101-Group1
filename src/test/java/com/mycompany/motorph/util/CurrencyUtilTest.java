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
class CurrencyUtilTest {

    @Test
    void testCurrencyFormattingWithPositiveValue() {
        double currencyValue = 1234.567;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("1,234.57", formattedCurrency);
    }

    @Test
    void testCurrencyFormattingWithNegativeValue() {
        double currencyValue = -5678.901;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("-5,678.90", formattedCurrency);
    }

    @Test
    void testCurrencyFormattingWithZeroValue() {
        double currencyValue = 0.0;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("0.00", formattedCurrency);
    }

    @Test
    void testCurrencyFormattingWithLargeValue() {
        double currencyValue = 12345678.90;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("12,345,678.90", formattedCurrency);
    }

    @Test
    void testCurrencyFormattingWithSmallValue() {
        double currencyValue = 0.12345;
        String formattedCurrency = CurrencyUtil.formatCurrency(currencyValue);
        assertEquals("0.12", formattedCurrency);
    }
}
