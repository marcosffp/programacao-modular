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

public class KnnClassifierTest {

  private DataSet dataset;
  private Metric metric;
  private KnnClassifier knnClassifier;

  @BeforeEach
  void setup() {
    dataset = new DataSet();

    DataPoint dataPoint1 = new DataPoint();
    dataPoint1.setState("ClasseA");
    dataPoint1.addAttribute(new Attribute(1.0));

    DataPoint dataPoint2 = new DataPoint();
    dataPoint2.setState("ClasseB");
    dataPoint2.addAttribute(new Attribute(2.0));

    DataPoint dataPoint3 = new DataPoint();
    dataPoint3.setState("ClasseA");
    dataPoint3.addAttribute(new Attribute(3.0));

    dataset.addDataPoints(Arrays.asList(dataPoint1, dataPoint2, dataPoint3));

    metric = new EuclideanDistanceMetric();

    int k = 2;
    knnClassifier = new KnnClassifier(dataset, metric, k);
  }

  @Test
  void testPredict() {
    DataPoint newPoint1 = new DataPoint();
    newPoint1.addAttribute(new Attribute(2.5));

    String predictedClass1 = knnClassifier.predict(newPoint1);
    assertEquals("ClasseB", predictedClass1, "Testando a previsão de classe não corresponde à esperada no cenário padrão.");
    knnClassifier = new KnnClassifier(dataset, metric, 1);

    DataPoint newPoint2 = new DataPoint();
    newPoint2.addAttribute(new Attribute(2.5));

    String predictedClass2 = knnClassifier.predict(newPoint2);
    assertEquals("ClasseB", predictedClass2, "Testando a previsão de classe não corresponde à esperada no cenário com K=1.");
    dataset = new DataSet();
    DataPoint dataPoint = new DataPoint();
    dataPoint.setState("ClasseA");
    dataPoint.addAttribute(new Attribute(1.0));
    dataset.addDataPoints(Arrays.asList(dataPoint));

    knnClassifier = new KnnClassifier(dataset, metric, 1);

    DataPoint newPoint3 = new DataPoint();
    newPoint3.addAttribute(new Attribute(1.5));

    String predictedClass3 = knnClassifier.predict(newPoint3);
    assertEquals("ClasseA", predictedClass3,
        "Testando a  previsão de classe não corresponde à esperada no cenário de dataset com único ponto.");
  }

}
