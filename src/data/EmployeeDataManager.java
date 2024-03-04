package data;

import java.util.ArrayList;
import java.util.List;

public abstract class EmployeeDataManager {
  private List<EmployeeInfo> employeeList;
  private List<AttendanceRecords> attendanceRecords;

  public EmployeeDataManager() {
    employeeList = new ArrayList<>();
    attendanceRecords = new ArrayList<>();
  }

  public List<EmployeeInfo> getEmployeeList() {
    return employeeList;
  }

  public List<AttendanceRecords> getAttendanceRecords() {
    return attendanceRecords;
  }

  public void addAttendanceRecord(AttendanceRecords record) {
    attendanceRecords.add(record);
  }
}
