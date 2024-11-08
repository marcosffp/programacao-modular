package br.lpm.business;
import br.lpm.data_structures.Attribute;
import br.lpm.data_structures.DataPoint;
import br.lpm.data_structures.DataSet;
import br.lpm.metrics.EuclideanDistanceMetric;
import br.lpm.metrics.Metric;

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
    dataPoint1.setState(new Attribute("ClasseA"));
    dataPoint1.addAttribute(new Attribute(1.0));

    DataPoint dataPoint2 = new DataPoint();
    dataPoint2.setState(new Attribute("ClasseB"));
    dataPoint2.addAttribute(new Attribute(2.0));

    DataPoint dataPoint3 = new DataPoint();
    dataPoint3.setState(new Attribute("ClasseA"));
    dataPoint3.addAttribute(new Attribute(3.0));

    dataset.addDataPoints(Arrays.asList(dataPoint1, dataPoint2, dataPoint3));

    metric = new EuclideanDistanceMetric();

    int k = 2;
    knnClassifier = new KnnClassifier(dataset, k, metric);
  }

  @Test
  void testPredict() {
    DataPoint newPoint1 = new DataPoint();
    newPoint1.addAttribute(new Attribute(2.5));

    Attribute predictedClass1 = knnClassifier.predict(newPoint1);
    assertEquals("ClasseB", predictedClass1.getValue());

    knnClassifier = new KnnClassifier(dataset, 1, metric);
    DataPoint newPoint2 = new DataPoint();
    newPoint2.addAttribute(new Attribute(2.5));

    Attribute predictedClass2 = knnClassifier.predict(newPoint2);
    assertEquals("ClasseB", predictedClass2.getValue());

    dataset = new DataSet();
    DataPoint dataPoint = new DataPoint();
    dataPoint.setState(new Attribute("ClasseA"));
    dataPoint.addAttribute(new Attribute(1.0));
    dataset.addDataPoints(Arrays.asList(dataPoint));

    knnClassifier = new KnnClassifier(dataset, 1, metric);
    DataPoint newPoint3 = new DataPoint();
    newPoint3.addAttribute(new Attribute(1.5));

    Attribute predictedClass3 = knnClassifier.predict(newPoint3);
    assertEquals("ClasseA", predictedClass3.getValue());
  }
}
