package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class DataPointTest {
  private DataPoint dataPoint;
  private Attribute attribute1;
  private Attribute attribute2;

  @BeforeEach
  void setUp() {
    dataPoint = new DataPoint();
    attribute1 = new Attribute("attribute1");
    attribute2 = new Attribute("attribute2");
  }

  @Test
  void testAddAttribute() {
    dataPoint.addAttribute(attribute1);

    assertEquals(1, dataPoint.getAttributes().size(), "Deveria haver 1 atributo adicionado.");
    assertEquals(attribute1, dataPoint.getAttribute(0), "O atributo adicionado deve ser o mesmo que o recuperado.");
  }

  @Test
  void testAddAttributes() {
    dataPoint.addAttributes(Arrays.asList(attribute1, attribute2));

    assertEquals(2, dataPoint.getAttributes().size(), "Deveriam haver 2 atributos adicionados.");
    assertEquals(attribute1, dataPoint.getAttribute(0), "O primeiro atributo deve ser attribute1.");
    assertEquals(attribute2, dataPoint.getAttribute(1), "O segundo atributo deve ser attribute2.");
  }

  @Test
  void testGetAttribute() {
    dataPoint.addAttribute(attribute1);

    assertEquals(attribute1, dataPoint.getAttribute(0), "O atributo na posição 0 deve ser o mesmo que o adicionado.");
  }

  @Test
  void testGetAttributes() {
    dataPoint.addAttributes(Arrays.asList(attribute1, attribute2));

    assertEquals(2, dataPoint.getAttributes().size(), "Deveriam haver 2 atributos.");
  }

  @Test
  void testGetState() {
    Object expectedState = "active";
    dataPoint.setState(expectedState);

    assertEquals(expectedState, dataPoint.getState(), "O estado deve ser o mesmo que foi definido.");
  }

  @Test
  void testParse() throws InvalidFormatException {
    assertEquals(true, dataPoint.parse("Sim"), "O input 'Sim' deve ser interpretado como true.");
    assertEquals(false, dataPoint.parse("Não"), "O input 'Não' deve ser interpretado como false.");
    assertEquals(LocalDate.of(2023, 10, 28), dataPoint.parse("10/28/2023"),
        "O formato 'M/d/yyyy' deve ser corretamente interpretado.");
    assertEquals(LocalDate.of(2023, 10, 28), dataPoint.parse("28/10/2023"),
        "O formato 'd/M/yyyy' deve ser corretamente interpretado.");
    assertEquals(123.45f, dataPoint.parse("123,45"), "O input '123,45' deve ser interpretado como Float.");
    assertEquals(123, dataPoint.parse("123"), "O input '123' deve ser interpretado como Integer.");
    assertEquals("abc", dataPoint.parse("abc"), "O input não correspondente deve ser retornado como String.");
  }

  @Test
  void testSetState() {
    Object state = "inactive";
    dataPoint.setState(state);

    assertEquals(state, dataPoint.getState(), "O estado deve ser 'inactive'.");
  }
}
