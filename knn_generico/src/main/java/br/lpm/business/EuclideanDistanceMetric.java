package br.lpm.business;

import java.util.List;

public class EuclideanDistanceMetric implements Metric {

  private Normalize normalize;

  @Override
  public double distance(DataPoint p1, DataPoint p2) {
    double distance = 0;

    List<Attribute> attributesP1 = p1.getAttributes();
    List<Attribute> attributesP2 = p2.getAttributes();

    if (attributesP1.size() != attributesP2.size()) {
      return -1; 
    }

    double[] minMaxValues = normalize.calculateMinMax(p1);
    double min = minMaxValues[0];
    double max = minMaxValues[1];

    for (int i = 0; i < attributesP1.size(); i++) {
      Object value1 = attributesP1.get(i).getValue();
      Object value2 = attributesP2.get(i).getValue();

      if (value1 instanceof Number && value2 instanceof Number) {
        double d1 = ((Number) value1).doubleValue();
        double d2 = ((Number) value2).doubleValue();

        double normalizedD1 = (d1 - min) / (max - min);
        double normalizedD2 = (d2 - min) / (max - min);

        distance += Math.pow(normalizedD1 - normalizedD2, 2);
      } else {
        distance += value1.equals(value2) ? 0 : 1;
      }
    }

    return Math.sqrt(distance/attributesP1.size());
  }
}
