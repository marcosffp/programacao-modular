package br.lpm.business.KNearestNeighbors;

import java.util.List;

import br.lpm.business.datamodel.Attribute;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.datamodel.DataSet;
import br.lpm.business.metrics.Metric;

public class KnnRegressor extends Knn {

  public KnnRegressor(DataSet dataset, int k, Metric metric) {
    super(dataset, k, metric);
  }

  public Attribute predict(DataPoint testPoint) {

    // Passo 1: Calcula as distâncias do ponto de teste para todos os pontos do
    // Dataset
    List<DataPoint> dp = getDataset().getDataPoints();
    List<Double> distances = this.calculateDistances(testPoint);

    // Passo 2: Selecionar os k menores elementos em relação ao testPoint
    List<Integer> indexes = this.getNearest(distances);

    // Passo 3: Calcule a saúde média dos k DataPoints mais próximos
    // e retorne como resultado.
    double sumState = 0;
    for (int n = 0; n < getK(); n++) {
      sumState += (Double) dp.get(indexes.get(n)).getState().getValue();
    }

    return new Attribute(sumState / getK());
  }

}