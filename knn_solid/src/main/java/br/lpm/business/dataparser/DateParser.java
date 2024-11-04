package br.lpm.business.dataparser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser implements DataParser {
  @Override
  public Object parse(String input) {
    DateTimeFormatter[] formatters = {
        DateTimeFormatter.ofPattern("M/d/yyyy"),
    };
    for (DateTimeFormatter formatter : formatters) {
      try {
        return LocalDate.parse(input, formatter);
      } catch (DateTimeParseException ignored) {
      }
    }
    return null;
  }
}
