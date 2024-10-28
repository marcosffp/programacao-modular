package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeTest {
  private Attribute attribute;

  @BeforeEach
  void setUp() {
    attribute = new Attribute("initialValue");
  }

  @Test
  void testGetValue() {
    String value = attribute.getValue();
    assertEquals("initialValue", value, "O valor inicial deveria ser 'initialValue'");
  }

  @Test
  void testSetValue() {
    attribute.setValue("newValue");
    String newValue = attribute.getValue();
    assertEquals("newValue", newValue, "O valor deveria ter sido atualizado para 'newValue'");
  }
}
