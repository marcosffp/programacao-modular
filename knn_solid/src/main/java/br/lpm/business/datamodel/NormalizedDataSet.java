package br.lpm.business.datamodel;

import java.util.List;
import java.util.OptionalDouble;

public class NormalizedDataSet extends BaseDataSet {

  public void normalizeField(String fieldName) {
    List<String> attributeNames = super.getAttributeNames();
    int index = attributeNames.indexOf(fieldName);

    if (index == -1) {
      return;
    }

    List<DataPoint> dataPoints = super.getDataPoints();
    OptionalDouble minOpt = dataPoints.stream()
        .mapToDouble(dp -> (Double) dp.getAttributes().get(index).getValue())
        .min();
    OptionalDouble maxOpt = dataPoints.stream()
        .mapToDouble(dp -> (Double) dp.getAttributes().get(index).getValue())
        .max();

    if (!minOpt.isPresent() || !maxOpt.isPresent()) {
      return;
    }

    double min = minOpt.getAsDouble();
    double max = maxOpt.getAsDouble();
    double range = max - min;

    for (DataPoint dataPoint : dataPoints) {
      double value = (Double) dataPoint.getAttributes().get(index).getValue();
      double normalizedValue = (value - min) / range;
      dataPoint.getAttributes().get(index).setValue(normalizedValue);
    }
  }

  @Override
  public String toString() {
    return "Normalized DataSet: " + super.toString();
  }
}
