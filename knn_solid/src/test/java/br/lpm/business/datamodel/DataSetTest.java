package br.lpm.business.datamodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataSetTest {
  private DataSet dataSet;
  private DataPoint dataPoint;
  private DataPoint dataPoint2;
  private DataPoint dataPoint3;

  @BeforeEach
  void setUp() {
    dataSet = new DataSet();
    dataPoint = new DataPoint();
    dataPoint2 = new DataPoint();
    dataPoint3 = new DataPoint();
  }

  @Test
  void testAddAttributeName() {
    dataSet.addAttributeName("testAttribute");
    assertEquals("testAttribute", dataSet.getAttributeName(0), "Testando adição de um atributo na posição 0.");
  }

  @Test
  void testAddAttributeNames() {
    dataSet.addAttributeNames(List.of("testAttribute", "testAttribute2", "testAttribute3"));
    assertEquals("testAttribute", dataSet.getAttributeName(0), "Testando adição do primeiro atributo.");
    assertEquals("testAttribute2", dataSet.getAttributeName(1), "Testando adição do segundo atributo.");
    assertEquals("testAttribute3", dataSet.getAttributeName(2), "Testando adição do terceiro atributo.");
  }

  @Test
  void testAddDataPoint() {
    dataSet.addDataPoint(dataPoint);
    assertEquals(dataPoint, dataSet.getDataPoint(0), "Testando adição de um ponto de dados na posição 0.");
  }

  @Test
  void testAddDataPoints() {
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    assertEquals(dataPoint, dataSet.getDataPoint(0), "Testando adição do primeiro ponto de dados.");
    assertEquals(dataPoint2, dataSet.getDataPoint(1), "Testando adição do segundo ponto de dados.");
    assertEquals(dataPoint3, dataSet.getDataPoint(2), "Testando adição do terceiro ponto de dados.");
  }

  @Test
  void testClearAttributeNames() {
    dataSet.addAttributeNames(List.of("testAttribute", "testAttribute2", "testAttribute3"));
    dataSet.clearAttributeNames();
    assertEquals(0, dataSet.sizeAttributeNames(), "Testando limpeza de todos os atributos.");
  }

  @Test
  void testClearDataPoints() {
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    dataSet.clearDataPoints();
    assertEquals(0, dataSet.sizeDataPoints(), "Testando limpeza de todos os pontos de dados.");
  }

  @Test
  void testGetAttributeName() {
    dataSet.addAttributeNames(List.of("testAttribute", "testAttribute2", "testAttribute3"));
    assertEquals("testAttribute", dataSet.getAttributeName(0), "Testando obtenção do primeiro atributo.");
  }

  @Test
  void testGetAttributeName2() {
    dataSet.addAttributeNames(List.of("testAttribute", "testAttribute2", "testAttribute3"));
    assertEquals("testAttribute", dataSet.getAttributeName("testAttribute"),
        "Testando obtenção do atributo pelo nome.");
  }

  @Test
  void testGetAttributeNames() {
    dataSet.addAttributeNames(List.of("testAttribute", "testAttribute2", "testAttribute3"));
    assertEquals("testAttribute", dataSet.getAttributeName(0), "Testando obtenção do primeiro atributo.");
    assertEquals("testAttribute2", dataSet.getAttributeName(1), "Testando obtenção do segundo atributo.");
    assertEquals("testAttribute3", dataSet.getAttributeName(2), "Testando obtenção do terceiro atributo.");
  }

  @Test
  void testGetDataPoint() {
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    assertEquals(dataPoint, dataSet.getDataPoint(0), "Testando obtenção do primeiro ponto de dados.");
  }

  @Test
  void testGetDataPoint2() {
    dataPoint.setState("testState");
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    assertEquals(dataPoint, dataSet.getDataPoint("testState"), "Testando obtenção do ponto de dados pelo estado.");
  }

  @Test
  void testGetDataPoints() {
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    assertEquals(dataPoint, dataSet.getDataPoint(0), "Testando obtenção do primeiro ponto de dados.");
    assertEquals(dataPoint2, dataSet.getDataPoint(1), "Testando obtenção do segundo ponto de dados.");
    assertEquals(dataPoint3, dataSet.getDataPoint(2), "Testando obtenção do terceiro ponto de dados.");
  }

  @Test
  void testGetStateName() {
    dataSet.setStateName("testState");
    assertEquals("testState", dataSet.getStateName(), "Testando definição e obtenção do nome do estado.");
  }

  @Test
  void testRemoveAttributeName() {
    dataSet.addAttributeNames(List.of("testAttribute", "testAttribute2", "testAttribute3"));
    dataSet.removeAttributeName("testAttribute");
    assertEquals(2, dataSet.sizeAttributeNames(), "Testando remoção do atributo pelo nome.");
  }

  @Test
  void testRemoveAttributeName2() {
    dataSet.addAttributeNames(List.of("testAttribute", "testAttribute2", "testAttribute3"));
    dataSet.removeAttributeName(0);
    assertEquals(2, dataSet.sizeAttributeNames(), "Testando remoção do atributo pela posição.");
  }

  @Test
  void testRemoveDataPoint() {
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    dataSet.removeDataPoint(dataPoint);
    assertEquals(2, dataSet.sizeDataPoints(), "Testando remoção do ponto de dados pelo objeto.");
  }

  @Test
  void testRemoveDataPoint2() {
    dataPoint.setState(2);
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    dataSet.removeDataPoint(2);
    assertEquals(2, dataSet.sizeDataPoints(), "Testando remoção do ponto de dados pelo estado.");
  }

  @Test
  void testRemoveDataPoint3() {
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    dataSet.removeDataPoint(0);
    assertEquals(2, dataSet.sizeDataPoints(), "Testando remoção do ponto de dados pela posição.");
  }

  @Test
  void testSetStateName() {
    dataSet.setStateName("testState");
    assertEquals("testState", dataSet.getStateName(), "Testando definição e obtenção do nome do estado.");
  }

  @Test
  void testSizeAttributeNames() {
    dataSet.addAttributeNames(List.of("testAttribute", "testAttribute2", "testAttribute3"));
    assertEquals(3, dataSet.sizeAttributeNames(), "Testando contagem de atributos após adição.");
  }

  @Test
  void testSizeDataPoints() {
    dataSet.addDataPoints(List.of(dataPoint, dataPoint2, dataPoint3));
    assertEquals(3, dataSet.sizeDataPoints(), "Testando contagem de pontos de dados após adição.");
  }
}
