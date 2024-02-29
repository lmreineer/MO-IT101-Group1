import java.text.DecimalFormat;

public class grossWageCalculation {

  public String calculateGrossWage(int employeeNumInput) {
    EmployeeInfo info = new EmployeeInfo();

    DecimalFormat decimal = new DecimalFormat("0.00");

    // Work in progress
    double grossWage = info.hourlyRate[employeeNumInput] * 40;
    String formattedGrossWage = decimal.format(grossWage);

    return formattedGrossWage;
  }
}
