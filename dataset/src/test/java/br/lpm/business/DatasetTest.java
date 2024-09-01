package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  public void setUp() throws Exception {
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
  @DisplayName("Testando addPessoa")
  public void testAddPessoa() {
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
    dataset.replacePessoa(pessoa1, pessoa2);
    assertEquals(0, dataset.size(), "Tamanho alterado");
    dataset.addPessoa(pessoa1);
    dataset.replacePessoa(pessoa1, pessoa2);
    assertEquals(pessoa2, dataset.getPessoaByName("Jamilly"), "Substituição falhou");
  }

  @Test
  @DisplayName("Testando getPessoaByName")
  public void testGetPessoaByName() {
    dataset.removeAll();
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
    dataset.removeAll();
    Pessoa[] pessoas = dataset.getAll();
    assertEquals(0, pessoas.length, "Vetor não vazio após remoção");
    dataset.addPessoa(pessoa1);
    Pessoa[] pessoas2 = dataset.getAll();
    assertEquals(1, pessoas2.length, "Tamanho do vetor errado");
    assertEquals(pessoa1, pessoas2[0], "Pessoa não encontrada no vetor");
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
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
    dataset.removeAll();
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        Moradia.ALUGUEL, dataset.modeMoradia(), "A moradia mais frequente deveria ser ALUGUEL");
  }

  @Test
  @DisplayName("Testando método percentHobby")
  public void testPercentHobby() {
    dataset.removeAll();
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
    dataset.removeAll();
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
