import java.text.DecimalFormat;

public class netWageCalculation {
  grossWageCalculation grossWage = new grossWageCalculation();

  EmployeeInfo info = new EmployeeInfo();

  DecimalFormat decimal = new DecimalFormat("0.00");

  public String calculateSssContribution(int employeeNumInput) {
    double netWage = 0;

    double doubledGrossWage = Double.parseDouble(grossWage.calculateGrossWage(employeeNumInput));
    if (doubledGrossWage > 24750) {
      netWage = doubledGrossWage - 1125;
    }

    String formattedNetWage = decimal.format(netWage);

    return formattedNetWage;
  }

  public String calculateNetWage(int employeeNumInput) {
    return calculateSssContribution(employeeNumInput);
  }
}
