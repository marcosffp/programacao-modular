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

      if (v1 instanceof Float && v2 instanceof Float) {
        double d1 = ((Float) v1).doubleValue();
        double d2 = ((Float) v2).doubleValue();
        distance += Math.pow(Math.abs(d1 - d2), 2);

      } else if (v1 instanceof Integer && v2 instanceof Integer) {
        double d1 = ((Integer) v1).doubleValue();
        double d2 = ((Integer) v2).doubleValue();
        distance += Math.pow(Math.abs(d1 - d2), 2);

      } else {
        distance += Math.pow(v1.equals(v2) ? 0 : 1, 2);
      }
    }
    return Math.sqrt(distance / attributes2.size());
  }
}
