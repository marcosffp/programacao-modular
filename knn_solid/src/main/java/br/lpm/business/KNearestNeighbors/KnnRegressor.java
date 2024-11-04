package br.lpm.business.KNearestNeighbors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.datamodel.DataSet;
import br.lpm.business.metrics.Metric;

public class KnnRegressor implements Knn<Double> {
  private DataSet dataset;
  private Metric metric;
  private int k;

  public KnnRegressor(DataSet dataset, Metric metric, int k) {
    this.dataset = dataset;
    this.metric = metric;
    this.k = k;
  }

  private List<Double> calculateDistances(DataPoint attributes) {
    List<Double> distances = new ArrayList<>();
    for (DataPoint dataPoint : dataset.getDataPoints()) {
      distances.add(metric.distance(attributes, dataPoint));
    }
    return distances;
  }

  @Override
  public Double predict(DataPoint testPoint) {
    List<DataPoint> dataPoints = dataset.getDataPoints();
    List<Double> distances = this.calculateDistances(testPoint);

    Integer[] indices = new Integer[dataPoints.size()];
    for (int i = 0; i < dataPoints.size(); i++) {
      indices[i] = i;
    }

    Arrays.sort(indices, (i1, i2) -> {
      int compareDistance = Double.compare(distances.get(i1), distances.get(i2));
      if (compareDistance == 0) {
        Double stateValue1 = tryParseDouble(dataPoints.get(i1).getState());
        Double stateValue2 = tryParseDouble(dataPoints.get(i2).getState());
        return Double.compare(stateValue2 != null ? stateValue2 : Double.MIN_VALUE,
            stateValue1 != null ? stateValue1 : Double.MIN_VALUE);
      }
      return compareDistance;
    });

    double sumState = 0;
    for (int n = 0; n < k; n++) {
      Double stateValue = tryParseDouble(dataPoints.get(indices[n]).getState());
      if (stateValue != null) {
        sumState += stateValue;
      }
    }

    return sumState / k;
  }

  private Double tryParseDouble(Object value) {
    try {
      return Double.parseDouble(value.toString());
    } catch (NumberFormatException e) {
      return null; 
    }
  }

}
