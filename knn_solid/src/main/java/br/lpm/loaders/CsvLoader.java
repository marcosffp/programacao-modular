package br.lpm.loaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import br.lpm.data_structures.Attribute;
import br.lpm.data_structures.DataPoint;
import br.lpm.data_structures.DataSet;
import br.lpm.data_structures.Parser;

public class CsvLoader implements DataLoader {

  private char delimiter;

  public char getDelimiter() {
    return delimiter;
  }

  public void setDelimiter(char delimiter) {
    this.delimiter = delimiter;
  }

  public CsvLoader(char delimiter) {
    this.delimiter = delimiter;
  }

  @Override
  public void load(String filename, DataSet dataSet) {
    try (BufferedReader inputFile = new BufferedReader(new FileReader(filename))) {
      String line = inputFile.readLine();
      if (line != null) {
        loadHeader(line, dataSet);
      }

      while ((line = inputFile.readLine()) != null) {
        DataPoint dp = parseDataPoint(line);
        dataSet.getDataPoints().add(dp);
      }
    } catch (Exception e) {

    }
  }

  private void loadHeader(String line, DataSet dataSet) {
    String[] headers = line.split(String.valueOf(delimiter));
    List<String> attributeNames = dataSet.getAttributeNames();
    for (int i = 0; i < headers.length; i++) {
      attributeNames.add(headers[i].trim());
    }
    dataSet.setStateName(headers[headers.length - 1].trim());
  }

  private DataPoint parseDataPoint(String line) {
    String[] dataFields = line.split(String.valueOf(delimiter));
    DataPoint dataPoint = new DataPoint();
    for (int i = 0; i < dataFields.length - 1; i++) {
      Attribute attribute = new Attribute(Parser.parse(dataFields[i].trim()));
      dataPoint.addAttribute(attribute);
    }
    dataPoint.setState(new Attribute(Parser.parse(dataFields[dataFields.length - 1].trim())));
    return dataPoint;
  }
}
