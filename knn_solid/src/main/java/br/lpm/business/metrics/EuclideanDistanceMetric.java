package br.lpm.business.metrics;

import java.util.List;

import br.lpm.business.datamodel.Attribute;
import br.lpm.business.datamodel.DataPoint;

public class EuclideanDistanceMetric implements Metric {

  @Override
  public double distance(DataPoint p1, DataPoint p2) {
    List<Attribute> attributesP1 = p1.getAttributes();
    List<Attribute> attributesP2 = p2.getAttributes();

    if (attributesP1.size() != attributesP2.size()) {
      return -1; 
    }

    double distance = 0;

    for (int i = 0; i < attributesP1.size(); i++) {
      Object value1 = attributesP1.get(i).getValue();
      Object value2 = attributesP2.get(i).getValue();

      if (value1 instanceof Number && value2 instanceof Number) {
        double d1 = ((Number) value1).doubleValue();
        double d2 = ((Number) value2).doubleValue();
        distance += Math.pow(d1 - d2, 2);
      } else {
        distance += value1.equals(value2) ? 0 : 1;
      }
    }
    return Math.sqrt(distance / attributesP1.size());
  }
}