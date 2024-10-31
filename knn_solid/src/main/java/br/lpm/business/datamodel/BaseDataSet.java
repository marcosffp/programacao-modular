package br.lpm.business.datamodel;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataSet {
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
