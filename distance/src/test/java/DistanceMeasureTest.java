import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Dataset;
import br.lpm.business.DistanceMeasure;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
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
            Genero.MASCULINO,
            1.70f,
            70,
            600.54f,
            "Mineira",
            Hobby.NENHUM,
            EstadoCivil.CASADO,
            Escolaridade.SUPERIOR,
            true,
            Moradia.CASA_PROPRIA);

    pessoa2 =
        criarPessoa(
            "Jamilly",
            LocalDate.of(2006, 2, 24),
            Genero.FEMININO,
            1.40f,
            80,
            100.12f, 
            "Mineira",
            Hobby.ESPORTE,
            EstadoCivil.SOLTEIRO,
            Escolaridade.MEDIO,
            true,
            Moradia.ALUGUEL);

    pessoa3 =
        criarPessoa(
            "Bernardo",
            LocalDate.of(2014, 2, 10),
            Genero.MASCULINO,
            1.60f,
            60,
            2000.12f,
            "Salvador",
            Hobby.ESPORTE,
            EstadoCivil.SOLTEIRO,
            Escolaridade.MEDIO,
            false,
            Moradia.ALUGUEL);

    dataset = new Dataset();
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);

    distanceMeasure = new DistanceMeasure(dataset);
  }

  private Pessoa criarPessoa(
      String nome,
      LocalDate dataNascimento,
      Genero genero,
      float altura,
      int peso,
      float renda,
      String naturalidade,
      Hobby hobby,
      EstadoCivil estadoCivil,
      Escolaridade escolaridade,
      boolean feliz,
      Moradia moradia) {
    Pessoa pessoa = new Pessoa();
    pessoa.setNome(nome);
    pessoa.setDataNascimento(dataNascimento);
    pessoa.setGenero(genero);
    pessoa.setAltura(altura);
    pessoa.setPeso(peso);
    pessoa.setRenda(renda);
    pessoa.setNaturalidade(naturalidade);
    pessoa.setHobby(hobby);
    pessoa.setEstadoCivil(estadoCivil);
    pessoa.setEscolaridade(escolaridade);
    pessoa.setFeliz(feliz);
    pessoa.setMoradia(moradia);
    return pessoa;
  }
  
  @Test
  public void testNormalizeFields() {
    float[] normalizacaoPesos = distanceMeasure.normalizeField("Peso");
    assertEquals(
        0.5f, normalizacaoPesos[0], "Valor normalizado para o primeiro objeto está incorreto");

    float[] normalizacaoAlturas = distanceMeasure.normalizeField("Altura");
    assertEquals(
        0.0f, normalizacaoAlturas[1], "Valor normalizado para o primeiro objeto está incorreto");
  }

  @Test
  public void testCalcDistance() {


   /*  for (int i = 0; i < campos.length; i++) {
      float[] valoresNormalizados = distanceMeasure.normalizeField(campos[i]);
      assertEquals(
          3,
          valoresNormalizados.length,
          "O número de valores normalizados está incorreto para o campo: " + campos[i]);
      float valorEsperado1 = (valores1[i] - minValores[i]) / (maxValores[i] - minValores[i]);
      float valorEsperado2 = (valores2[i] - minValores[i]) / (maxValores[i] - minValores[i]);
      float valorEsperado3 = (valores3[i] - minValores[i]) / (maxValores[i] - minValores[i]);

      assertEquals(
          valorEsperado1,
          valoresNormalizados[0],
          0.01f,
          "Valor normalizado para o primeiro objeto está incorreto para o campo: " + campos[i]);
      assertEquals(
          valorEsperado2,
          valoresNormalizados[1],
          0.01f,
          "Valor normalizado para o segundo objeto está incorreto para o campo: " + campos[i]);
      assertEquals(
          valorEsperado3,
          valoresNormalizados[2],
          0.01f,
          "Valor normalizado para o terceiro objeto está incorreto para o campo: " + campos[i]);
    }*/
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
