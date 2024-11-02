package br.lpm.business.dataparser;

import java.util.List;

public interface CompositeDataParser {
  public Object parse(String input);

  public void addParser(DataParser parser);

  public List<DataParser> getParsers();

  public void removeParser(DataParser parser);
} 