package wage_calculation;

import data.SssContributionList;

public class NetWageCalculation {
  GrossWageCalculation grossWage = new GrossWageCalculation();

  public String calculateSssContribution(int employeeNumInput) {
    double sssDeduction = 0;

    SssContributionList sss = new SssContributionList();

    double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

    // Loop through the compensation ranges and apply deduction based on contribution
    for (int i = 0; i < sss.getCompensationRanges().length; i++) {
      // Separate the values
      String[] asd = sss.getCompensationRanges()[i].split("-");
      // Convert to double and assign both values to their sides based on the SSS Contribution sheet
      double leftValue = Double.parseDouble(asd[0]);
      double rightValue = Double.parseDouble(asd[1]);

      // Check the range of which the employee's grossWage falls between
      if (doubledGrossWage > leftValue && doubledGrossWage <= rightValue) {
        // Apply deduction based on contribution
        // "contribution[i]"'s logic: ONLY return the value if the condition is true
        sssDeduction = sss.getContributions()[i];
      } else if (doubledGrossWage <= leftValue) {
        // Subtract grossWage with the first element of the contributions array
        sssDeduction = sss.getContributions()[0];
      } else if (doubledGrossWage > rightValue) {
        // Subtract grossWage with the last element of the contributions array
        sssDeduction = sss.getContributions()[sss.getContributions().length - 1];
      }
    }

    // Round to two decimal places and apply thousands separator
    String formattedSssDeduction = String.format("%,.2f", sssDeduction);

    return formattedSssDeduction;
  }

  public String calculatePhilHealthContribution(int employeeNumInput) {
    double monthlyPremium = 0;
    double philHealthDeduction = 0;

    double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

    // If grossWage is less than the minimum basic salary
    if (doubledGrossWage < 10000) {
      // Deduct a minimum contribution of 300
      monthlyPremium = 300;
      // Take 50% from the monthlyPremium as employee share
      philHealthDeduction = (50 * monthlyPremium) / 100;

      // Else if grossWage is greater than the maximum basic salary
    } else if (doubledGrossWage > 60000) {
      // Deduct a maximum contribution of 1800
      monthlyPremium = 1800;
      // Take 50% from the monthlyPremium as employee share
      philHealthDeduction = (50 * monthlyPremium) / 100;

      // Else if it falls between the range of 10000.01 - 59999.99
    } else {
      // Deduct 3% from the total grossWage
      monthlyPremium = (3 * doubledGrossWage) / 100;
      // Take 50% from the monthlyPremium as employee share
      philHealthDeduction = (50 * monthlyPremium) / 100;
    }

    // Round to two decimal places and apply thousands separator
    String formattedPhilhealthDeduction = String.format("%,.2f", philHealthDeduction);

    return formattedPhilhealthDeduction;
  }

  public String calculatePagIbigContribution(int employeeNumInput) {
    double pagIbigDeduction = 0;

    double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

    // If grossWage is at least 1000 to 1500
    if (doubledGrossWage >= 1000 && doubledGrossWage <= 1500) {
      // Take 3% from the grossWage
      pagIbigDeduction = (3 * doubledGrossWage) / 100;

      // Else if grossWage is greater than 1500
    } else if (doubledGrossWage > 1500) {
      // Take 4% from the grossWage
      double totalContribution = (4 * doubledGrossWage) / 100;
      // If totalContribution is greater than 100, don't increase, else, continue calculation
      pagIbigDeduction = totalContribution > 100 ? 100 : totalContribution;
    }

    // Round to two decimal places and apply thousands separator
    String formattedPhilhealthDeduction = String.format("%,.2f", pagIbigDeduction);

    return formattedPhilhealthDeduction;
  }

  public double calculateMonthlyContributions(int employeeNumInput) {
    double sssContribution = Double.parseDouble(calculateSssContribution(employeeNumInput));
    double philHealthContribution =
        Double.parseDouble(calculatePhilHealthContribution(employeeNumInput));
    double pagIbigContribution = Double.parseDouble(calculatePagIbigContribution(employeeNumInput));

    // Calculate total deductions before calculating withholding tax
    double monthlyContributions = sssContribution + philHealthContribution + pagIbigContribution;

    return monthlyContributions;
  }

  public String calculateWithholdingTax(int employeeNumInput) {
    double excessValue = 0;
    double withholdingTax = 0;

    double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

    double monthlyContributions = calculateMonthlyContributions(employeeNumInput);

    double taxableIncome = doubledGrossWage - monthlyContributions;

    if (taxableIncome <= 20832) {
      withholdingTax = 0;
    } else if (taxableIncome > 20832 && taxableIncome < 33333) {
      // Subtract taxable income with the excess value
      excessValue = taxableIncome - 20833;
      // Take 20% of the calculated amount
      withholdingTax = (20 * excessValue) / 100;
    } else if (taxableIncome >= 33333 && taxableIncome < 66667) {
      // Subtract taxable income with the excess value
      excessValue = taxableIncome - 33333;
      // Take 25% of the calculated amount plus 2500
      withholdingTax = ((25 * excessValue) / 100) + 2500;
    } else if (taxableIncome >= 66667 && taxableIncome < 166667) {
      // Subtract taxable income with the excess value
      excessValue = taxableIncome - 66667;
      // Take 30% of the calculated amount plus 666667
      withholdingTax = ((30 * excessValue) / 100) + 10833;
    } else if (taxableIncome >= 166667 && taxableIncome < 666667) {
      // Subtract taxable income with the excess value
      excessValue = taxableIncome - 166667;
      // Take 32% of the calculated amount plus 40833.33
      withholdingTax = ((32 * excessValue) / 100) + 40833.33;
    } else if (taxableIncome >= 666667) {
      // Subtract taxable income with the excess value
      excessValue = taxableIncome - 666667;
      // Take 32% of the calculated amount plus 200833.33
      withholdingTax = ((32 * excessValue) / 100) + 200833.33;
    }

    // Round to two decimal places and apply thousands separator
    String formattedWithholdingTax = String.format("%,.2f", withholdingTax);

    return formattedWithholdingTax;
  }

  public double calculateLateArrivalDeduction(int employeeNumInput) {
    return 0;
  }

  public double calculateTotalDeductions(int employeeNumInput) {
    double monthlyContributions = calculateMonthlyContributions(employeeNumInput);
    double withholdingTax = Double.parseDouble(calculateWithholdingTax(employeeNumInput));
    double lateArrivalDeduction = calculateLateArrivalDeduction(employeeNumInput);

    double totalDeductions = monthlyContributions + withholdingTax + lateArrivalDeduction;

    return totalDeductions;
  }

  public String formatTotalDeductions(int employeeNumInput) {
    double totalDeductions = calculateTotalDeductions(employeeNumInput);

    // Round to two decimal places and apply thousands separator
    String formattedTotalDeductions = String.format("%,.2f", totalDeductions);

    return formattedTotalDeductions;
  }

  public String calculateNetWage(int employeeNumInput) {
    double totalDeductions = calculateTotalDeductions(employeeNumInput);

    double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

    double netWage = doubledGrossWage - totalDeductions;

    // Round to two decimal places and apply thousands separator
    String formattedNetWage = String.format("%,.2f", netWage);

    return formattedNetWage;
  }
}
