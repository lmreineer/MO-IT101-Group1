import java.io.IOException;
import java.util.Scanner;

public class MotorPH {
  static void clearConsole() {
    for (int i = 0; i < 1000; i++) {
      System.out.println("\n");
    }
  }

  public static void main(String[] args) {
    EmployeeInfo employeeInfo = new EmployeeInfo();

    Scanner scanner = new Scanner(System.in);

    System.out.println("================================");
    System.out.println("|   Motor PH Payroll System    |");
    System.out.println("================================");
    System.out.println("|   1:  Search Employee        |");
    System.out.println("|   2:  Calculate Gross Wage   |");
    System.out.println("|   3:  Calculate Net Wage     |");
    System.out.println("================================");
    int menuInput = scanner.nextInt();

    // If user chose option 1
    if (menuInput == 1) {
      clearConsole();

      System.out.println("================================");
      System.out.println("|     Employee Information     |");
      System.out.println("================================");

      System.out.print("Employee Number: ");
      // Start the array at 1
      int employeeNumInput = scanner.nextInt() - 1;

      if (employeeNumInput <= employeeInfo.totalEmployees) {
        // Show employee information based on inputted number
        System.out.println("Last Name: " + employeeInfo.lastName[employeeNumInput]);
        System.out.println("First Name: " + employeeInfo.firstName[employeeNumInput]);
        System.out.println("Birthdate: " + employeeInfo.birthdate[employeeNumInput]);
        System.out.println("Address: " + employeeInfo.address[employeeNumInput]);
        System.out.println("Phone Number: " + employeeInfo.phoneNumber[employeeNumInput]);
        System.out.println("SSS #: " + employeeInfo.sssNumber[employeeNumInput]);
        System.out.println("PhilHealth #: " + employeeInfo.philhealthNumber[employeeNumInput]);
        System.out.println("TIN #: " + employeeInfo.tinNumber[employeeNumInput]);
        System.out.println("Pag-IBIG #: " + employeeInfo.pagIbigNumber[employeeNumInput]);
        System.out.println("Status: " + employeeInfo.status[employeeNumInput]);
        System.out.println("Position: " + employeeInfo.position[employeeNumInput]);
        System.out.println(
            "Immediate Supervisor: " + employeeInfo.immediateSupervisor[employeeNumInput]);
        System.out.println("Basic Salary: " + employeeInfo.basicSalary[employeeNumInput]);
        System.out.println("Rice Subsidy: " + employeeInfo.riceSubsidy[employeeNumInput]);
        System.out.println("Phone Allowance: " + employeeInfo.phoneAllowance[employeeNumInput]);
        System.out.println(
            "Clothing Allowance: " + employeeInfo.clothingAllowance[employeeNumInput]);
        System.out.println(
            "Gross Semi-monthly Rate: " + employeeInfo.grossSemimonthlyRate[employeeNumInput]);
        System.out.println("Hourly Rate: " + employeeInfo.hourlyRate[employeeNumInput]);
      } else {
        System.out.println("Employee not found");
      }
    } else {
      System.out.println("Invalid input");
    }
  }
}
