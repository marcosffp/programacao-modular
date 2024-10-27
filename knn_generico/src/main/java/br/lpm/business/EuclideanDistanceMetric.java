package br.lpm.business;

import java.util.List;

public class EuclideanDistanceMetric implements Metric {

  private static final int INVALID = -1;

  @Override
  public double distance(DataPoint p1, DataPoint p2) {
    if (p1.getAttributes().size() != p2.getAttributes().size()) {
      return INVALID;
    }
    double distance = 0;
    List<Attribute> attributesP1 = p1.getAttributes();
    List<Attribute> attributesP2 = p2.getAttributes();

    for (int i = 0; i <attributesP1.size(); i++) {
      Object value1 = attributesP1.get(i).getValue();
      Object value2 = attributesP2.get(i).getValue();

      if (value1 instanceof Float && value2 instanceof Float) {
        double d1 = (Float) value1;
        double d2 = (Float) value2;
        distance += Math.pow(Math.abs(d1 - d2), 2);
      } else if (value1 instanceof Integer && value2 instanceof Integer) {
        double d1 = (Integer) value1;
        double d2 = (Integer) value2;
        distance += Math.pow(Math.abs(d1 - d2), 2);
      } else {
        distance += value1.equals(value2) ? 0 : 1;
      }
      }
    return Math.sqrt(distance);
  }
}
