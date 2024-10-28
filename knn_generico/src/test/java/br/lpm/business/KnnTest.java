package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KnnTest {

  private Knn knn;
  private DataSet dataset;
  private Metric metric;
  private DataPoint dataPoint;

  @BeforeEach
  void setUp() {
    dataset = new DataSet();
    metric = new FelizMetric();
    dataPoint = new DataPoint();
    dataPoint.setState("Class1");
    dataPoint.addAttribute(new Attribute(1.0));
    dataset.addDataPoint(dataPoint);
    knn = new Knn(dataset, metric, 1);
  }

  @Test
  void testClassify() {
    DataPoint novoDataPoint = new DataPoint();
    novoDataPoint.addAttribute(new Attribute(1.0));

    String resultadoClassificacao = knn.classify(novoDataPoint);
    assertEquals("Class1", resultadoClassificacao, "Classificação incorreta para o ponto de dados.");
  }

  @Test
  void testGetDataset() {
    assertEquals(dataset, knn.getDataset(), "O dataset retornado é diferente do esperado.");
  }

  @Test
  void testGetK() {
    assertEquals(1, knn.getK(), "O valor de k retornado é diferente do esperado.");
  }

  @Test
  void testGetMetric() {
    assertEquals(metric, knn.getMetric(), "A métrica retornada é diferente da esperada.");
  }

  @Test
  void testSetDataset() {
    DataSet novoDataset = new DataSet();
    knn.setDataset(novoDataset);
    assertEquals(novoDataset, knn.getDataset(), "O dataset não foi atualizado corretamente.");
  }

  @Test
  void testSetK() {
    knn.setK(2);
    assertEquals(1, knn.getK(), "O valor de k foi aceito incorretamente para um valor maior que o tamanho do dataset.");

    dataset.addDataPoint(new DataPoint());
    knn.setK(2);
    assertEquals(2, knn.getK(), "O valor de k não foi atualizado corretamente.");
  }

  @Test
  void testSetMetric() {
    Metric novaMetric = new FelizMetric();
    knn.setMetric(novaMetric);
    assertEquals(novaMetric, knn.getMetric(), "A métrica não foi atualizada corretamente.");
  }
}
