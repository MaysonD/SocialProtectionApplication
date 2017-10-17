package helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator {

    public static LocalDate makeDateValid(LocalDate date) {
        return date == null ? LocalDate.parse("01.01.0001", DateTimeFormatter.ofPattern("d.MM.yyyy")) : date;
    }

    public static LocalDate checkDate(LocalDate date) {
        return date == null || date.toString().equals("0001-12-31") || date.toString().equals("0001-01-01") ? null : date;
    }
}
