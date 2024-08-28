package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatasetTest {

  public static Dataset dataset;
  public static Pessoa pessoa1;
  public static Pessoa pessoa2;
  public static Pessoa pessoa3;

  @BeforeEach
  public void setUp() throws Exception {
    pessoa1 = new Pessoa();
    pessoa2 = new Pessoa();
    pessoa3 = new Pessoa();
    dataset = new Dataset();
  }

  @Test
  @DisplayName("Testando método addPessoa")
  public void testAddPessoa() {
    dataset.addPessoa(pessoa1);
    assertEquals(1, dataset.size(), "O tamanho da coleção deveria ser 1");
    dataset.removerPessoa(pessoa1);
    assertEquals(0, dataset.size(), "O tamanho da coleção deveria ser 0");

    dataset.addPessoa(null);
    assertEquals(0, dataset.size(), "O tamanho da coleção deveria ser 0");
  }

  @Test
  @DisplayName("Testando método avgAltura")
  void testAvgAltura() {
    pessoa1.setAltura(1.70f);
    pessoa2.setAltura(1.80f);
    pessoa3.setAltura(1.60f);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(1.7f, dataset.avgAltura(), 0.01f, "A média das alturas deveria ser 1.7");
  }

  @Test
  @DisplayName("Testando método maxAltura")
  void testMaxAltura() {
    pessoa1.setAltura(1.70f);
    pessoa2.setAltura(1.80f);
    pessoa3.setAltura(1.60f);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(1.8f, dataset.maxAltura(), "A maior altura deveria ser 1.8");
  }

  @Test
  @DisplayName("Testando método minAltura")
  void testMinAltura() {
    pessoa1.setAltura(1.70f);
    pessoa2.setAltura(1.80f);
    pessoa3.setAltura(1.60f);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(1.6f, dataset.minAltura(), "A menor altura deveria ser 1.6");
  }

  @Test
  @DisplayName("Testando método avgPeso")
  void testAvgPeso() {
    pessoa1.setPeso(70);
    pessoa2.setPeso(80);
    pessoa3.setPeso(60);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(70, dataset.avgPeso(), 0.01f, "A média dos pesos deveria ser 70.0");
  }

  @Test
  @DisplayName("Testando método maxPeso")
  void testMaxPeso() {
    pessoa1.setPeso(70);
    pessoa2.setPeso(80);
    pessoa3.setPeso(60);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(80, dataset.maxPeso(), "O maior peso deveria ser 80.0");
  }

  @Test
  @DisplayName("Testando método minPeso")
  void testMinPeso() {
    pessoa1.setPeso(70);
    pessoa2.setPeso(80);
    pessoa3.setPeso(60);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(60, dataset.minPeso(), "O menor peso deveria ser 60.0");
  }

  @Test
  @DisplayName("Testando método percentAdult")
  void testPercentAdult() {
    pessoa1.setDataNascimento(LocalDate.of(2005, 10, 3));
    pessoa2.setDataNascimento(LocalDate.of(2006, 2, 24));
    pessoa3.setDataNascimento(LocalDate.of(2014, 2, 10));
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    float valorExpecatativo = (2 / 3) * 100;
    assertEquals(
        valorExpecatativo,
        dataset.percentAdult(),
        0.001f,
        "A porcentagem de adultos deveria ser 66.67% com dois adultos em um total de três"
            + " pessoas.");
  }

  @Test
  @DisplayName("Testando método percentEstadoCivil")
  void testPercentEstadoCivil() {
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
        "A porcentagem de solteiros deveria ser 66.67");
  }

  @Test
  @DisplayName("Testando método modeEstadoCivil")
  void testModeEstadoCivil() {
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
  void testPercentEscolaridade() {
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
        "A porcentagem de pessoas com ensino médio deveria ser 66.67");
  }

  @Test
  @DisplayName("Testando método modeEscolaridade")
  void testModeEscolaridade() {
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
  void testPercentMoradia() {
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
        "A porcentagem de pessoas que moram de aluguel deveria ser 66.67");
  }

  @Test
  @DisplayName("Testando método modeMoradia")
  void testModeMoradia() {
    pessoa1.setMoradia(Moradia.CASA_PROPRIA);
    pessoa2.setMoradia(Moradia.ALUGUEL);
    pessoa3.setMoradia(Moradia.ALUGUEL);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    assertEquals(
        Moradia.ALUGUEL, dataset.modeMoradia(), "A moradia mais frequente deveria ser ALUGUEL");
  }

  @Test
  @DisplayName("Testando método percentHobby")
  void testPercentHobby() {
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
        "A porcentagem de pessoas com hobbies deveria ser 66.67");
  }

  @Test
  @DisplayName("Testando método percentFeliz")
  void testPercentFeliz() {
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
        "A porcentagem de pessoas felizes deveria ser 66.67");
  }

  @Test
  @DisplayName("Testando método replacePessoa")
  void testReplacePessoa() {
    pessoa1.setNome("João");
    pessoa2.setNome("Maria");
    pessoa3.setNome("Carlos");

    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);

    dataset.replacePessoa(pessoa1, pessoa3);
    assertEquals(
        "Carlos",
        dataset.getPessoaByName("Carlos").getNome(),
        "A pessoa deveria ter sido substituída");
    assertEquals(null, dataset.getPessoaByName("João"), "João não deveria mais existir no dataset");
  }

  @Test
  @DisplayName("Testando método size")
  void testSize() {
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    assertEquals(
        2, dataset.size(), "O tamanho do dataset deveria ser 2 após adicionar duas pessoas.");
  }
}
