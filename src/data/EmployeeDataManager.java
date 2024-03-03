package data;

import java.util.ArrayList;
import java.util.List;

public abstract class EmployeeDataManager {
  private List<EmployeeInfo> employeeList;

  public EmployeeDataManager() {
    employeeList = new ArrayList<>();
  }

  public List<EmployeeInfo> getEmployeeList() {
    return employeeList;
  }
}
