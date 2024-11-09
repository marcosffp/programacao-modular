package br.lpm.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.lpm.data_structures.Attribute;
import br.lpm.data_structures.DataPoint;
import br.lpm.data_structures.DataSet;
import br.lpm.metrics.Metric;

public class KnnClassifier extends Knn {

  public KnnClassifier(DataSet dataset, int k, Metric metric) {
    super(dataset, k, metric);
  }

  public Attribute predict(DataPoint testPoint) {
    List<DataPoint> dataPoints = super.getDataset().getDataPoints();
    List<Double> distances = super.calculateDistances(testPoint);
    List<Integer> nearestIndexes = super.getNearest(distances);

    Map<Object, Integer> stateCount = countStateOccurrences(dataPoints, nearestIndexes);
    Object predictedState = findMostFrequentState(stateCount);

    return new Attribute(predictedState);
  }

  private Map<Object, Integer> countStateOccurrences(List<DataPoint> dataPoints, List<Integer> nearestIndexes) {
    Map<Object, Integer> stateCount = new HashMap<>();
    for (int i = 0; i < super.getK(); i++) {
      Object stateValue = dataPoints.get(nearestIndexes.get(i)).getState().getValue();
      stateCount.put(stateValue, stateCount.getOrDefault(stateValue, 0) + 1);
    }
    return stateCount;
  }

  private Object findMostFrequentState(Map<Object, Integer> stateCount) {
    Set<Map.Entry<Object, Integer>> states = stateCount.entrySet();
    return states.stream()
        .max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
        .get()
        .getKey();
  }
}
