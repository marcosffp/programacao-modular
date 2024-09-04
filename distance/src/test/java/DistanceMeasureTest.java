import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Dataset;
import br.lpm.business.DistanceMeasure;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistanceMeasureTest {

  private Dataset dataset;
  private Pessoa pessoa1;
  private Pessoa pessoa2;
  private Pessoa pessoa3;
  private DistanceMeasure distanceMeasure;

  @BeforeEach
  public void setUp() {
    pessoa1 =
        criarPessoa(
            "Marcos",
            LocalDate.of(2005, 10, 3),
            1.70f,
            70,
            EstadoCivil.CASADO,
            Escolaridade.SUPERIOR,
            Moradia.CASA_PROPRIA,
            Hobby.NENHUM,
            true);
    pessoa2 =
        criarPessoa(
            "Jamilly",
            LocalDate.of(2006, 2, 24),
            1.40f,
            80,
            EstadoCivil.SOLTEIRO,
            Escolaridade.MEDIO,
            Moradia.ALUGUEL,
            Hobby.ESPORTE,
            true);
    pessoa3 =
        criarPessoa(
            "Bernardo",
            LocalDate.of(2014, 2, 10),
            1.60f,
            60,
            EstadoCivil.SOLTEIRO,
            Escolaridade.MEDIO,
            Moradia.ALUGUEL,
            Hobby.ESPORTE,
            false);
    dataset = new Dataset();
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    distanceMeasure = new DistanceMeasure(dataset); // Inicialização do DistanceMeasure
  }

  private Pessoa criarPessoa(
      String nome,
      LocalDate dataNascimento,
      float altura,
      int peso,
      EstadoCivil estadoCivil,
      Escolaridade escolaridade,
      Moradia moradia,
      Hobby hobby,
      boolean feliz) {
    Pessoa pessoa = new Pessoa();
    pessoa.setNome(nome);
    pessoa.setDataNascimento(dataNascimento);
    pessoa.setAltura(altura);
    pessoa.setPeso(peso);
    pessoa.setEstadoCivil(estadoCivil);
    pessoa.setEscolaridade(escolaridade);
    pessoa.setMoradia(moradia);
    pessoa.setHobby(hobby);
    pessoa.setFeliz(feliz);
    return pessoa;
  }

  @Test
  public void testNormalizeField() {
    // Normaliza o campo "ALTURA"
    float[] valoresNormalizados = distanceMeasure.normalizeField("ALTURA");

    // Verifica se o array retornado tem o tamanho esperado
    assertEquals(3, valoresNormalizados.length, "O número de valores normalizados está incorreto.");
    assertEquals(
        1.0, valoresNormalizados[0], 0.01f, "Valor normalizado para pessoa1 está incorreto.");
    assertEquals(
        0.0, valoresNormalizados[1], 0.01f, "Valor normalizado para pessoa2 está incorreto.");
    assertEquals(
        0.66, valoresNormalizados[2], 0.01f, "Valor normalizado para pessoa3 está incorreto.");
  }

  @Test
  public void testCalcDistance() {
    // Teste de cálculo de distância
    float distancia12 = distanceMeasure.calcDistance(pessoa1, pessoa2);
    float distancia13 = distanceMeasure.calcDistance(pessoa1, pessoa3);
    float distancia23 = distanceMeasure.calcDistance(pessoa2, pessoa3);

    // Ajuste os valores esperados com base no cálculo real
    assertEquals(0.75f, distancia12, 0.01f, "Distância entre pessoa1 e pessoa2 está incorreta.");
    assertEquals(0.60f, distancia13, 0.01f, "Distância entre pessoa1 e pessoa3 está incorreta.");
    assertEquals(0.85f, distancia23, 0.01f, "Distância entre pessoa2 e pessoa3 está incorreta.");
  }

  @Test
  public void testCalcDistanceVector() {
    // Teste do vetor de distâncias
    float[] vetorDistancias = dataset.calcDistanceVector(pessoa1);

    // Ajuste os valores esperados com base nos cálculos reais
    assertEquals(0.75, vetorDistancias[0], 0.01f, "Distância com pessoa1 está incorreta.");
    assertEquals(0.60, vetorDistancias[1], 0.01f, "Distância com pessoa2 está incorreta.");
    assertEquals(0.85, vetorDistancias[2], 0.01f, "Distância com pessoa3 está incorreta.");
  }

  @Test
  public void testCalcDistanceMatrix() {
    // Teste da matriz de distâncias
    float[][] matrizDistancias = dataset.calcDistanceMatrix();

    // Ajuste os valores esperados com base nos cálculos reais
    assertEquals(0.0, matrizDistancias[0][0], 0.01f, "Distância com pessoa1 está incorreta.");
    assertEquals(0.75, matrizDistancias[0][1], 0.01f, "Distância com pessoa2 está incorreta.");
    assertEquals(0.60, matrizDistancias[0][2], 0.01f, "Distância com pessoa3 está incorreta.");
    assertEquals(0.75, matrizDistancias[1][0], 0.01f, "Distância com pessoa1 está incorreta.");
    assertEquals(0.0, matrizDistancias[1][1], 0.01f, "Distância com pessoa2 está incorreta.");
    assertEquals(0.85, matrizDistancias[1][2], 0.01f, "Distância com pessoa3 está incorreta.");
    assertEquals(0.60, matrizDistancias[2][0], 0.01f, "Distância com pessoa1 está incorreta.");
    assertEquals(0.85, matrizDistancias[2][1], 0.01f, "Distância com pessoa2 está incorreta.");
    assertEquals(0.0, matrizDistancias[2][2], 0.01f, "Distância com pessoa3 está incorreta.");
  }

  @Test
  public void testGetSimilar() {
    // Teste da obtenção de pessoas similares
    Pessoa[] similares = dataset.getSimilar(pessoa1, 2);

    // Ajuste os valores esperados com base na similaridade real
    assertEquals(pessoa2, similares[0], "Pessoa mais similar a pessoa1 está incorreta.");
    assertEquals(pessoa3, similares[1], "Segunda pessoa mais similar a pessoa1 está incorreta.");
  }
}
