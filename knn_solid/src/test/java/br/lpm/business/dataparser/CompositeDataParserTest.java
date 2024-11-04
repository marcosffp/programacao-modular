package br.lpm.business.dataparser;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeDataParserTest {
  private CompositeDataParser compositeParser;
  private DataParser dateParser;
  private DataParser booleanParser;
  private DataParser numericParser;

  @BeforeEach
  void setUp() {
    compositeParser = new SimpleCompositeDataParser();
    dateParser = new DateParser();
    booleanParser = new BooleanParser();
    numericParser = new NumericParser();

    compositeParser.addParser(dateParser);
    compositeParser.addParser(booleanParser);
    compositeParser.addParser(numericParser);
  }

  @Test
  void testAddParser() {
    DataParser newParser = new NumericParser();
    compositeParser.addParser(newParser);
    assertEquals(4, compositeParser.sizeParsers(),
        "Testando a adição de um novo parser ao compositeParser");
  }

  @Test
  void testAddParsers() {
    DataParser extraParser = new BooleanParser();
    compositeParser.addParsers(Arrays.asList(extraParser));
    assertEquals(4, compositeParser.sizeParsers(),
        "Testando a adição de uma lista de parsers ao compositeParser");
  }

  @Test
  void testGetParsers() {
    assertEquals(3, compositeParser.getParsers().size(),
        "Testando a recuperação dos parsers no compositeParser");
  }

  @Test
  void testGetParserByIndex() {
    assertEquals(dateParser, compositeParser.getParser(0),
        "Testando a recuperação de um parser por índice");
  }

  @Test
  void testGetParserByName() {
    assertEquals(booleanParser, compositeParser.getParser("BooleanParser"),
        "Testando a recuperação de um parser pelo nome");
  }

  @Test
  void testRemoveParserByObject() {
    compositeParser.removeParser(booleanParser);
    assertEquals(2, compositeParser.sizeParsers(),
        "Testando a remoção de um parser pelo objeto");
  }

  @Test
  void testRemoveParserByIndex() {
    compositeParser.removeParser(0);
    assertEquals(2, compositeParser.sizeParsers(),
        "Testando a remoção de um parser pelo índice.");
  }

  @Test
  void testRemoveParserByName() {
    compositeParser.removeParser("NumericParser");
    assertEquals(2, compositeParser.sizeParsers(),
        "Testando a remoção de um parser pelo nome");
  }

  @Test
  void testClearParsers() {
    compositeParser.clearParsers();
    assertEquals(0, compositeParser.sizeParsers(),
        "Testando a limpeza de todos os parsers");
  }

  @Test
  void testSizeParsers() {
    assertEquals(3, compositeParser.sizeParsers(),
        "Testando o tamanho inicial dos parsers");
  }

  @Test
  void testParse() {
    assertEquals(LocalDate.of(2024, 11, 3), compositeParser.parse("11/03/2024"),
        "Testando o parse de data no formato d/M/yyyy");
    assertEquals(true, compositeParser.parse("Sim"),
        "Testando o parse de boolean para 'Sim'.");
    assertEquals(10.0, (double) compositeParser.parse("10"), 1e-6,
        "Testando o parse de número inteiro");
  }
}
