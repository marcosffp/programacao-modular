package br.lpm.business.dataparser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser implements DataParser {
  @Override
  public Object parse(String input) {
    try {
      DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M/d/yyyy");
      DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/M/yyyy");
      try {
        return LocalDate.parse(input, formatter1);
      } catch (DateTimeParseException ignored) {
      }
      return LocalDate.parse(input, formatter2);
    } catch (DateTimeParseException e) {
      return null;
    }
  }
}
