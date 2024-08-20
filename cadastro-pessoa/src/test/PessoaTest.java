package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import business.Pessoa;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PessoaTest {

  @Test
  @DisplayName("Testa nome com símbolo")
  public void testSetNomeSimbolo() {
    Pessoa pessoa = new Pessoa();
    String nome = "M@rco$";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  @Test
  @DisplayName("Testa nome com número")
  public void testSetNomeNumero() {
    Pessoa pessoa = new Pessoa();
    String nome = "M1rc05";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  @Test
  @DisplayName("Testa nome null")
  public void testSetNomeNull() {
    Pessoa pessoa = new Pessoa();
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(null);
        });
  }

  @Test
  @DisplayName("Testa nome vazio")
  public void testSetNomeVazio() {
    Pessoa pessoa = new Pessoa();
    String nome = "";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  @Test
  @DisplayName("Testa nome válido")
  public void testSetNomeValido() {
    Pessoa pessoa = new Pessoa();
    String nome = "Marcos Alberto";
    pessoa.setNome(nome);
    assertEquals(nome, pessoa.getNome());
  }
}
