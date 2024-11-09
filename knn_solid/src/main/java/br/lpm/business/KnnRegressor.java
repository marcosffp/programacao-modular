package br.lpm.business;

import java.util.List;

import br.lpm.data_structures.Attribute;
import br.lpm.data_structures.DataPoint;
import br.lpm.data_structures.DataSet;
import br.lpm.metrics.Metric;

public class KnnRegressor extends Knn {

  public KnnRegressor(DataSet dataset, int k, Metric metric) {
    super(dataset, k, metric);
  }

  public Attribute predict(DataPoint testPoint) {
    List<DataPoint> dp = super.getDataset().getDataPoints();
    List<Double> distances = super.calculateDistances(testPoint);
    List<Integer> indexes = super.getNearest(distances);
    double sumState = 0;

    for (int n = 0; n < super.getK(); n++) {
      try {
        Double stateValue = Double.valueOf(dp.get(indexes.get(n)).getState().getValue().toString());
        sumState += stateValue;
      } catch (NumberFormatException e) {
        System.err.println("Valor não numérico encontrado, ignorando: " + dp.get(indexes.get(n)).getState().getValue());
      }
    }
    return new Attribute(sumState / super.getK());
  }

}