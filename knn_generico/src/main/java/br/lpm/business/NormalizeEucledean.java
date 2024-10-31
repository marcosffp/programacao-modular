package br.lpm.business;

import java.util.List;

public class NormalizeEucledean implements Normalize {

  @Override
  public double[] calculateMinMax(DataPoint dataPoint) {
    List<Attribute> attributes = dataPoint.getAttributes();
    double min = Double.MAX_VALUE;
    double max = Double.MIN_VALUE;

    for (Attribute attribute : attributes) {
      double value = (Double) attribute.getValue();
      if (value < min)
        min = value;
      if (value > max)
        max = value;
    }

    return new double[] { min, max };
  }
}
