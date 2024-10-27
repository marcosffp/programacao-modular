package br.lpm.business;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataPoint {
  private List<Attribute> attributes = new ArrayList<>();
  private Object state;

  public void addAttribute(Attribute attribute) {
    attributes.add(attribute);
  }

  public void addAttributes(List<Attribute> attributes) {
    this.attributes.addAll(attributes);
  }

  public DataPoint setState(Object state) {
    this.state = state;
    return this;
  }

  public Attribute getAttribute(int index) {
    return attributes.get(index);
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  @SuppressWarnings("unchecked")
  public <T> T getState() {
    return (T) this.state;
  }

  public Object parse(String input) throws InvalidFormatException {
    try {
      if (input.equalsIgnoreCase("Sim") || input.equalsIgnoreCase("true")) {
        return true;
      } else if (input.equalsIgnoreCase("Não") || input.equalsIgnoreCase("false")) {
        return false;
      }

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
      try {
        return LocalDate.parse(input, formatter);
      } catch (DateTimeParseException ignored) {
      }

      if (input.contains(",") || input.contains(".")) {
        return Float.parseFloat(input.replace(",", "."));
      }
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new InvalidFormatException("Input format is invalid: " + input);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(state);
    for (Attribute attribute : attributes) {
      sb.append(" ");
      sb.append(attribute);
    }
    return sb.toString();
  }
}
