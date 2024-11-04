package br.lpm.business.dataparser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeDataParser {
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

  public Object parse(String input) {
    for (DataParser parser : parsers) {
      Object parsedValue = parser.parse(input);
      if (parsedValue != null) {
        return parsedValue;
      }
    }
    return input;
  }

  public void addParsers(List<DataParser> parsers) {
    this.parsers.addAll(parsers);
  }

  public DataParser getParser(int index) {
    return parsers.get(index);
  }

  public DataParser getParser(String name) {
    return parsers.stream().filter(parser -> parser.getClass().getSimpleName().equals(name)).findFirst().orElse(null);
  }

  public void removeParser(int index) {
    parsers.remove(index);
  }

  public void removeParser(String name) {
    this.parsers.removeIf(parser -> parser.getClass().getSimpleName().equals(name));
  }

  public void clearParsers() {
    parsers.clear();
  }

  public int sizeParsers() {
    return parsers.size();
  }

  @Override
  public String toString() {
    return "CompositeDataParser{" +
        "parsers=" + (parsers != null ? parsers.stream()
            .map(DataParser::toString)
            .collect(Collectors.joining(", "))
            : "[]")
        +
        '}';
  }

}
