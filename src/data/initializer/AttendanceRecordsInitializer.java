package data.initializer;

import data.AttendanceRecords;

import java.util.Arrays;
import java.util.List;

public class AttendanceRecordsInitializer {
  public List<AttendanceRecords> attendance =
      Arrays.asList(
          new AttendanceRecords(16, "12/19/2022", "8:00", "17:00"),
          new AttendanceRecords(17, "12/19/2022", "8:00", "17:00"),
          new AttendanceRecords(18, "12/19/2022", "8:00", "17:00"),
          new AttendanceRecords(19, "12/19/2022", "8:00", "17:00"));
}
