package br.lpm.business.datamodel;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataSet {
  private List<BaseDataPoint> dataPoints = new ArrayList<>();
  private List<String> attributeNames = new ArrayList<>();
  private String stateName;

  public void addDataPoint(BaseDataPoint dataPoint) {
    dataPoints.add(dataPoint);
  }

  public void addDataPoints(List<BaseDataPoint> dataPoints) {
    this.dataPoints.addAll(dataPoints);
  }

  public void removeDataPoint(BaseDataPoint dataPoint) {
    dataPoints.remove(dataPoint);
  }

  public void removeDataPoint(Object state) {
    dataPoints.removeIf(dataPoint -> dataPoint.getState().equals(state));
  }

  public void removeDataPoint(int index) {
    dataPoints.remove(index);
  }

  public void clearDataPoints() {
    dataPoints.clear();
  }

  public int sizeDataPoints() {
    return dataPoints.size();
  }

  public BaseDataPoint getDataPoint(int index) {
    return dataPoints.get(index);
  }

  public BaseDataPoint getDataPoint(Object state) {
    for (BaseDataPoint dataPoint : dataPoints) {
      if (dataPoint.getState().equals(state)) {
        return dataPoint;
      }
    }
    return null;
  }

  public List<BaseDataPoint> getDataPoints() {
    return dataPoints;
  }

  public void addAttributeName(String attributeName) {
    attributeNames.add(attributeName);
  }

  public void addAttributeNames(List<String> attributeNames) {
    this.attributeNames.addAll(attributeNames);
  }

  public void removeAttributeName(String attributeName) {
    attributeNames.remove(attributeName);
  }

  public void removeAttributeName(int index) {
    attributeNames.remove(index);
  }

  public void clearAttributeNames() {
    attributeNames.clear();
  }

  public int sizeAttributeNames() {
    return attributeNames.size();
  }

  public String getAttributeName(int index) {
    return attributeNames.get(index);
  }

  public String getAttributeName(String name) {
    for (String attributeName : attributeNames) {
      if (attributeName.equals(name)) {
        return attributeName;
      }
    }
    return null;
  }
  
  public List<String> getAttributeNames() {
    return attributeNames;
  }

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Attributes: ");
    for (String attributeName : attributeNames) {
      sb.append(attributeName).append(", ");
    }
    sb.append("\nState Name: ").append(stateName).append("\nData Points:\n");
    for (BaseDataPoint dataPoint : dataPoints) {
      sb.append(dataPoint.toString()).append("\n");
    }
    return sb.toString();
  }
}
