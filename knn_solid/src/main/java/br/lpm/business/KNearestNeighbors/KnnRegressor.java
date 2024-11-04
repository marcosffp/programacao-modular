package br.lpm.business.KNearestNeighbors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import br.lpm.business.datamodel.BaseDataPoint;
import br.lpm.business.datamodel.BaseDataSet;
import br.lpm.business.metrics.Metric;

public class KnnRegressor implements Knn<Double> {
  private BaseDataSet dataset;
  private Metric metric;
  private int k;

  public KnnRegressor(BaseDataSet dataset, Metric metric, int k) {
    this.dataset = dataset;
    this.metric = metric;
    this.k = k;
  }

  private List<Double> calculateDistances(BaseDataPoint attributes) {
    List<Double> distances = new ArrayList<>();
    for (BaseDataPoint dataPoint : dataset.getDataPoints()) {
      distances.add(metric.distance(attributes, dataPoint));
    }
    return distances;
  }

  @Override
  public Double predict(BaseDataPoint testPoint) {
    List<BaseDataPoint> dataPoints = dataset.getDataPoints();
    List<Double> distances = this.calculateDistances(testPoint);

    Integer[] indices = new Integer[dataPoints.size()];
    for (int i = 0; i < dataPoints.size(); i++) {
      indices[i] = i;
    }

    Arrays.sort(indices, Comparator.comparingDouble(i -> distances.get(i)));

    double sumState = 0;
    for (int n = 0; n < k; n++) {
      sumState += (double) dataPoints.get(indices[n]).getState();
    }

    return sumState / k;
  }

}
