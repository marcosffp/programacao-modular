package br.lpm.business.dataparser;


public class NumericParser implements DataParser {
  @Override
  public Object parse(String input) {
    try {
      if (input.contains(",") || input.contains(".")) {
        return Double.parseDouble(input.replace(",", "."));
      } else {
        return (double) Integer.parseInt(input);
      }
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
