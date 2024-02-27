import java.util.Scanner;

public class MotorPH {
  static void displayUpperBorder(String text) {
    System.out.println("================================");
    System.out.println(text);
    System.out.println("================================");
  }

  static void displayMenu() {
    displayUpperBorder("|   Motor PH Payroll System    |");
    System.out.println("|   1:  Search Employee        |");
    System.out.println("|   2:  Calculate Gross Wage   |");
    System.out.println("|   3:  Calculate Net Wage     |");
    System.out.println("================================");
  }

  static void clearConsole() {
    for (int i = 0; i < 1000; i++) {
      System.out.println("\n");
    }
  }

  static void showEmployeeInfo(int employeeNumInput) {
    EmployeeInfo info = new EmployeeInfo();

    System.out.println("Last Name: " + info.lastName[employeeNumInput]);
    System.out.println("First Name: " + info.firstName[employeeNumInput]);
    System.out.println("Birthdate: " + info.birthdate[employeeNumInput]);
    System.out.println("Address: " + info.address[employeeNumInput]);
    System.out.println("Phone Number: " + info.phoneNumber[employeeNumInput]);
    System.out.println("SSS #: " + info.sssNumber[employeeNumInput]);
    System.out.println("PhilHealth #: " + info.philhealthNumber[employeeNumInput]);
    System.out.println("TIN #: " + info.tinNumber[employeeNumInput]);
    System.out.println("Pag-IBIG #: " + info.pagIbigNumber[employeeNumInput]);
    System.out.println("Status: " + info.status[employeeNumInput]);
    System.out.println("Position: " + info.position[employeeNumInput]);
    System.out.println("Immediate Supervisor: " + info.immediateSupervisor[employeeNumInput]);
    System.out.println("Basic Salary: " + info.basicSalary[employeeNumInput]);
    System.out.println("Rice Subsidy: " + info.riceSubsidy[employeeNumInput]);
    System.out.println("Phone Allowance: " + info.phoneAllowance[employeeNumInput]);
    System.out.println("Clothing Allowance: " + info.clothingAllowance[employeeNumInput]);
    System.out.println("Gross Semi-monthly Rate: " + info.grossSemimonthlyRate[employeeNumInput]);
    System.out.println("Hourly Rate: " + info.hourlyRate[employeeNumInput]);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    displayMenu();
    int menuInput = scanner.nextInt();

    EmployeeInfo info = new EmployeeInfo();

    // If user chose option 1
    if (menuInput == 1) {
      clearConsole();

      displayUpperBorder("|     Employee Information     |");

      System.out.print("Employee Number: ");
      // Start the array at 1
      int employeeNumInput = scanner.nextInt() - 1;

      if (employeeNumInput <= info.totalEmployees) {
        // Show employee information based on inputted number
        showEmployeeInfo(employeeNumInput);
      } else {
        System.out.println("Employee not found");
      }
    } else if (menuInput == 2) {
      clearConsole();

      displayUpperBorder("|         Gross Wage           |");

      System.out.print("Employee Number: ");
      // Start the array at 1
      int employeeNumInput = scanner.nextInt() - 1;

      if (employeeNumInput <= info.totalEmployees) {
        // Show employee information based on inputted number
        System.out.println("Last Name: " + info.lastName[employeeNumInput]);
        System.out.println("First Name: " + info.firstName[employeeNumInput]);

        wageCalculation grossWage = new wageCalculation();

        System.out.println("Gross Wage: " + grossWage.calculateGrossWage(employeeNumInput));
      } else {
        System.out.println("Employee not found");
      }
    } else {
      System.out.println("Invalid input");
    }
  }
}
