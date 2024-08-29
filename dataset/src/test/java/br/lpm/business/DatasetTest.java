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
    pessoa1 = new Pessoa();
    pessoa2 = new Pessoa();
    pessoa3 = new Pessoa();
    dataset = new Dataset();
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
    pessoa1.setNome("Marcos");
    dataset.addPessoa(pessoa1);
    pessoa2.setNome("Jamilly");
    dataset.addPessoa(pessoa2);
    pessoa3.setNome("Bernardo");
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
    pessoa1.setNome("Marcos");
    pessoa2.setNome("João");
    dataset.addPessoa(pessoa1);
    dataset.replacePessoa(pessoa1, pessoa2);
    assertEquals(pessoa2, dataset.getPessoaByName("João"), "Substituição falhou");
  }

  @Test
  @DisplayName("Testando getPessoaByName")
  public void testGetPessoaByName() {
    dataset.removeAll();
    pessoa1.setNome("Marcos");
    dataset.addPessoa(pessoa1);
    assertEquals(null, dataset.getPessoaByName("João"), "Pessoa não encontrada");
    pessoa2.setNome("Marcos");
    dataset.addPessoa(pessoa2);
    assertEquals(pessoa1, dataset.getPessoaByName("Marcos"), "Pessoa errada retornada");
    pessoa3.setNome("Pedro");
    dataset.addPessoa(pessoa3);
    assertEquals(pessoa3, dataset.getPessoaByName("Pedro"), "Pessoa não encontrada");
  }

  @Test
  @DisplayName("Testando getAll")
  public void testGetAll() {
    dataset.removeAll();
    Pessoa[] pessoas = dataset.getAll();
    assertEquals(0, pessoas.length, "Vetor não vazio após remoção");
    pessoa1.setNome("Marcos");
    dataset.addPessoa(pessoa1);
    Pessoa[] pessoas2 = dataset.getAll();
    assertEquals(1, pessoas2.length, "Tamanho do vetor errado");
    assertEquals(pessoa1, pessoas2[0], "Pessoa não encontrada no vetor");
    dataset.removeAll();
  }

  @Test
  @DisplayName("Testando removeAll")
  public void testRemoveAll() {
    pessoa1.setNome("Marcos");
    pessoa2.setNome("João");
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
    pessoa1.setAltura(1.70f);
    dataset.addPessoa(pessoa1);
    assertEquals(1.70f, dataset.maxAltura(), 0.01f, "Altura máxima deve ser 1.70");
    pessoa2.setAltura(1.40f);
    dataset.addPessoa(pessoa2);
    assertEquals(1.70f, dataset.maxAltura(), 0.01f, "Altura máxima não deve mudar");
    pessoa3.setAltura(1.70f);
    dataset.addPessoa(pessoa3);
    assertEquals(1.70f, dataset.maxAltura(), 0.01f, "Altura máxima deve permanecer 1.70");
  }

  @Test
  @DisplayName("Testando minAltura")
  public void testMinAltura() {
    dataset.removeAll();
    pessoa1.setAltura(1.70f);
    dataset.addPessoa(pessoa1);
    assertEquals(1.70f, dataset.minAltura(), 0.01f, "Altura mínima deve ser 1.70");
    pessoa2.setAltura(1.40f);
    dataset.addPessoa(pessoa2);
    assertEquals(1.40f, dataset.minAltura(), 0.01f, "Altura mínima não deve mudar");
    pessoa3.setAltura(1.40f);
    dataset.addPessoa(pessoa3);
    assertEquals(1.40f, dataset.minAltura(), 0.01f, "Altura mínima deve permanecer 1.40");
  }

  @Test
  @DisplayName("Testando avgAltura")
  public void testAvgAltura() {
    dataset.removeAll();
    pessoa1.setAltura(1.70f);
    dataset.addPessoa(pessoa1);
    assertEquals(1.70f, dataset.avgAltura(), 0.01f, "Altura média deve ser 1.70");
    dataset.removePessoa(pessoa1);
    pessoa2.setAltura(1.70f);
    dataset.addPessoa(pessoa2);
    pessoa3.setAltura(1.40f);
    dataset.addPessoa(pessoa3);
    float valorEsperado = (1.70f + 1.40f) / 2.0f;
    assertEquals(valorEsperado, dataset.avgAltura(), 0.01f, "Altura média está incorreta");
  }

  @Test
  @DisplayName("Testando maxPeso")
  public void testMaxPeso() {
    dataset.removeAll();
    pessoa1.setPeso(70);
    dataset.addPessoa(pessoa1);
    assertEquals(70, dataset.maxPeso(), "Peso máximo deve ser 70");
    pessoa2.setPeso(80);
    dataset.addPessoa(pessoa2);
    assertEquals(80, dataset.maxPeso(), "Peso máximo deve ser 80");
    pessoa3.setPeso(60);
    dataset.addPessoa(pessoa3);
    assertEquals(80, dataset.maxPeso(), "Peso máximo não deve mudar");
    pessoa3.setPeso(80);
    dataset.replacePessoa(pessoa2, pessoa3);
    assertEquals(80, dataset.maxPeso(), "Peso máximo deve ser 80");
  }

  @Test
  @DisplayName("Testando minPeso")
  public void testMinPeso() {
    dataset.removeAll();

    pessoa1.setPeso(70);
    pessoa2.setPeso(60);
    pessoa3.setPeso(80);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(60.0, dataset.minPeso(), "Peso mínimo deve ser 60");
    dataset.removePessoa(pessoa2);
    pessoa2.setPeso(80);
    dataset.addPessoa(pessoa2); 
    assertEquals(70.0, dataset.minPeso(), "Peso mínimo deve ser 70");

  }

  @Test
  @DisplayName("Testando avgPeso")
  public void testAvgPeso() {
    dataset.removeAll();
    assertEquals(0, dataset.avgPeso(), "Peso médio deve ser 0");
    pessoa1.setPeso(70);
    dataset.addPessoa(pessoa1);
    assertEquals(70, dataset.avgPeso(), 0.01f, "Peso médio deve ser 70");
    dataset.removePessoa(pessoa1);
    pessoa2.setPeso(70);
    dataset.addPessoa(pessoa2);
    pessoa3.setPeso(60);
    dataset.addPessoa(pessoa3);
    float valorEsperado = (70 + 60) / 2.0f;
    assertEquals(valorEsperado, dataset.avgPeso(), 0.01f, "Peso médio está incorreto");
  }

  @Test
  @DisplayName("Testando método percentAdult")
  public void testPercentAdult() {
    pessoa1.setDataNascimento(LocalDate.of(2005, 10, 3));
    pessoa2.setDataNascimento(LocalDate.of(2006, 2, 24));
    pessoa3.setDataNascimento(LocalDate.of(2014, 2, 10));
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
    pessoa1.setEstadoCivil(EstadoCivil.CASADO);
    pessoa2.setEstadoCivil(EstadoCivil.SOLTEIRO);
    pessoa3.setEstadoCivil(EstadoCivil.SOLTEIRO);
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
    pessoa1.setEstadoCivil(EstadoCivil.CASADO);
    pessoa2.setEstadoCivil(EstadoCivil.SOLTEIRO);
    pessoa3.setEstadoCivil(EstadoCivil.SOLTEIRO);
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
    pessoa1.setEscolaridade(Escolaridade.SUPERIOR);
    pessoa2.setEscolaridade(Escolaridade.MEDIO);
    pessoa3.setEscolaridade(Escolaridade.MEDIO);
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
    pessoa1.setEscolaridade(Escolaridade.SUPERIOR);
    pessoa2.setEscolaridade(Escolaridade.MEDIO);
    pessoa3.setEscolaridade(Escolaridade.MEDIO);
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
    pessoa1.setMoradia(Moradia.CASA_PROPRIA);
    pessoa2.setMoradia(Moradia.ALUGUEL);
    pessoa3.setMoradia(Moradia.ALUGUEL);
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
    pessoa1.setMoradia(Moradia.CASA_PROPRIA);
    pessoa2.setMoradia(Moradia.ALUGUEL);
    pessoa3.setMoradia(Moradia.ALUGUEL);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        Moradia.ALUGUEL,
        dataset.modeMoradia(),
        "A moradia mais frequente deveria ser ALUGUEL");
  }

  @Test
  @DisplayName("Testando método percentHobby")
  public void testPercentHobby() {
    pessoa1.setHobby(Hobby.NENHUM);
    pessoa2.setHobby(Hobby.ESPORTE);
    pessoa3.setHobby(Hobby.ESPORTE);
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
    pessoa1.setFeliz(true);
    pessoa2.setFeliz(true);
    pessoa3.setFeliz(false);
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