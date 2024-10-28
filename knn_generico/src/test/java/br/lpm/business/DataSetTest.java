package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class DataSetTest {
  private DataSet dataSet;
  private DataPoint dataPoint1;
  private DataPoint dataPoint2;

  @BeforeEach
  void setUp() {
    dataSet = new DataSet();
    dataPoint1 = new DataPoint();
    dataPoint2 = new DataPoint();
    dataPoint1.addAttribute(new Attribute("value1"));
    dataPoint1.setState("class1");
    dataPoint2.addAttribute(new Attribute("value2"));
    dataPoint2.setState("class2");
  }

  @Test
  void testAddAttributeName() {
    dataSet.addAttributeName("Attribute1");
    assertEquals(1, dataSet.size(), "Deveria haver 1 atributo adicionado.");
    assertEquals("Attribute1", dataSet.getAttributeName(0), "O atributo adicionado deve ser 'Attribute1'.");
  }

  @Test
  void testAddAttributeNames() {
    dataSet.addAttributeNames(Arrays.asList("Attribute1", "Attribute2"));
    assertEquals(2, dataSet.size(), "Deveriam haver 2 atributos adicionados.");
  }

  @Test
  void testAddDataPoint() {
    dataSet.addDataPoint(dataPoint1);
    assertEquals(1, dataSet.numDataPoints(), "Deveria haver 1 DataPoint adicionado.");
    assertEquals(dataPoint1, dataSet.getDataPoint(0), "O DataPoint adicionado deve ser o mesmo que o recuperado.");
  }

  @Test
  void testAddDataPoints() {
    dataSet.addDataPoints(Arrays.asList(dataPoint1, dataPoint2));
    assertEquals(2, dataSet.numDataPoints(), "Deveriam haver 2 DataPoints adicionados.");
  }

  @Test
  void testGetAttributeName() {
    dataSet.addAttributeName("Attribute1");
    assertEquals("Attribute1", dataSet.getAttributeName(0), "O nome do atributo deve ser 'Attribute1'.");
  }

  @Test
  void testGetAttributeNames() {
    dataSet.addAttributeNames(Arrays.asList("Attribute1", "Attribute2"));
    assertEquals(2, dataSet.getAttributeNames().size(), "Deveriam haver 2 atributos.");
  }

  @Test
  void testGetDataPoint() {
    dataSet.addDataPoint(dataPoint1);
    assertEquals(dataPoint1, dataSet.getDataPoint(0), "O DataPoint na posição 0 deve ser o mesmo que o adicionado.");
  }

  @Test
  void testGetDataPoints() {
    dataSet.addDataPoints(Arrays.asList(dataPoint1, dataPoint2));
    assertEquals(2, dataSet.getDataPoints().size(), "Deveriam haver 2 DataPoints.");
  }

  @Test
  void testGetStateName() {
    dataSet.setStateName("MyState");
    assertEquals("MyState", dataSet.getStateName(), "O nome do estado deve ser 'MyState'.");
  }

  @Test
  void testLoadDataFromCSV() {
    File tempFile = null;
    try {
      tempFile = File.createTempFile("test", ".csv");
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
        writer.write("Attribute1;Attribute2;State\n");
        writer.write("10;20;ClassA\n");
        writer.write("30;40;ClassB\n");
      }

      dataSet.loadDataFromCSV(tempFile.getAbsolutePath());

      assertEquals(2, dataSet.numDataPoints(), "Deveria haver 2 DataPoints carregados.");
      assertEquals("Attribute1", dataSet.getAttributeName(0), "O primeiro atributo deveria ser 'Attribute1'.");
      assertEquals("Attribute2", dataSet.getAttributeName(1), "O segundo atributo deveria ser 'Attribute2'.");
      assertEquals("State", dataSet.getStateName(), "O nome do estado deveria ser 'State'.");

      DataPoint loadedPoint1 = dataSet.getDataPoint(0);
      DataPoint loadedPoint2 = dataSet.getDataPoint(1);

      assertEquals(10, (int) loadedPoint1.getAttribute(0).getValue(),
          "O valor do primeiro atributo do primeiro ponto deveria ser '10'.");
      assertEquals("ClassA", loadedPoint1.getState(), "O estado do primeiro ponto deveria ser 'ClassA'.");
      assertEquals(30, (int) loadedPoint2.getAttribute(0).getValue(),
          "O valor do primeiro atributo do segundo ponto deveria ser '30'.");
      assertEquals("ClassB", loadedPoint2.getState(), "O estado do segundo ponto deveria ser 'ClassB'.");

    } catch (IOException | InvalidFormatException e) {
      fail("Erro durante o teste de carregamento de CSV: " + e.getMessage());
    } finally {
      if (tempFile != null && tempFile.exists()) {
        tempFile.delete();
      }
    }
  }

  @Test
  void testNumDataPoints() {
    assertEquals(0, dataSet.numDataPoints(), "Deveria haver 0 DataPoints no início.");
    dataSet.addDataPoint(dataPoint1);
    assertEquals(1, dataSet.numDataPoints(), "Deveria haver 1 DataPoint após a adição.");
  }

  @Test
  void testRemoveAllAttributeNames() {
    dataSet.addAttributeNames(Arrays.asList("Attribute1", "Attribute2"));
    dataSet.removeAllAttributeNames();
    assertEquals(0, dataSet.size(), "Deveriam não haver atributos após remoção.");
  }

  @Test
  void testRemoveAllDataPoints() {
    dataSet.addDataPoints(Arrays.asList(dataPoint1, dataPoint2));
    dataSet.removeAllDataPoints();
    assertEquals(0, dataSet.numDataPoints(), "Deveriam não haver DataPoints após remoção.");
  }

  @Test
  void testRemoveAttribute() {
    dataSet.addAttributeName("Attribute1");
    dataSet.removeAttribute(0);
    assertEquals(0, dataSet.size(), "Deveriam não haver atributos após remoção.");
  }

  @Test
  void testRemoveAttributeName() {
    dataSet.addAttributeName("Attribute1");
    dataSet.removeAttributeName("Attribute1");
    assertEquals(0, dataSet.size(), "Deveriam não haver atributos após remoção.");
  }

  @Test
  void testRemoveDataPoint() {
    dataSet.addDataPoint(dataPoint1);
    dataSet.removeDataPoint(dataPoint1);
    assertEquals(0, dataSet.numDataPoints(), "Deveriam não haver DataPoints após remoção.");
  }

  @Test
  void testRemoveDataPoints() {
    dataSet.addDataPoints(Arrays.asList(dataPoint1, dataPoint2));
    dataSet.removeDataPoints(Arrays.asList(dataPoint1));
    assertEquals(1, dataSet.numDataPoints(), "Deveria haver 1 DataPoint após remoção.");
  }

  @Test
  void testSetStateName() {
    dataSet.setStateName("NewState");
    assertEquals("NewState", dataSet.getStateName(), "O nome do estado deve ser 'NewState'.");
  }

  @Test
  void testSize() {
    assertEquals(0, dataSet.size(), "Deveria haver 0 atributos no início.");
    dataSet.addAttributeName("Attribute1");
    assertEquals(1, dataSet.size(), "Deveria haver 1 atributo após adição.");
  }
}
