import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Dataset;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatasetTest {
  private static Dataset dataset;
  private static Pessoa pessoa1;
  private static Pessoa pessoa2;
  private static Pessoa pessoa3;
  private static Pessoa pessoaIdenticaApessoa1;

  @BeforeEach
  public void setUp() {
    pessoa1 =
        new Pessoa(
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
        new Pessoa(
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
        new Pessoa(
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

    pessoaIdenticaApessoa1 =
        new Pessoa(
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

    dataset = new Dataset();
  }

  @Test
  @DisplayName("Testando de adição de pessoas")
  public void testAddPessoa() {
    dataset.addPessoa(null);
    assertEquals(0, dataset.size(), "Adição de pessoa nula não deve alterar o tamanho do dataset");
    dataset.addPessoa(pessoa1);
    assertEquals(1, dataset.size(), "Adição de pessoa válida não registrada corretamente");
    dataset.addPessoa(pessoa1);
    assertEquals(1, dataset.size(), "Pessoa duplicada não deve ser adicionada");
    dataset.addPessoa(pessoa2);
    assertEquals(2, dataset.size(), "Adição de segunda pessoa válida não registrada corretamente");
  }

  @Test
  @DisplayName("Testando de remoção de pessoas")
  public void testRemovePessoa() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.removePessoa(pessoa3);
    assertEquals(2, dataset.size(), "Remoção de pessoa inexistente não deve alterar o tamanho");
    dataset.removePessoa(pessoa1);
    assertEquals(1, dataset.size(), "Remoção de pessoa existente não registrada corretamente");
    dataset.removePessoa(pessoa2);
    assertEquals(0, dataset.size(), "Remoção de todas as pessoas não registrada corretamente");
  }

  @Test
  @DisplayName("Testando de remoção de pessoas por nome")
  public void testRemovePessoaByName() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.removePessoaByName("Bernardo");
    assertEquals(2, dataset.size(), "Remoção por nome inexistente não deve alterar o tamanho");
    dataset.removePessoaByName("Marcos");
    assertEquals(1, dataset.size(), "Remoção por nome existente não registrada corretamente");
    dataset.removePessoaByName("Jamilly");
    assertEquals(
        0, dataset.size(), "Remoção de todas as pessoas por nome não registrada corretamente");
  }

  @Test
  @DisplayName("Testando de substituição de pessoas")
  public void testReplacePessoa() {
    dataset.replacePessoa(pessoa1, pessoa2);
    assertEquals(0, dataset.size(), "Substituição em dataset vazio não deve alterar o tamanho");
    dataset.addPessoa(pessoa1);
    dataset.replacePessoa(pessoa1, pessoa2);
    assertEquals(
        pessoa2,
        dataset.getPessoaByName("Jamilly"),
        "Substituição de pessoa não registrada corretamente");
  }

  @Test
  @DisplayName("Testando de obtenção de pessoa por nome")
  public void testGetPessoaByName() {
    dataset.addPessoa(pessoa1);
    assertEquals(
        null, dataset.getPessoaByName("João"), "Busca por nome inexistente deve retornar null");
    dataset.addPessoa(pessoa2);
    assertEquals(
        pessoa1,
        dataset.getPessoaByName("Marcos"),
        "Busca por nome existente retornou a pessoa errada");
    dataset.addPessoa(pessoa3);
    assertEquals(
        pessoa3,
        dataset.getPessoaByName("Bernardo"),
        "Busca por nome existente não retornou a pessoa correta");
  }

  @Test
  @DisplayName("Testando de obtenção de todas as pessoas")
  public void testGetAll() {
    Pessoa[] pessoas = dataset.getAll();
    assertEquals(0, pessoas.length, "Vetor de pessoas deve estar vazio inicialmente");
    dataset.addPessoa(pessoa1);
    Pessoa[] pessoas2 = dataset.getAll();
    assertEquals(1, pessoas2.length, "Tamanho do vetor de pessoas está incorreto");
    assertEquals(pessoa1, pessoas2[0], "Pessoa não encontrada corretamente no vetor");
  }

  @Test
  @DisplayName("Testando de remoção de todas as pessoas")
  public void testRemoveAll() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    assertEquals(2, dataset.size(), "Tamanho do dataset antes da remoção está incorreto");
    dataset.removeAll();
    assertEquals(0, dataset.size(), "Dataset deve estar vazio após remoção de todas as pessoas");
  }

  @Test
  @DisplayName("Testando de tamanho do dataset")
  public void testSize() {
    dataset.addPessoa(pessoa1);
    assertEquals(1, dataset.size(), "Tamanho do dataset incorreto após adicionar uma pessoa");
    dataset.addPessoa(pessoa2);
    assertEquals(2, dataset.size(), "Tamanho do dataset incorreto após adicionar segunda pessoa");
    dataset.removePessoa(pessoa1);
    assertEquals(1, dataset.size(), "Tamanho do dataset incorreto após remover uma pessoa");
    dataset.removeAll();
    assertEquals(0, dataset.size(), "Tamanho do dataset incorreto após remover todas as pessoas");
  }

  @Test
  @DisplayName("Testando de altura máxima")
  public void testMaxAltura() {
    dataset.addPessoa(pessoa1);
    assertEquals(
        1.70f, dataset.maxAltura(), 0.01f, "Altura máxima incorreta após adicionar uma pessoa");
    dataset.addPessoa(pessoa2);
    assertEquals(1.70f, dataset.maxAltura(), 0.01f, "Altura máxima não deve mudar com nova adição");
    dataset.addPessoa(pessoa3);
    assertEquals(1.70f, dataset.maxAltura(), 0.01f, "Altura máxima deve permanecer a mesma");
  }

  @Test
  @DisplayName("Testando de altura mínima")
  public void testMinAltura() {
    dataset.addPessoa(pessoa1);
    assertEquals(
        1.70f, dataset.minAltura(), 0.01f, "Altura mínima incorreta após adicionar uma pessoa");
    dataset.addPessoa(pessoa2);
    assertEquals(
        1.40f, dataset.minAltura(), 0.01f, "Altura mínima incorreta após adicionar segunda pessoa");
    dataset.addPessoa(pessoa3);
    assertEquals(
        1.40f,
        dataset.minAltura(),
        0.01f,
        "Altura mínima deve permanecer a mesma após adicionar terceira pessoa");
  }

  @Test
  @DisplayName("Testando de altura média")
  public void testAvgAltura() {
    dataset.addPessoa(pessoa1);
    assertEquals(
        1.70f, dataset.avgAltura(), 0.01f, "Altura média incorreta após adicionar uma pessoa");
    dataset.removePessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    float valorEsperado = (1.60f + 1.40f) / 2;
    assertEquals(
        valorEsperado,
        dataset.avgAltura(),
        0.01f,
        "Altura média incorreta após remover uma pessoa e adicionar outras duas");
  }

  @Test
  @DisplayName("Testando cálculo de peso máximo")
  public void testMaxPeso() {
    dataset.addPessoa(pessoa1);
    assertEquals(70, dataset.maxPeso(), "O peso máximo deve ser 70");
    dataset.addPessoa(pessoa2);
    assertEquals(80, dataset.maxPeso(), "O peso máximo deve ser 80");
    dataset.addPessoa(pessoa3);
    assertEquals(80, dataset.maxPeso(), "O peso máximo deve permanecer 80");
    dataset.replacePessoa(pessoa2, pessoa3);
    assertEquals(70, dataset.maxPeso(), "O peso máximo deve ser 70 após substituição");
  }

  @Test
  @DisplayName("Testando cálculo de peso mínimo")
  public void testMinPeso() {
    dataset.addPessoa(pessoa1);
    assertEquals(70, dataset.minPeso(), "O peso mínimo deve ser 70");
    dataset.addPessoa(pessoa2);
    assertEquals(70, dataset.minPeso(), "O peso mínimo deve permanecer 70");
    dataset.addPessoa(pessoa3);
    assertEquals(60, dataset.minPeso(), "O peso mínimo deve ser 60");
    dataset.replacePessoa(pessoa2, pessoa3);
    assertEquals(60, dataset.minPeso(), "O peso mínimo deve permanecer 60 após substituição");
  }

  @Test
  @DisplayName("Testando cálculo de peso médio")
  public void testAvgPeso() {
    assertEquals(0, dataset.avgPeso(), "O peso médio deve ser 0 para dataset vazio");
    dataset.addPessoa(pessoa1);
    assertEquals(70, dataset.avgPeso(), 0.01f, "O peso médio deve ser 70");
    dataset.removePessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    float valorEsperado = (80 + 60) / 2.0f;
    assertEquals(valorEsperado, dataset.avgPeso(), 0.01f, "O peso médio calculado está incorreto");
  }

  @Test
  @DisplayName("Testando cálculo da porcentagem de adultos")
  public void testPercentAdult() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    float valorEsperado = (2.0f / 3.0f) * 100;
    assertEquals(
        valorEsperado, dataset.percentAdult(), 0.001f, "A porcentagem de adultos deve ser 66.67%");
  }

  @Test
  @DisplayName("Testando cálculo da porcentagem de estado civil")
  public void testPercentEstadoCivil() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentEstadoCivil(EstadoCivil.SOLTEIRO),
        0.01f,
        "A porcentagem de solteiros deve ser 66.67%");
  }

  @Test
  @DisplayName("Testando moda do estado civil")
  public void testModeEstadoCivil() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        EstadoCivil.SOLTEIRO,
        dataset.modeEstadoCivil(),
        "O estado civil mais frequente deve ser SOLTEIRO");
  }

  @Test
  @DisplayName("Testando cálculo da porcentagem de escolaridade")
  public void testPercentEscolaridade() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentEscolaridade(Escolaridade.MEDIO),
        0.01f,
        "A porcentagem de pessoas com ensino médio deve ser 66.67%");
  }

  @Test
  @DisplayName("Testando moda da escolaridade")
  public void testModeEscolaridade() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        Escolaridade.MEDIO,
        dataset.modeEscolaridade(),
        "A escolaridade mais frequente deve ser MEDIO");
  }

  @Test
  @DisplayName("Testando cálculo da porcentagem de moradia")
  public void testPercentMoradia() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentMoradia(Moradia.ALUGUEL),
        0.01f,
        "A porcentagem de pessoas que moram de aluguel deve ser 66.67%");
  }

  @Test
  @DisplayName("Testando moda da moradia")
  public void testModeMoradia() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        Moradia.ALUGUEL, dataset.modeMoradia(), "A moradia mais frequente deve ser ALUGUEL");
  }

  @Test
  @DisplayName("Testando cálculo da porcentagem de pessoas com hobbies")
  public void testPercentHobby() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentHobby(),
        0.01f,
        "A porcentagem de pessoas com hobbies deve ser 66.67%");
  }

  @Test
  @DisplayName("Testando cálculo da porcentagem de pessoas felizes")
  public void testPercentFeliz() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f, dataset.percentFeliz(), 0.01f, "A porcentagem de pessoas felizes deve ser 66.67%");
  }

  @Test
  @DisplayName("Testando vetor de distâncias calculadas")
  public void testCalcDistanceVector() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    dataset.addPessoa(pessoaIdenticaApessoa1);
    float[] distanceVector = dataset.calcDistanceVector(pessoa1);
    assertEquals(0, distanceVector[0], "A distância entre pessoa1 e ela mesma deve ser 0.");
    assertEquals(
        0.79f,
        distanceVector[1],
        0.1f,
        "A distância entre pessoa1 e pessoa2 deve ser aproximadamente 0.79.");
    assertEquals(
        0.0f,
        distanceVector[3],
        0.1f,
        "A distância entre pessoa1 e pessoaIdenticaApessoa1 deve ser 0.");
  }

  @Test
  @DisplayName("Testando matriz de distâncias calculadas")
  public void testCalcDistanceMatrix() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    dataset.addPessoa(pessoaIdenticaApessoa1);
    float[][] distanceMatrix = dataset.calcDistanceMatrix();
    assertEquals(0f, distanceMatrix[1][1], "A distância entre pessoa2 e ela mesma deve ser 0.");
    assertEquals(
        0.79f,
        distanceMatrix[1][3],
        0.1f,
        "A distância entre pessoa2 e pessoaIdenticaApessoa1 deve ser aproximadamente 0.79.");
    assertEquals(
        0.0f,
        distanceMatrix[0][3],
        "A distância entre pessoa1 e pessoaIdenticaApessoa1 deve ser 0.");
  }

  @Test
  @DisplayName("Testando obtenção das pessoas mais similares")
  public void testGetSimilar() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    Pessoa[] similares = dataset.getSimilar(pessoa1, 2);
    assertEquals(2, similares.length, "O array deve conter 2 pessoas mais similares");
    assertEquals(pessoa2, similares[0], "A pessoa mais similar a pessoa1 deve ser pessoa2");
    dataset.addPessoa(pessoaIdenticaApessoa1);
    Pessoa[] similares2 = dataset.getSimilar(pessoa1, 1);
    assertEquals(
        pessoaIdenticaApessoa1,
        similares2[0],
        "A pessoa mais similar a pessoa1 deve ser pessoaIdenticaApessoa1");
  }
}
