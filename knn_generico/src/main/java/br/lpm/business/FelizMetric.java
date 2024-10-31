package br.lpm.business;

import java.util.List;

public class FelizMetric implements Metric {

  private Normalize normalize;

  @Override
  public double distance(DataPoint p1, DataPoint p2) {
    double[] minMaxValues = normalize.calculateMinMax(p1);
    double minAltura = minMaxValues[0];
    double maxAltura = minMaxValues[1];
    double minPeso = minMaxValues[2];
    double maxPeso = minMaxValues[3];
    double minRenda = minMaxValues[4];
    double maxRenda = minMaxValues[5];

    List<Attribute> ap1 = p1.getAttributes();
    List<Attribute> ap2 = p2.getAttributes();

    double distance = 0;

    for (int i = 0; i < ap1.size(); i++) {
      Object vp1 = ap1.get(i).getValue();
      Object vp2 = ap2.get(i).getValue();

      if (vp1 instanceof Number && vp2 instanceof Number) {
        double normalizedDifference = 0;
        switch (i) {
          case 3: 
            normalizedDifference = normalizeValueDifference((Double) vp1, (Double) vp2, minAltura, maxAltura);
            break;
          case 4: 
            normalizedDifference = normalizeValueDifference((Double) vp1, (Double) vp2, minPeso, maxPeso);
            break;
          case 5: 
            normalizedDifference = normalizeValueDifference((Double) vp1, (Double) vp2, minRenda, maxRenda);
            break;
        }
        distance += normalizedDifference;
      } else {
        distance += vp1.equals(vp2) ? 0 : 1;
      }
    }

    return distance;
  }

  private double normalizeValueDifference(double v1, double v2, double min, double max) {
    return Math.abs((v1 - min) / (max - min) - (v2 - min) / (max - min));
  }
}
