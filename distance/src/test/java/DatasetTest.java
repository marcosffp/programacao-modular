

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Pessoa;
import br.lpm.business.Dataset;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatasetTest {

  private static Dataset dataset;
  private static Pessoa pessoa1;
  private static Pessoa pessoa2;
  private static Pessoa pessoa3;

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
  @DisplayName("Testando addPessoa")
  public void testAddPessoa() {
    dataset.addPessoa(null);
    assertEquals(0, dataset.size(), "Pessoa nula adicionada");
    dataset.addPessoa(pessoa1);
    assertEquals(1, dataset.size(), "Pessoa não adicionada");
    dataset.addPessoa(pessoa1);
    assertEquals(1, dataset.size(), "Pessoa duplicada adicionada");
    dataset.addPessoa(pessoa2);
    assertEquals(2, dataset.size(), "Pessoa não adicionada");
  }

  @Test
  @DisplayName("Testando removePessoa")
  public void testRemovePessoa() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.removePessoa(pessoa3);
    assertEquals(2, dataset.size(), "Tamanho não mudou com pessoa inexistente");
    dataset.removePessoa(pessoa1);
    assertEquals(1, dataset.size(), "Tamanho não mudou com pessoa já removida");
    dataset.removePessoa(pessoa2);
    assertEquals(0, dataset.size(), "Tamanho não é 0 após remover todas");
  }

  @Test
  @DisplayName("Testando removePessoaByName")
  public void testRemovePessoaByName() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.removePessoaByName("Bernardo");
    assertEquals(2, dataset.size(), "Tamanho não mudou com nome inexistente");
    dataset.removePessoaByName("Marcos");
    assertEquals(1, dataset.size(), "Tamanho não mudou com nome já removido");
    dataset.removePessoaByName("Jamilly");
    assertEquals(0, dataset.size(), "Tamanho não é 0 após remover todas");
  }

  @Test
  @DisplayName("Testando replacePessoa")
  public void testReplacePessoa() {
    dataset.replacePessoa(pessoa1, pessoa2);
    assertEquals(0, dataset.size(), "Tamanho alterado");
    dataset.addPessoa(pessoa1);
    dataset.replacePessoa(pessoa1, pessoa2);
    assertEquals(pessoa2, dataset.getPessoaByName("Jamilly"), "Substituição falhou");
  }

  @Test
  @DisplayName("Testando getPessoaByName")
  public void testGetPessoaByName() {
    dataset.addPessoa(pessoa1);
    assertEquals(null, dataset.getPessoaByName("João"), "Pessoa não encontrada");
    dataset.addPessoa(pessoa2);
    assertEquals(pessoa1, dataset.getPessoaByName("Marcos"), "Pessoa errada retornada");
    dataset.addPessoa(pessoa3);
    assertEquals(pessoa3, dataset.getPessoaByName("Bernardo"), "Pessoa não encontrada");
  }

  @Test
  @DisplayName("Testando getAll")
  public void testGetAll() {
    Pessoa[] pessoas = dataset.getAll();
    assertEquals(0, pessoas.length, "Vetor não vazio após remoção");
    dataset.addPessoa(pessoa1);
    Pessoa[] pessoas2 = dataset.getAll();
    assertEquals(1, pessoas2.length, "Tamanho do vetor errado");
    assertEquals(pessoa1, pessoas2[0], "Pessoa não encontrada no vetor");
  }

  @Test
  @DisplayName("Testando removeAll")
  public void testRemoveAll() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    assertEquals(2, dataset.size(), "Tamanho incorreto antes de remover");
    dataset.removeAll();
    assertEquals(0, dataset.size(), "Dataset não vazio após remoção");
  }

  @Test
  @DisplayName("Testando size")
  public void testSize() {
    dataset.addPessoa(pessoa1);
    assertEquals(1, dataset.size(), "Tamanho incorreto após adicionar uma pessoa");
    dataset.addPessoa(pessoa2);
    assertEquals(2, dataset.size(), "Tamanho incorreto após adicionar segunda pessoa");
    dataset.removePessoa(pessoa1);
    assertEquals(1, dataset.size(), "Tamanho incorreto após remover uma pessoa");
    dataset.removeAll();
    assertEquals(0, dataset.size(), "Tamanho incorreto após remover todas");
  }

  @Test
  @DisplayName("Testando maxAltura")
  public void testMaxAltura() {
    dataset.addPessoa(pessoa1);
    assertEquals(1.70f, dataset.maxAltura(), 0.01f, "Altura máxima deve ser 1.70");
    dataset.addPessoa(pessoa2);
    assertEquals(1.70f, dataset.maxAltura(), 0.01f, "Altura máxima não deve mudar");
    dataset.addPessoa(pessoa3);
    assertEquals(1.70f, dataset.maxAltura(), 0.01f, "Altura máxima deve permanecer 1.70");
  }

  @Test
  @DisplayName("Testando minAltura")
  public void testMinAltura() {
    dataset.addPessoa(pessoa1);
    assertEquals(1.70f, dataset.minAltura(), 0.01f, "Altura mínima deve ser 1.70");
    dataset.addPessoa(pessoa2);
    assertEquals(1.40f, dataset.minAltura(), 0.01f, "Altura mínima não deve mudar");
    dataset.addPessoa(pessoa3);
    assertEquals(1.40f, dataset.minAltura(), 0.01f, "Altura mínima deve permanecer 1.40");
  }

  @Test
  @DisplayName("Testando avgAltura")
  public void testAvgAltura() {
    dataset.addPessoa(pessoa1);
    assertEquals(1.70f, dataset.avgAltura(), 0.01f, "Altura média deve ser 1.70");
    dataset.removePessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    float valorEsperado = (1.60f + 1.40f) / 2.0f;
    assertEquals(valorEsperado, dataset.avgAltura(), 0.01f, "Altura média está incorreta");
  }

  @Test
  @DisplayName("Testando maxPeso")
  public void testMaxPeso() {
    dataset.addPessoa(pessoa1);
    assertEquals(70, dataset.maxPeso(), "Peso máximo deve ser 70");
    dataset.addPessoa(pessoa2);
    assertEquals(80, dataset.maxPeso(), "Peso máximo deve ser 80");
    dataset.addPessoa(pessoa3);
    assertEquals(80, dataset.maxPeso(), "Peso máximo não deve mudar");
    dataset.replacePessoa(pessoa2, pessoa3);
    assertEquals(70, dataset.maxPeso(), "Peso máximo deve ser 70");
  }

  @Test
  @DisplayName("Testando minPeso")
  public void testMinPeso() {
    dataset.addPessoa(pessoa1);
    assertEquals(70, dataset.minPeso(), "Peso mínimo deve ser 70");
    dataset.addPessoa(pessoa2);
    assertEquals(70, dataset.minPeso(), "Peso mínimo deve ser 70");
    dataset.addPessoa(pessoa3);
    assertEquals(60, dataset.minPeso(), "Peso mínimo deve ser 60");
    dataset.replacePessoa(pessoa2, pessoa3);
    assertEquals(60, dataset.minPeso(), "Peso mínimo deve ser 60");
  }

  @Test
  @DisplayName("Testando avgPeso")
  public void testAvgPeso() {
    assertEquals(0, dataset.avgPeso(), "Peso médio deve ser 0");
    dataset.addPessoa(pessoa1);
    assertEquals(70, dataset.avgPeso(), 0.01f, "Peso médio deve ser 70");
    dataset.removePessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    float valorEsperado = (80 + 60) / 2.0f;
    assertEquals(valorEsperado, dataset.avgPeso(), 0.01f, "Peso médio está incorreto");
  }

  @Test
  @DisplayName("Testando método percentAdult")
  public void testPercentAdult() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    float valorEsperado = (2.0f / 3.0f) * 100;
    assertEquals(
        valorEsperado,
        dataset.percentAdult(),
        0.001f,
        "A porcentagem de adultos deveria ser 66.67%");
  }

  @Test
  @DisplayName("Testando método percentEstadoCivil")
  public void testPercentEstadoCivil() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentEstadoCivil(EstadoCivil.SOLTEIRO),
        0.01f,
        "A porcentagem de solteiros deveria ser 66.67%");
  }

  @Test
  @DisplayName("Testando método modeEstadoCivil")
  public void testModeEstadoCivil() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        EstadoCivil.SOLTEIRO,
        dataset.modeEstadoCivil(),
        "O estado civil mais frequente deveria ser SOLTEIRO");
  }

  @Test
  @DisplayName("Testando método percentEscolaridade")
  public void testPercentEscolaridade() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentEscolaridade(Escolaridade.MEDIO),
        0.01f,
        "A porcentagem de pessoas com ensino médio deveria ser 66.67% ");
  }

  @Test
  @DisplayName("Testando método modeEscolaridade")
  public void testModeEscolaridade() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        Escolaridade.MEDIO,
        dataset.modeEscolaridade(),
        "A escolaridade mais frequente deveria ser MEDIO");
  }

  @Test
  @DisplayName("Testando método percentMoradia")
  public void testPercentMoradia() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentMoradia(Moradia.ALUGUEL),
        0.01f,
        "A porcentagem de pessoas que moram de aluguel deveria ser 66.67%");
  }

  @Test
  @DisplayName("Testando método modeMoradia")
  public void testModeMoradia() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        Moradia.ALUGUEL, dataset.modeMoradia(), "A moradia mais frequente deveria ser ALUGUEL");
  }

  @Test
  @DisplayName("Testando método percentHobby")
  public void testPercentHobby() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentHobby(),
        0.01f,
        "A porcentagem de pessoas com hobbies deveria ser 66.67%");
  }

  @Test
  @DisplayName("Testando método percentFeliz")
  public void testPercentFeliz() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        66.67f,
        dataset.percentFeliz(),
        0.01f,
        "A porcentagem de pessoas felizes deveria ser 66.67%");
  }
}
