package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


import business.Pessoa;
import org.junit.Test;

public class PessoaTest {

  /*
   * Testa nome com símbolo.
   */
  @Test
  public void testSetNomeSimbolo() {
    Pessoa pessoa = new Pessoa();
    String nome = "M@rco$";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  /*
   * Testa nome com número.
   */
  @Test
  public void testSetNomeNumero() {
    Pessoa pessoa = new Pessoa();
    String nome = "M1rc05";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  /*
   * Testa nome null.
   */
  @Test
  public void testSetNomeNull() {
    Pessoa pessoa = new Pessoa();
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(null);
        });
  }

  /*
   * Testa nome vazio.
   */
  @Test
  public void testSetNomeVazio() {
    Pessoa pessoa = new Pessoa();
    String nome = "";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  /*
   * Testa nome válido.
   */
  @Test
  public void testSetNomeValido() {
    Pessoa pessoa = new Pessoa();
    String nome = "Marcos Alberto";
    pessoa.setNome(nome);
    assertEquals(nome, pessoa.getNome());
  }
}
