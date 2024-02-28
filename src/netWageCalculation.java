import java.io.Serializable;
import java.text.DecimalFormat;

public class netWageCalculation {
  grossWageCalculation grossWage = new grossWageCalculation();

  DecimalFormat decimal = new DecimalFormat("0.00");

  public String calculateSssContribution(int employeeNumInput) {
    double sssDeduction = 0;

    SssContributionList sss = new SssContributionList();

    double doubledGrossWage = Double.parseDouble(grossWage.calculateGrossWage(employeeNumInput));

    // Loop through the compensation ranges and apply deduction based on contribution
    for (int i = 0; i < sss.compensationRanges.length; i++) {
      // Separate the values
      String[] asd = sss.compensationRanges[i].split("-");
      // Convert to double and assign both values to their sides based on the SSS Contribution sheet
      double leftValue = Double.parseDouble(asd[0]);
      double rightValue = Double.parseDouble(asd[1]);

      // Check the range of which the employee's grossWage falls between
      if (doubledGrossWage > leftValue && doubledGrossWage <= rightValue) {
        // Apply deduction based on contribution
        // "contribution[i]"'s logic: ONLY return the value if the condition is true
        sssDeduction = doubledGrossWage - sss.contributions[i];
      } else if (doubledGrossWage <= leftValue) {
        // Subtract grossWage with the first element of the contributions array
        sssDeduction = doubledGrossWage - sss.contributions[0];
      } else if (doubledGrossWage > rightValue) {
        // Subtract grossWage with the last element of the contributions array
        sssDeduction = doubledGrossWage - sss.contributions[sss.contributions.length - 1];
      }
    }

    String formattedSssDeduction = decimal.format(sssDeduction);

    return formattedSssDeduction;
  }

  public String calculatePhilHealthContribution(int employeeNumInput) {
    double monthlyPremium = 0;
    double employeeShare = 0;

    double doubledGrossWage = Double.parseDouble(grossWage.calculateGrossWage(employeeNumInput));

    // If grossWage is less than the minimum basic salary
    if (doubledGrossWage < 10000) {
      // Deduct a minimum contribution of 300
      monthlyPremium = 300;
      // Deduct 50% from the monthlyPremium
      employeeShare = (50 * monthlyPremium) / 100;

      // Else if grossWage is more than the maximum basic salary
    } else if (doubledGrossWage > 60000) {
      // Deduct a maximum contribution of 1800
      monthlyPremium = 1800;
      // Deduct 50% from the monthlyPremium
      employeeShare = (50 * monthlyPremium) / 100;

      // Else if it falls between the range of 10000.01 - 59999.99
    } else {
      // Deduct 3% from the total grossWage
      monthlyPremium = (3 * doubledGrossWage) / 100;
      // Deduct 50% from the monthlyPremium
      employeeShare = (50 * monthlyPremium) / 100;
    }

    double philHealthDeduction = doubledGrossWage - employeeShare;

    String formattedPhilHealthDeduction = decimal.format(philHealthDeduction);

    return formattedPhilHealthDeduction;
  }

  public String calculatePagIbigContribution(int employeeNumInput) {
    double validatedTotalContribution = 0;

    double doubledGrossWage = Double.parseDouble(grossWage.calculateGrossWage(employeeNumInput));

    if (doubledGrossWage >= 1000 && doubledGrossWage <= 1500) {
      validatedTotalContribution = (3 * doubledGrossWage) / 100;
    } else if (doubledGrossWage > 1500) {
      double totalContribution = (4 * doubledGrossWage) / 100;
      validatedTotalContribution = totalContribution > 100 ? 100 : totalContribution;
    }

    double pagIbigDeduction = doubledGrossWage - validatedTotalContribution;

    String formattedPhilHealthDeduction = decimal.format(pagIbigDeduction);

    return formattedPhilHealthDeduction;
  }

  public String calculateNetWage(int employeeNumInput) {
    return calculateSssContribution(employeeNumInput);
  }
}
