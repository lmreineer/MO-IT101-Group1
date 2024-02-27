import java.text.DecimalFormat;

public class wageCalculation {
  EmployeeInfo info = new EmployeeInfo();

  DecimalFormat df = new DecimalFormat("0.00");

  public String calculateGrossWage(int employeeNumInput) {
    // 160 because 8 x 5 is 40 and 40  x 4 = 160
    double grossWage = info.hourlyRate[employeeNumInput] * 160;
    String formattedGrossWage = df.format(grossWage);

    return formattedGrossWage;
  }

  public double calculateNetWage(int employeeNumInput) {
    double netWage = info.hourlyRate[employeeNumInput] * 160;
    return netWage;
  }
}
