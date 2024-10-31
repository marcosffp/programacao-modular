package br.lpm.business;

import java.util.List;

public class NormalizeFeliz implements Normalize {

  @Override
  public double[] calculateMinMax(DataPoint dataPoint) {
    double minAltura = Double.MAX_VALUE, maxAltura = Double.MIN_VALUE;
    double minPeso = Double.MAX_VALUE, maxPeso = Double.MIN_VALUE;
    double minRenda = Double.MAX_VALUE, maxRenda = Double.MIN_VALUE;

    List<Attribute> attributes = dataPoint.getAttributes();
    for (int i = 0; i < attributes.size(); i++) {
      double value = (Double) attributes.get(i).getValue();

      switch (i) {
        case 3: 
          minAltura = Math.min(minAltura, value);
          maxAltura = Math.max(maxAltura, value);
          break;
        case 4: 
          minPeso = Math.min(minPeso, value);
          maxPeso = Math.max(maxPeso, value);
          break;
        case 5: 
          minRenda = Math.min(minRenda, value);
          maxRenda = Math.max(maxRenda, value);
          break;
      }
    }

    return new double[]{minAltura, maxAltura, minPeso, maxPeso, minRenda, maxRenda};
  }
}
