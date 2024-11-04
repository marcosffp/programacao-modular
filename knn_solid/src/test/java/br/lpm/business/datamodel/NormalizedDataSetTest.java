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

    normalizedDataSet.addDataPoints(List.of(dataPoint1, dataPoint2));
    normalizedDataSet.addAttributeName("testAttribute");
  }

  @Test
  void testNormalize() {
    List<DataPoint> normalizedDataPoints = normalizedDataSet.normalizeData(normalizedDataSet.getDataPoints());

    assertNotNull(normalizedDataPoints, "Testando a lista de dados normalizados não deve ser nula.");
    assertEquals(2, normalizedDataPoints.size(), "Testando o valor de dois pontos de dados normalizados.");

    assertEquals(0.0, normalizedDataPoints.get(0).getAttributes().get(0).getValue(), 1e-6,
        "Testando valor normalizado mínimo");
    assertEquals(1.0, normalizedDataPoints.get(1).getAttributes().get(0).getValue(), 1e-6,
        "Testando valor normalizado máximo");
  }
}
