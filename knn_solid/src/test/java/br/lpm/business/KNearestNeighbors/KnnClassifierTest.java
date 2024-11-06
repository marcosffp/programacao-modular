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
    dataPoint1.setState(new Attribute("ClasseA")); // Corrigido para passar um Attribute
    dataPoint1.addAttribute(new Attribute(1.0));

    DataPoint dataPoint2 = new DataPoint();
    dataPoint2.setState(new Attribute("ClasseB")); // Corrigido para passar um Attribute
    dataPoint2.addAttribute(new Attribute(2.0));

    DataPoint dataPoint3 = new DataPoint();
    dataPoint3.setState(new Attribute("ClasseA")); // Corrigido para passar um Attribute
    dataPoint3.addAttribute(new Attribute(3.0));

    dataset.addDataPoints(Arrays.asList(dataPoint1, dataPoint2, dataPoint3));

    metric = new EuclideanDistanceMetric();

    int k = 2;
    knnClassifier = new KnnClassifier(dataset, k, metric);
  }

  @Test
  void testPredict() {
    // Teste 1: Prevendo a classe de um novo ponto
    DataPoint newPoint1 = new DataPoint();
    newPoint1.addAttribute(new Attribute(2.5));

    Attribute predictedClass1 = knnClassifier.predict(newPoint1);
    assertEquals("ClasseB", predictedClass1.getValue(),
        "Testando a previsão de classe não corresponde à esperada no cenário padrão.");

    // Teste 2: Prevendo a classe com K=1
    knnClassifier = new KnnClassifier(dataset, 1, metric);
    DataPoint newPoint2 = new DataPoint();
    newPoint2.addAttribute(new Attribute(2.5));

    Attribute predictedClass2 = knnClassifier.predict(newPoint2);
    assertEquals("ClasseB", predictedClass2.getValue(),
        "Testando a previsão de classe não corresponde à esperada no cenário com K=1.");

    // Teste 3: Prevendo a classe com um dataset contendo apenas um ponto
    dataset = new DataSet();
    DataPoint dataPoint = new DataPoint();
    dataPoint.setState(new Attribute("ClasseA")); // Corrigido para passar um Attribute
    dataPoint.addAttribute(new Attribute(1.0));
    dataset.addDataPoints(Arrays.asList(dataPoint));

    knnClassifier = new KnnClassifier(dataset, 1, metric);
    DataPoint newPoint3 = new DataPoint();
    newPoint3.addAttribute(new Attribute(1.5));

    Attribute predictedClass3 = knnClassifier.predict(newPoint3);
    assertEquals("ClasseA", predictedClass3.getValue(),
        "Testando a previsão de classe não corresponde à esperada no cenário de dataset com único ponto.");
  }
}
