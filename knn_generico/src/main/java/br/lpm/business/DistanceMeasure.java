package br.lpm.business;

import java.util.List;

public class DistanceMeasure implements Metric {

  private static final int INVALID = -1;

  @Override
  public double distance(DataPoint p1, DataPoint p2) {
    List<Attribute> attributes1 = p1.getAttributes();
    List<Attribute> attributes2 = p2.getAttributes();

    if (attributes1.size() != attributes2.size()) {
      return INVALID;
    }

    double distance = 0;
    for (int i = 0; i < attributes1.size(); i++) {
      Object v1 = attributes1.get(i).getValue();
      Object v2 = attributes2.get(i).getValue();

      if (v1 instanceof Number && v2 instanceof Number) {
        double d1 = ((Number) v1).doubleValue();
        double d2 = ((Number) v2).doubleValue();
        distance += Math.pow(d1 - d2, 2);
      } else {
        distance += v1.equals(v2) ? 0 : 1;
      }
    }
    return Math.sqrt(distance) / attributes1.size(); 
  }
}
