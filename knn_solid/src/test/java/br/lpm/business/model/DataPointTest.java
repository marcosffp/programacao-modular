package br.lpm.business.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class DataPointTest {
  private DataPoint dataPoint;
  private Attribute attribute;
  private Attribute attribute2;
  private Attribute attribute3;

  @BeforeEach
  void setUp() {
    dataPoint = new DataPoint();
    attribute = new Attribute("testValue");
    attribute2 = new Attribute("testValue2");
    attribute3 = new Attribute("testValue3");
  }

  @Test
  void testAddAttribute() {
    attribute.setValue("testValue");
    dataPoint.addAttribute(attribute);
    assertEquals("testValue", dataPoint.getAttribute("testValue").getValue(), "Testando adição de um atributo.");
  }

  @Test
  void testAddAttributes() {
    attribute.setValue("testValue");
    attribute2.setValue("testValue2");
    attribute3.setValue("testValue3");
    dataPoint.addAttributes(List.of(attribute, attribute2, attribute3));
    assertEquals("testValue", dataPoint.getAttribute("testValue").getValue(),
        "Testando adição do primeiro atributo.");
    assertEquals("testValue2", dataPoint.getAttribute("testValue2").getValue(),
        "Testando adição do segundo atributo.");
    assertEquals("testValue3", dataPoint.getAttribute("testValue3").getValue(),
        "Testando adição do terceiro atributo.");
  }

  @Test
  void testClearAttributes() {
    dataPoint.addAttributes(List.of(attribute, attribute2, attribute3));
    dataPoint.clearAttributes();
    assertEquals(0, dataPoint.sizeAttribute(), "Testando limpeza de todos os atributos.");
  }

  @Test
  void testGetAttribute() {
    attribute.setValue("testValue");
    dataPoint.addAttributes(List.of(attribute, attribute2, attribute3));
    assertEquals("testValue", dataPoint.getAttribute("testValue").getValue(),
        "Testando obtenção de um atributo específico.");
  }

  @Test
  void testGetAttributes() {
    attribute.setValue("testValue");
    attribute2.setValue("testValue2");
    attribute3.setValue("testValue3");
    dataPoint.addAttributes(List.of(attribute, attribute2, attribute3));
    assertEquals("testValue", dataPoint.getAttribute("testValue").getValue(),
        "Testando obtenção do primeiro atributo.");
    assertEquals("testValue2", dataPoint.getAttribute("testValue2").getValue(),
        "Testando obtenção do segundo atributo.");
    assertEquals("testValue3", dataPoint.getAttribute("testValue3").getValue(),
        "Testando obtenção do terceiro atributo.");
  }

  @Test
  void testGetState() {
    dataPoint.setState(new Attribute("testState"));
    assertEquals("testState", dataPoint.getState().getValue(), "Testando definição e obtenção do estado.");
  }

  @Test
  void testRemoveAttribute() {
    dataPoint.addAttributes(List.of(attribute, attribute2, attribute3));
    dataPoint.removeAttribute(attribute);
    assertEquals(2, dataPoint.sizeAttribute(), "Testando remoção de um atributo pelo objeto.");
  }

  @Test
  void testRemoveAttributeByName() {
    attribute.setValue("testAttribute");
    dataPoint.addAttributes(List.of(attribute, attribute2, attribute3));
    dataPoint.removeAttribute("testAttribute");
    assertEquals(2, dataPoint.sizeAttribute(), "Testando remoção de um atributo pelo nome.");
  }

  @Test
  void testRemoveAttributeByIndex() {
    dataPoint.addAttributes(List.of(attribute, attribute2, attribute3));
    dataPoint.removeAttribute(0);
    assertEquals(2, dataPoint.sizeAttribute(), "Testando remoção de um atributo pela posição.");
  }

  @Test
  void testSetState() {
    dataPoint.setState(new Attribute("testState"));
    assertEquals("testState", dataPoint.getState().getValue(), "Testando definição e obtenção do estado.");
  }

  @Test
  void testSizeAttribute() {
    dataPoint.addAttributes(List.of(attribute, attribute2, attribute3));
    assertEquals(3, dataPoint.sizeAttribute(), "Testando contagem de atributos após adição.");
  }
}
