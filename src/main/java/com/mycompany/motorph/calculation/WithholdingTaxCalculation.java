/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

/**
 * A class that calculates withholding tax using gross wage and deductions.
 *
 * @author Lance1
 */
public class WithholdingTaxCalculation {

    private final SSSDeduction sssDeduction;
    private final HealthInsurancesDeduction healthInsuranceDeduction;

    /**
     * Constructor for WithholdingTaxCalculation.
     *
     * @param sssDeduction SSS deduction calculator
     * @param healthInsuranceDeduction Health insurances deduction instance
     */
    public WithholdingTaxCalculation(SSSDeduction sssDeduction, HealthInsurancesDeduction healthInsuranceDeduction) {
        // Initialize deductions
        this.sssDeduction = sssDeduction;
        this.healthInsuranceDeduction = healthInsuranceDeduction;
    }

    /**
     * Calculates the withholding tax based on the gross wage and monthly
     * contributions/deduction.
     *
     * @param grossWage Employee's gross wage
     * @return Withholding tax
     */
    double calculateWithholdingTax(double grossWage) {
        // Calculate the taxable income by subtracting gross wage from monthly contributions/deductions
        double taxableIncome = grossWage - calculateMonthlyContributions(grossWage);

        // Determine the excess value and additional rate in percentage
        double excessValue = determineExcessValue(taxableIncome);
        double additionalRateInPercentage = determineAdditionalRateInPercentage(taxableIncome);

        double withholdingTax;

        // Calculate withholding tax based on excess value and additional rate
        if (excessValue == 0 || additionalRateInPercentage == 0) {
            withholdingTax = 0;
        } else {
            withholdingTax = (taxableIncome - excessValue) * additionalRateInPercentage;
        }

        return withholdingTax;
    }

    /**
     * Calculates the total of SSS, PhilHealth, and Pag-IBIG
     * contributions/deduction.
     *
     * @param grossWage Employee's gross wage
     * @return Total monthly contributions/deductions
     */
    double calculateMonthlyContributions(double grossWage) {
        // Calculate contributions/deductions of SSS, PhilHealth, and Pag-IBIG
        double sssContribution = sssDeduction.calculateSssDeduction(grossWage);
        double philHealthContribution = healthInsuranceDeduction.calculatePhilHealthDeduction(grossWage);
        double pagIbigContribution = healthInsuranceDeduction.calculatePagIbigDeduction(grossWage);

        // Return the sum of all the contributions/deductions
        return sssContribution + philHealthContribution + pagIbigContribution;
    }

    /**
     * Determines the "additional" tax rate provided in percentage based on the
     * reference.
     *
     * @param taxableIncome Amount of the taxable income
     * @return Additional tax rate provided in percentage
     */
    private double determineAdditionalRateInPercentage(double taxableIncome) {
        if (taxableIncome >= 666667) {
            return 0.35;
        } else if (taxableIncome >= 166667 && taxableIncome < 666667) {
            return 0.32;
        } else if (taxableIncome >= 66667 && taxableIncome < 166667) {
            return 0.30;
        } else if (taxableIncome >= 33333 && taxableIncome < 66667) {
            return 0.25;
        } else if (taxableIncome >= 20832 && taxableIncome < 33333) {
            return 0.20;
        } else {
            return 0.0;
        }
    }

    /**
     * Determines the excess value.
     *
     * @param taxableIncome Taxable income amount
     * @return Excess value
     */
    private double determineExcessValue(double taxableIncome) {
        if (taxableIncome >= 666667) {
            return 666667;
        } else if (taxableIncome >= 166667 && taxableIncome < 666667) {
            return 166667;
        } else if (taxableIncome >= 66667 && taxableIncome < 166667) {
            return 66667;
        } else if (taxableIncome >= 33333 && taxableIncome < 66667) {
            return 33333;
        } else if (taxableIncome >= 20832 && taxableIncome < 33333) {
            return 20832;
        } else {
            return 0.0;
        }
    }

}
