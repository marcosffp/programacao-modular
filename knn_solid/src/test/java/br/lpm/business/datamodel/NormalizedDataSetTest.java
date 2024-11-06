package br.lpm.business.datamodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class NormalizedDataSetTest {
  private NormalizedDataSet normalizedDataSet;
  private DataPoint dataPoint1;
  private DataPoint dataPoint2;
  private Attribute attribute1;
  private Attribute attribute2;

  @BeforeEach
  void setUp() {
    normalizedDataSet = new NormalizedDataSet();
    attribute1 = new Attribute(10.0); 
    attribute2 = new Attribute(20.0); 
    dataPoint1 = new DataPoint();
    dataPoint2 = new DataPoint();
    dataPoint1.addAttribute(attribute1);
    dataPoint2.addAttribute(attribute2);
    normalizedDataSet.addDataPoint(dataPoint1);
    normalizedDataSet.addDataPoint(dataPoint2);
    normalizedDataSet.addAttributeName("testAttribute");
  }

  @Test
  void testNormalize() {
    DataSet result = normalizedDataSet.normalize();
    List<DataPoint> normalizedDataPoints = result.getDataPoints();

    assertNotNull(normalizedDataPoints, "Testando a lista de dados normalizados não deve ser nula.");
    assertEquals(2, normalizedDataPoints.size(), "Deve haver dois pontos de dados normalizados.");
    assertEquals(0.0, normalizedDataPoints.get(0).getAttributes().get(0).getValue(), 1e-6,
        "Testando o valor normalizado mínimo deve ser 0.0");
    assertEquals(1.0, normalizedDataPoints.get(1).getAttributes().get(0).getValue(), 1e-6,
        "Testando o valor normalizado máximo deve ser 1.0");
  }
}
