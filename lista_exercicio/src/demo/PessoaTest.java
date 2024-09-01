package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PessoaTest {
  private Pessoa pessoa;

  @BeforeEach
  void setUp() {
    pessoa = new Pessoa();
  }

  @Test
  void testGetCpf() {
    pessoa.setCpf("123456789");
    assertEquals("123456789", pessoa.getCpf());
  }

  @Test
  void testGetIdade() {
    pessoa.setIdade(17);
    assertEquals(17, pessoa.getIdade());
  }

  @Test
  void testGetNome() {
    pessoa.setNome("Marcos");
    assertEquals("Marcos", pessoa.getNome());
  }

  @Test
  void testGetSexo() {
    pessoa.setSexo("Masculino");
    assertEquals("Masculino", pessoa.getSexo());
  }

  @Test
  void testIsMaiorDeIdade() {
    assertEquals(false, pessoa.isMaiorIdade());
    pessoa.setIdade(18);
    assertEquals(true, pessoa.isMaiorIdade());
  }

  @Test
  void testSetCpf() {
    pessoa.setCpf("987654321");
    assertEquals("987654321", pessoa.getCpf());
  }

  @Test
  void testSetIdade() {
    pessoa.setIdade(25);
    assertEquals(25, pessoa.getIdade());
  }

  @Test
  void testSetNome() {
    pessoa.setNome("Ana");
    assertEquals("Ana", pessoa.getNome());
  }

  @Test
  void testSetSexo() {
    pessoa.setSexo("Feminino");
    assertEquals("Feminino", pessoa.getSexo());
  }
}
