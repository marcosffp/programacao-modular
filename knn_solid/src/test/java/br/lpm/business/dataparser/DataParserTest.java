package br.lpm.business.dataparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataParserTest {
  private DataParser dateParser;
  private DataParser booleanParser;
  private DataParser numericParser;

  @BeforeEach
  void setUp() {
    dateParser = new DateParser();
    booleanParser = new BooleanParser();
    numericParser = new NumericParser();
  }

  @Test
  void testParse() {
    assertEquals(LocalDate.of(2024, 11, 3), dateParser.parse("11/03/2024"),
        "Testando parse de data no formato d/M/yyyy: Deve retornar a data correta.");
    assertNull(dateParser.parse("2024/11/03"), "Testando parse de data com formato inválido: Deve retornar null.");

    assertEquals(true, booleanParser.parse("Sim"), "Testando parse de boolean para 'Sim': Deve retornar verdadeiro.");
    assertEquals(true, booleanParser.parse("true"), "Testando parse de boolean para 'true': Deve retornar verdadeiro.");
    assertEquals(false, booleanParser.parse("Não"), "Testando parse de boolean para 'Não': Deve retornar falso.");
    assertEquals(false, booleanParser.parse("false"), "Testando parse de boolean para 'false': Deve retornar falso.");
    assertNull(booleanParser.parse("Talvez"), "Testando parse de boolean com entrada inválida.");

    assertEquals(10.0, (double) numericParser.parse("10"), 1e-6,
        "Testando parse de número inteiro: Deve retornar 10.0.");
    assertEquals(10.5, (double) numericParser.parse("10,5"), 1e-6,
        "Testando parse de número double com vírgula: Deve retornar 10.5.");
    assertEquals(10.5, (double) numericParser.parse("10.5"), 1e-6,
        "Testando parse de número double com ponto: Deve retornar 10.5.");
    assertNull(numericParser.parse("dez"), "Testando parse de número com entrada inválida.");
  }

}
