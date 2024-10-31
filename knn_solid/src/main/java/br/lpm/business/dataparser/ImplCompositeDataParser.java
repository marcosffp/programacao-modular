package br.lpm.business.dataparser;

import java.util.ArrayList;
import java.util.List;

public class ImplCompositeDataParser implements CompositeDataParser {
  private List<DataParser> parsers = new ArrayList<>();

  public void addParser(DataParser parser) {
    parsers.add(parser);
  }

  public List<DataParser> getParsers() {
    return parsers;
  }

  public void removeParser(DataParser parser) {
    parsers.remove(parser);
  }

  @Override
  public Object parse(String input) {
    for (DataParser parser : parsers) {
      Object parsedValue = parser.parse(input);
      if (parsedValue != null) {
        return parsedValue;
      }
    }
    return input;
  }
}
