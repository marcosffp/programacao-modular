package br.lpm.business.dataparser;

public class BooleanParser implements DataParser {
  @Override
  public Object parse(String input) {
    if (input.equalsIgnoreCase("Sim") || input.equalsIgnoreCase("true"))
      return true;
    if (input.equalsIgnoreCase("Não") || input.equalsIgnoreCase("false"))
      return false;
    return null;
  }
}
