package br.lpm.business.datamodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class NormalizedDataSetTest {
  private NormalizedDataSet normalizedDataSet;
  private BaseDataPoint dataPoint1;
  private BaseDataPoint dataPoint2;
  private BaseAttribute attribute1;
  private BaseAttribute attribute2;

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
  void testNormalizeField() {
    normalizedDataSet.normalizeField("testAttribute");
    assertEquals(0.0, dataPoint1.getAttributes().get(0).getValue(), 1e-6, "Testando valor normalizado mínimo");
    assertEquals(1.0, dataPoint2.getAttributes().get(0).getValue(), 1e-6, "Testando valor normalizado máximo");
    normalizedDataSet.normalizeField("invalidAttribute");
    assertEquals(0.0, dataPoint1.getAttributes().get(0).getValue(), 1e-6,
        "Testando que valor normalizado permanece inalterado");
    assertEquals(1.0, dataPoint2.getAttributes().get(0).getValue(), 1e-6,
        "Testando que valor normalizado permanece inalterado");
  }

}
