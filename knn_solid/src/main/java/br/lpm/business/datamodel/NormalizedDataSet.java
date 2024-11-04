package br.lpm.business.datamodel;

import java.util.ArrayList;
import java.util.List;

public class NormalizedDataSet extends DataSet implements Normalize {

  @Override
  public List<DataPoint> normalizeData(List<DataPoint> data) {
    List<String> attributeNames = super.getAttributeNames();
    List<DataPoint> normalizedDataPoints = new ArrayList<>();

    double[] minValues = new double[attributeNames.size()];
    double[] maxValues = new double[attributeNames.size()];

    for (int i = 0; i < attributeNames.size(); i++) {
      minValues[i] = Double.MAX_VALUE;
      maxValues[i] = Double.NEGATIVE_INFINITY;
    }

    for (DataPoint dp : data) {
      for (int i = 0; i < attributeNames.size(); i++) {
        Object value = dp.getAttributes().get(i).getValue();
        if (value instanceof Double) {
          double doubleValue = (Double) value;
          if (doubleValue < minValues[i]) {
            minValues[i] = doubleValue;
          }
          if (doubleValue > maxValues[i]) {
            maxValues[i] = doubleValue;
          }
        }
      }
    }

    for (int i = 0; i < attributeNames.size(); i++) {
      System.out.println("Atributo: " + attributeNames.get(i) +
          " | Mínimo: " + minValues[i] +
          " | Máximo: " + maxValues[i]);
    }

    for (DataPoint originalDataPoint : data) {
      DataPoint normalizedDataPoint = new DataPoint();
      normalizedDataPoint.setState(originalDataPoint.getState());

      for (int i = 0; i < attributeNames.size(); i++) {
        Object value = originalDataPoint.getAttributes().get(i).getValue();
        if (value instanceof Double) {
          double originalValue = (Double) value;
          double range = maxValues[i] - minValues[i];
          double normalizedValue = range == 0 ? 0 : (originalValue - minValues[i]) / range;
          normalizedDataPoint.addAttribute(new Attribute(normalizedValue));
        } else {
          normalizedDataPoint.addAttribute(originalDataPoint.getAttributes().get(i));
        }
      }
      normalizedDataPoints.add(normalizedDataPoint);
    }

    return normalizedDataPoints;
  }

  @Override
  public String toString() {
    return "Normalized DataSet: " + super.toString();
  }
}
