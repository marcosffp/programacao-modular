package br.lpm.business.knn;

import java.util.List;

import br.lpm.business.dataset.DataSet;
import br.lpm.business.metric.Metric;
import br.lpm.business.model.Attribute;
import br.lpm.business.model.DataPoint;

public class KnnRegressor extends Knn {

  public KnnRegressor(DataSet dataset, int k, Metric metric) {
    super(dataset, k, metric);
  }

  public Attribute predict(DataPoint testPoint) {
    List<DataPoint> dp = getDataset().getDataPoints();
    List<Double> distances = super.calculateDistances(testPoint);
    List<Integer> indexes = super.getNearest(distances);
    double sumState = 0;

    for (int n = 0; n < getK(); n++) {
      try {
        Double stateValue = Double.valueOf(dp.get(indexes.get(n)).getState().getValue().toString());
        sumState += stateValue;
      } catch (NumberFormatException e) {
        System.err.println("Valor não numérico encontrado, ignorando: " + dp.get(indexes.get(n)).getState().getValue());
      }
    }
    return new Attribute(sumState / getK());
  }

}