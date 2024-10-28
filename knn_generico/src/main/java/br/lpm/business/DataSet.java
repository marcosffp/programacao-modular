package br.lpm.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;

public class DataSet {
  private List<DataPoint> dataPoints = new ArrayList<>();
  private List<String> attributeNames = new ArrayList<>();
  private String stateName;

  public void addDataPoint(DataPoint dataPoint) {
    dataPoints.add(dataPoint);
  }

  public void addDataPoints(List<DataPoint> dataPoints) {
    this.dataPoints.addAll(dataPoints);
  }

  public void addAttributeName(String attributeName) {
    attributeNames.add(attributeName);
  }

  public void addAttributeNames(List<String> attributeNames) {
    this.attributeNames.addAll(attributeNames);
  }

  public DataPoint getDataPoint(int index) {
    return dataPoints.get(index);
  }

  public List<DataPoint> getDataPoints() {
    return dataPoints;
  }

  public String getStateName() {
    return stateName;
  }

  public String getAttributeName(int index) {
    return attributeNames.get(index);
  }

  public List<String> getAttributeNames() {
    return attributeNames;
  }

  public int numDataPoints() {
    return dataPoints.size();
  }

  public int size() {
    return attributeNames.size();
  }

  public void removeAttribute(int index) {
    attributeNames.remove(index);
  }

  public void removeDataPoint(int index) {
    dataPoints.remove(index);
  }

  public void removeDataPoint(DataPoint dataPoint) {
    dataPoints.remove(dataPoint);
  }

  public void removeDataPoints(List<DataPoint> dataPoints) {
    this.dataPoints.removeAll(dataPoints);
  }

  public void removeAttributeName(String attributeName) {
    attributeNames.remove(attributeName);
  }

  public void removeAttributeNames(List<String> attributeNames) {
    this.attributeNames.removeAll(attributeNames);
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  public String getSateName() {
    return stateName;
  }

  public void removeAllDataPoints() {
    dataPoints.clear();
  }

  public void removeAllAttributeNames() {
    attributeNames.clear();
  }

  public void loadDataFromCSV(String filename) throws InvalidFormatException {
    try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
      String line = file.readLine();
      if (line == null) {
        throw new IOException("Arquivo CSV vazio");
      }

      String[] nomesAtributos = line.split(";");
      for (int i = 0; i < nomesAtributos.length - 1; i++) {
        addAttributeName(nomesAtributos[i].trim());
      }

      setStateName(nomesAtributos[nomesAtributos.length - 1].trim());

      DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(',');
      DecimalFormat format = new DecimalFormat("0.#");
      format.setDecimalFormatSymbols(symbols);

      while ((line = file.readLine()) != null) {
        String[] fields = line.split(";");
        DataPoint dataPoint = new DataPoint();

        for (int i = 0; i < fields.length - 1; i++) {
          try {
            dataPoint.addAttribute(new Attribute(dataPoint.parse(fields[i].trim())));
          } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Formato inválido no campo: " + fields[i]);
          }
        }

        dataPoint.setState(fields[fields.length - 1].trim());
        addDataPoint(dataPoint);
      }
    } catch (IOException e) {
      throw new InvalidFormatException("Erro ao ler arquivo CSV: " + e.getMessage());
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Attributes: ");
    for (String attributeName : attributeNames) {
      sb.append(attributeName).append(", ");
    }
    sb.append("\nState Name: ").append(stateName).append("\nData Points:\n");
    for (DataPoint dataPoint : dataPoints) {
      sb.append(dataPoint.toString()).append("\n");
    }
    return sb.toString();
  }
}