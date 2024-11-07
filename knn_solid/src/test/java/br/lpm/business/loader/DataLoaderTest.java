package br.lpm.business.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import br.lpm.business.dataset.DataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataLoaderTest {
  private CsvLoader dataLoader;
  private DataSet dataSet;
  private static final String BASE_DIRECTORY = "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular";
  private static final String TEST_CSV_FILE = BASE_DIRECTORY + "\\knn_solid\\LPM - Turma 1 - Cadastro de Pessoas.csv";

  @BeforeEach
  void setUp() {
    dataLoader = new CsvLoader(';');
    dataSet = new DataSet();
  }

  @Test
  void testLoad() throws IOException {
    String csvContent = """
        Nome da Pessoa:;Data de Nascimento:;Gênero:;Altura (em metros):;Peso aproximado (em quilos inteiros):;Renda aproximada (em reais):;Naturalidade (cidade de nascimento):;Moradia;Estado Civil:;Escolaridade;Possui Hobby:;Você é feliz:
        Bruna;6/23/1999;Feminino;1,6;62;2000;Belo Horizonte;Com a família;Solteiro;Ensino Médio;Música;Sim
        """;

    File file = new File(TEST_CSV_FILE);
    file.getParentFile().mkdirs(); 
    try (FileWriter writer = new FileWriter(TEST_CSV_FILE)) {
      writer.write(csvContent);
    }

    dataLoader.load(TEST_CSV_FILE, dataSet);

    assertEquals(1, dataSet.size(), "Testando o número de pontos de dados deve ser 1.");
    assertEquals(12, dataSet.getAttributeNames().size(), "Testando o número de atributos deve ser 12.");
    assertTrue(dataSet.getAttributeNames().contains("Nome da Pessoa:"), "Deve conter o atributo 'Nome da Pessoa:'.");
    assertTrue(dataSet.getAttributeNames().contains("Você é feliz:"), "Deve conter o atributo 'Você é feliz:'.");
  }

}
