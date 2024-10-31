package br.lpm.business.dataparser;

public class NumericParser implements DataParser {
  @Override
  public Object parse(String input) {
    try {
      if (input.contains(",") || input.contains(".")) {
        return Float.parseFloat(input.replace(",", "."));
      }
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
