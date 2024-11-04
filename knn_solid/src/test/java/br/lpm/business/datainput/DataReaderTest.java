package br.lpm.business.datainput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.datamodel.DataSet;
import br.lpm.business.dataparser.CompositeDataParser;
import br.lpm.business.dataparser.NumericParser;
import java.io.FileWriter;
import java.io.IOException;

public class DataReaderTest {
  private CsvReader dataReader;
  private DataSet dataSet;
  private CompositeDataParser compositeDataParser;
  private NumericParser numericParser;
  private static final String TEST_CSV_FILE = "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular\\knn_solid\\LPM - Turma 1 - Cadastro de Pessoas.csv";

  @BeforeEach
  void setUp() {
    numericParser = new NumericParser();
    compositeDataParser = new CompositeDataParser();
    compositeDataParser.addParser(numericParser);
    dataReader = new CsvReader(compositeDataParser);
    dataSet = new DataSet();
  }

  @Test
  void testLoadDataFrom() throws IOException {
    String csvContent = """
        Nome da Pessoa:;Data de Nascimento:;Gênero:;Altura (em metros):;Peso aproximado (em quilos inteiros):;Renda aproximada (em reais):;Naturalidade (cidade de nascimento):;Moradia;Estado Civil:;Escolaridade;Possui Hobby:;Você é feliz:
        Bruna;6/23/1999;Feminino;1,6;62;2000;Belo Horizonte;Com a família;Solteiro;Ensino Médio;Música;Sim
        """;
    try (FileWriter writer = new FileWriter(TEST_CSV_FILE)) {
      writer.write(csvContent);
    }

    dataReader.loadDataFrom(TEST_CSV_FILE, dataSet);

    assertEquals(1, dataSet.sizeDataPoints(), "Testando o número de pontos de dados deve ser 1.");
    assertEquals(12, dataSet.getAttributeNames().size(), "Testando o número de atributos deve ser 12.");
    assertTrue(dataSet.getAttributeNames().contains("Nome da Pessoa:"), "Testando o atributo 'Nome da Pessoa:'");
  }

  @Test
  void testSetCompositeDataParser() {
    CompositeDataParser parser = new CompositeDataParser();
    parser.addParser(new NumericParser());

    dataReader.setCompositeDataParser(parser);
    assertNotNull(dataReader.getCompositeDataParser(), "Testando o CompositeDataParser não deve ser nulo.");
    assertEquals(1, dataReader.getCompositeDataParser().sizeParsers(), "Testando o número de parsers deve ser 1.");
  }

  @Test
  void testGetCompositeDataParser() {
    assertNotNull(dataReader.getCompositeDataParser(), "Testando o CompositeDataParser não deve ser nulo.");
    assertEquals(1, dataReader.getCompositeDataParser().sizeParsers(), "Testando o número de parsers deve ser 1.");
  }
}
