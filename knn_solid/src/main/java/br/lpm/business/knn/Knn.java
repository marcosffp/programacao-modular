package br.lpm.business.knn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.lpm.business.dataset.DataSet;
import br.lpm.business.metric.Metric;
import br.lpm.business.model.Attribute;
import br.lpm.business.model.DataPoint;


public abstract class Knn {
  private DataSet dataset;
  private int k;
  private Metric metric;

  public DataSet getDataset() {
    return dataset;
  }

  public Metric getMetric() {
    return metric;
  }

  public int getK() {
    return k;
  }

  public Knn(DataSet dataset, int k, Metric metric) {
    this.dataset = dataset;
    this.k = k;
    this.metric = metric;
  }

  protected List<Double> calculateDistances(DataPoint testPoint) {

    List<Double> distances = new ArrayList<Double>(dataset.size());

    List<DataPoint> dpList = dataset.getDataPoints();
    for (DataPoint dp : dpList) {
      distances.add(metric.distance(dp, testPoint));
    }
    return distances;
  }

  protected List<Integer> getNearest(List<Double> distances) {

    int size = dataset.size();
    List<Integer> indices = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      indices.add(i, i);
    }
    Collections.sort(indices, Comparator.comparingDouble(i -> distances.get(i)));

    return indices;
  }

  public abstract Attribute predict(DataPoint testPoint);

}