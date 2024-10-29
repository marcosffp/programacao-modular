package br.lpm.business;

import java.util.List;

public class EuclideanDistanceMetric implements Metric {

  private static final double MAX_ALTURA = 2.60;
  private static final double MAX_PESO = 600.00;
  private static final double MAX_RENDA = 10000.00;
  private static final int INVALID = -1;

  @Override
  public double distance(DataPoint p1, DataPoint p2) {
    List<Attribute> attributesP1 = p1.getAttributes();
    List<Attribute> attributesP2 = p2.getAttributes();

    if (attributesP1.size() != attributesP2.size()) {
      return INVALID;
    }

    double distance = 0;
    for (int i = 0; i < attributesP1.size(); i++) {
      Object value1 = attributesP1.get(i).getValue();
      Object value2 = attributesP2.get(i).getValue();

      if (value1 instanceof Number && value2 instanceof Number) {
        double d1 = ((Number) value1).doubleValue();
        double d2 = ((Number) value2).doubleValue();
        if (i == 3) {
          distance += Math.pow((d1 - d2) / MAX_ALTURA, 2);
        } else if (i == 4) {
          distance += Math.pow((d1 - d2) / MAX_PESO, 2);
        } else if (i == 5) {
          distance += Math.pow((d1 - d2) / MAX_RENDA, 2);
        } else {
          distance += Math.pow(d1 - d2, 2);
        }
      } else {
        distance += value1.equals(value2) ? 0 : 1;
      }
    }
    return Math.sqrt(distance / attributesP1.size());
  }
}