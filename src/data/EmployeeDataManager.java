package data;

import java.util.ArrayList;
import java.util.List;

public abstract class EmployeeDataManager {
  private List<EmployeeInfo> employeeInfo;
  private List<AttendanceRecords> attendanceRecords;
  private List<SssContributionRange> sssContributionRange;

  public EmployeeDataManager() {
    employeeInfo = new ArrayList<>();
    attendanceRecords = new ArrayList<>();
    sssContributionRange = new ArrayList<>();
  }

  public List<EmployeeInfo> getEmployeeList() {
    return employeeInfo;
  }

  public List<AttendanceRecords> getAttendanceRecords() {
    return attendanceRecords;
  }

  public List<SssContributionRange> getSssContributionRange() {
    return sssContributionRange;
  }
}
