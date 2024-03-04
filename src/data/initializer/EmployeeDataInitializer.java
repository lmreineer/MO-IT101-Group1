package data.initializer;

import data.AttendanceRecords;
import data.EmployeeDataManager;
import data.EmployeeInfo;

import java.util.List;

public class EmployeeDataInitializer extends EmployeeDataManager {
  EmployeeInfoInitializer info = new EmployeeInfoInitializer();
  AttendanceRecordsInitializer record = new AttendanceRecordsInitializer();

  public EmployeeDataInitializer() {
    List<EmployeeInfo> employees = (info.employees);
    List<AttendanceRecords> attendance = (record.attendance);

    // Populate
    getEmployeeList().addAll(employees);
    getAttendanceRecords().addAll(attendance);
  }
}
