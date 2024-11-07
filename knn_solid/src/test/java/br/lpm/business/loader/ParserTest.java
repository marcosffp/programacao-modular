package br.lpm.business.loader;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

  @Test
  void testParse() {
    String inputInt = "123";
    Object resultInt = Parser.parse(inputInt);
    assertEquals(123, resultInt, "Testando o valor retornado deve ser um inteiro.");

    String inputDouble = "123.45";
    Object resultDouble = Parser.parse(inputDouble);
    assertEquals(123.45, resultDouble, "Testando o valor retornado deve ser um número decimal.");

    String inputBooleanTrue = "sim";
    Object resultBooleanTrue = Parser.parse(inputBooleanTrue);
    assertTrue((Boolean) resultBooleanTrue, "Testando o valor retornado deve ser verdadeiro.");

    String inputBooleanFalse = "não";
    Object resultBooleanFalse = Parser.parse(inputBooleanFalse);
    assertFalse((Boolean) resultBooleanFalse, "Testando o valor retornado deve ser falso.");

    String inputDate1 = "23/06/1999";
    Object resultDate1 = Parser.parse(inputDate1);
    assertEquals(LocalDate.of(1999, 6, 23), resultDate1, "Testando a data deve ser interpretada corretamente.");

    String inputDate2 = "06/23/1999";
    Object resultDate2 = Parser.parse(inputDate2);
    assertEquals(LocalDate.of(1999, 6, 23), resultDate2, "Testando a data no formato MM/dd/yyyy deve ser interpretada corretamente.");

    String inputString = "Texto simples";
    Object resultString = Parser.parse(inputString);
    assertEquals("Texto simples", resultString, "Testando o input não for um tipo reconhecido, deve retornar a string original.");

    String inputInvalidDate = "32/13/2023";
    Object resultInvalidDate = Parser.parse(inputInvalidDate);
    assertEquals("32/13/2023", resultInvalidDate, "Testando uma data inválida deve ser tratada como uma string.");
  }
}
