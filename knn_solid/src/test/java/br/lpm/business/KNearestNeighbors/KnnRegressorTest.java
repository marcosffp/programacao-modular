package br.lpm.business.KNearestNeighbors;

import br.lpm.business.datamodel.Attribute;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.datamodel.DataSet;
import br.lpm.business.metrics.EuclideanDistanceMetric;
import br.lpm.business.metrics.Metric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

public class KnnRegressorTest {

  private DataSet dataset;
  private Metric metric;
  private KnnRegressor knnRegressor;

  @BeforeEach
  void setup() {
    dataset = new DataSet();

    DataPoint dataPoint1 = new DataPoint();
    dataPoint1.setState(new Attribute(1.0));
    dataPoint1.addAttribute(new Attribute(1.0));

    DataPoint dataPoint2 = new DataPoint();
    dataPoint2.setState(new Attribute(2.0));
    dataPoint2.addAttribute(new Attribute(2.0));

    DataPoint dataPoint3 = new DataPoint();
    dataPoint3.setState(new Attribute(3.0));
    dataPoint3.addAttribute(new Attribute(3.0));

    dataset.addDataPoints(Arrays.asList(dataPoint1, dataPoint2, dataPoint3));

    metric = new EuclideanDistanceMetric();

    int k = 2;
    knnRegressor = new KnnRegressor(dataset, k, metric);
  }

  @Test
  void testPredict() {
    DataPoint newPoint1 = new DataPoint();
    newPoint1.addAttribute(new Attribute(2.5));

    Attribute predictedValue1 = knnRegressor.predict(newPoint1);
    assertEquals(2.5, (Double) predictedValue1.getValue(),
        "A previsão não corresponde ao valor esperado no cenário padrão.");

    knnRegressor = new KnnRegressor(dataset, 1, metric);
    DataPoint newPoint2 = new DataPoint();
    newPoint2.addAttribute(new Attribute(2.5));

    Attribute predictedValue2 = knnRegressor.predict(newPoint2);
    assertEquals(2.0, (Double) predictedValue2.getValue(),
        "A previsão não corresponde ao valor esperado no cenário com K=1.");

    // Teste 3: Prevendo o valor com um dataset contendo apenas um ponto
    dataset = new DataSet();
    DataPoint dataPoint = new DataPoint();
    dataPoint.setState(new Attribute(1.0));
    dataPoint.addAttribute(new Attribute(1.0));
    dataset.addDataPoints(Arrays.asList(dataPoint));

    knnRegressor = new KnnRegressor(dataset, 1, metric);
    DataPoint newPoint3 = new DataPoint();
    newPoint3.addAttribute(new Attribute(1.5));

    Attribute predictedValue3 = knnRegressor.predict(newPoint3);
    assertEquals(1.0, (Double) predictedValue3.getValue(),
        "A previsão não corresponde ao valor esperado no cenário de dataset com único ponto.");
  }
}
