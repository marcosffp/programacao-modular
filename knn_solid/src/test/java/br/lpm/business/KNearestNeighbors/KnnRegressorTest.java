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
    dataPoint1.setState(1.0);
    dataPoint1.addAttribute(new Attribute(1.0));

    DataPoint dataPoint2 = new DataPoint();
    dataPoint2.setState(2.0);
    dataPoint2.addAttribute(new Attribute(2.0));

    DataPoint dataPoint3 = new DataPoint();
    dataPoint3.setState(3.0);
    dataPoint3.addAttribute(new Attribute(3.0));

    dataset.addDataPoints(Arrays.asList(dataPoint1, dataPoint2, dataPoint3));

    metric = new EuclideanDistanceMetric();
    knnRegressor = new KnnRegressor(dataset, metric, 2);
  }

  @Test
  void testPredict() {
    DataPoint newPointK2 = new DataPoint();
    newPointK2.addAttribute(new Attribute(2.5));

    Double predictedValueK2 = knnRegressor.predict(newPointK2);
    assertEquals(2.5, predictedValueK2, 0.01, "Testando a previsão com k=2 não corresponde ao valor esperado.");

    knnRegressor = new KnnRegressor(dataset, metric, 1);

    DataPoint newPointK1 = new DataPoint();
    newPointK1.addAttribute(new Attribute(2.5));

    Double predictedValueK1 = knnRegressor.predict(newPointK1);
    assertEquals(3.0, predictedValueK1, 0.01, "Testando a previsão com k=1 não corresponde ao valor esperado.");


    dataset = new DataSet();
    DataPoint singleDataPoint = new DataPoint();
    singleDataPoint.setState(1.0);
    singleDataPoint.addAttribute(new Attribute(1.0));
    dataset.addDataPoints(Arrays.asList(singleDataPoint));

    knnRegressor = new KnnRegressor(dataset, metric, 1);

    DataPoint newPointSingle = new DataPoint();
    newPointSingle.addAttribute(new Attribute(1.5));

    Double predictedValueSingle = knnRegressor.predict(newPointSingle);
    assertEquals(1.0, predictedValueSingle, 0.01,
        "Testando a previsão não corresponde ao valor esperado para dataset com único ponto.");
  }

}
