package br.lpm.data_structures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AttributeTest {
  private Attribute attribute;

  @BeforeEach
  void setUp() {
    attribute = new Attribute("testValue");
  }

  @Test
  void testGetValue() {
    assertEquals("testValue", attribute.getValue(), "Testando obtenção do valor inicial do atributo.");
  }

  @Test
  void testSetValue() {
    attribute.setValue("newValue");
    assertEquals("newValue", attribute.getValue(), "Testando alteração do valor do atributo.");
  }
}
