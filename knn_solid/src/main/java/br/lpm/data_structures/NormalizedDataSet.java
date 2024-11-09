package br.lpm.data_structures;

public class NormalizedDataSet extends DataSet {

  public NormalizedDataSet() {
    super();
  }

  public DataSet normalize() {
    NormalizedDataSet normalizedDataSet = new NormalizedDataSet();
    for (DataPoint dataPoint : super.getDataPoints()) {
      DataPoint normalizedDataPoint = normalizeDataPoint(dataPoint);
      normalizedDataSet.addDataPoint(normalizedDataPoint);
    }
    normalizedDataSet.addAttributeNames(super.getAttributeNames());

    return normalizedDataSet;
  }

  private DataPoint normalizeDataPoint(DataPoint dataPoint) {
    DataPoint normalizedDataPoint = new DataPoint();
    for (Attribute attribute : dataPoint.getAttributes()) {
      if (attribute.getValue() instanceof Number) {
        normalizedDataPoint.addAttribute(new Attribute(normalizeValue((Number) attribute.getValue())));
      } else {
        normalizedDataPoint.addAttribute(attribute);
      }
    }
    normalizedDataPoint.setState(dataPoint.getState());
    return normalizedDataPoint;
  }

  private double normalizeValue(Number value) {
    return (value.doubleValue() - getMinValue()) / (getMaxValue() - getMinValue());
  }

  private double getMinValue() {
    double min = Double.MAX_VALUE;
    for (DataPoint dataPoint : super.getDataPoints()) {
      for (Attribute attribute : dataPoint.getAttributes()) {
        if (attribute.getValue() instanceof Number) {
          double value = ((Number) attribute.getValue()).doubleValue();
          min = Math.min(min, value);
        }
      }
    }
    return min;
  }

  private double getMaxValue() {
    double max = Double.MIN_VALUE;
    for (DataPoint dataPoint : super.getDataPoints()) {
      for (Attribute attribute : dataPoint.getAttributes()) {
        if (attribute.getValue() instanceof Number) {
          double value = ((Number) attribute.getValue()).doubleValue();
          max = Math.max(max, value);
        }
      }
    }
    return max;
  }

  @Override
  public String toString() {
    return "NormalizedDataSet{" + super.toString() + "}";
  }
}
