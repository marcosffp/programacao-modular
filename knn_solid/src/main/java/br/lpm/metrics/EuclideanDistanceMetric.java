package br.lpm.metrics;

import java.util.List;

import br.lpm.data_structures.Attribute;
import br.lpm.data_structures.DataPoint;

public class EuclideanDistanceMetric implements Metric {

  @Override
  public double distance(DataPoint p1, DataPoint p2) {
    List<Attribute> attributesP1 = p1.getAttributes();
    List<Attribute> attributesP2 = p2.getAttributes();

    if (attributesP1.size() != attributesP2.size()) {
      throw new IllegalArgumentException("Os DataPoints comparados devem ter o mesmo número de atributos.");
    }

    double totalDistance = 0;
    for (int i = 0; i < attributesP1.size(); i++) {
      Object value1 = attributesP1.get(i).getValue();
      Object value2 = attributesP2.get(i).getValue();
      totalDistance += calculateAttributeDistance(value1, value2);
    }
    return Math.sqrt(totalDistance / attributesP1.size());
  }

  private double calculateAttributeDistance(Object value1, Object value2) {
    if (value1 instanceof Number && value2 instanceof Number) {
      return calculateNumericDistance((Number) value1, (Number) value2);
    } else {
      return calculateNonNumericDistance(value1, value2);
    }
  }

  private double calculateNumericDistance(Number n1, Number n2) {
    double d1 = n1.doubleValue();
    double d2 = n2.doubleValue();
    return Math.pow(d1 - d2, 2);
  }

  private double calculateNonNumericDistance(Object value1, Object value2) {
    return value1.equals(value2) ? 0 : 1;
  }
}
