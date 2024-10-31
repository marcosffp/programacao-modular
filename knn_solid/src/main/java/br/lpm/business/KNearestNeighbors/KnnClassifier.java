package br.lpm.business.KNearestNeighbors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.lpm.business.datamodel.BaseDataSet;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.metrics.Metric;

public class KnnClassifier implements Knn<String> {
  private BaseDataSet dataset;
  private Metric metric;
  private int k;

  public KnnClassifier(BaseDataSet dataset, Metric metric, int k) {
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
  public String predict(DataPoint attributes) {
    List<DataPoint> pontosDeDados = dataset.getDataPoints();
    int numeroDePontos = pontosDeDados.size();
    List<Double> distancias = this.calculateDistances(attributes);

    Integer[] indices = new Integer[numeroDePontos];
    for (int i = 0; i < numeroDePontos; i++) {
      indices[i] = i;
    }

    Arrays.sort(indices, Comparator.comparingDouble(i -> distancias.get(i)));

    Map<Object, Integer> contagemDeClasses = new HashMap<>();

    for (int i = 0; i < k; i++) {
      Integer contagemAtual = contagemDeClasses.get(pontosDeDados.get(indices[i]).getState());
      if (contagemAtual == null) {
        contagemDeClasses.put(pontosDeDados.get(indices[i]).getState(), 1);
      } else {
        contagemDeClasses.put(pontosDeDados.get(indices[i]).getState(), contagemAtual + 1);
      }
    }

    Set<Map.Entry<Object, Integer>> conjuntoDeEntradas = contagemDeClasses.entrySet();
    Map.Entry<Object, Integer> vencedor = conjuntoDeEntradas.stream()
        .max((entrada1, entrada2) -> entrada1.getValue().compareTo(entrada2.getValue()))
        .get();

    return (String) vencedor.getKey();
  }

}
