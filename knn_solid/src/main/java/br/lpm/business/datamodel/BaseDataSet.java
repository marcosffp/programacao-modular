package br.lpm.business.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    return dataPoints.stream().filter(dataPoint -> dataPoint.getState().equals(state)).findFirst().orElse(null);
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
    return attributeNames.stream().filter(attributeName -> attributeName.equals(name)).findFirst().orElse(null);
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
    return "BaseDataSet{" +
        "dataPoints=" + (dataPoints != null ? 
            dataPoints.stream()
                      .map(Object::toString)
                      .collect(Collectors.joining(", ")) 
            : "[]") +
        ", attributeNames=" + (attributeNames != null ? 
            attributeNames.stream()
                          .collect(Collectors.joining(", ")) 
            : "[]") +
        ", stateName='" + stateName + '\'' +
        '}';
}

}