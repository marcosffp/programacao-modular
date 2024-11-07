package br.lpm.business.loader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Parser {
  private Parser() {
  }

  public static Object parse(String input) {
    try {
      return Integer.parseInt(input);
    } catch (Exception e) {

    }
    try { 
      return Double.parseDouble(input);
    } catch (Exception e) {

    }
    if (input.equalsIgnoreCase("sim") || input.equalsIgnoreCase("true")) {
      return true;
    }
    if (input.equalsIgnoreCase("não") || input.equalsIgnoreCase("false")) {
      return false;
    }
    try {
      return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    } catch (DateTimeParseException e) {
    }
    try {
      return LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    } catch (DateTimeParseException e) {
    }
    return input;
  }

}
