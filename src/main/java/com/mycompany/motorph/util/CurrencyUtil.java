/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.util;

/**
 * A utility class for formatting currency values.
 *
 * @author Lance1
 */
public class CurrencyUtil {

    /**
     * Formats a currency value.
     *
     * @param currencyValue Currency value to be formatted
     * @return Formatted currency as a String
     */
    public static String formatCurrency(double currencyValue) {
        // Format the value with commas for thousands separator and two decimal places
        return String.format("%,.2f", currencyValue);
    }
}
