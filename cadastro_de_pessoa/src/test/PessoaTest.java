package test;

import static org.junit.Assert.assertEquals;

import business.Pessoa;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PessoaTest {
  public static Pessoa pessoa;

  @BeforeEach
  public void setUp() throws Exception {
    pessoa = new Pessoa();
  }

  @Test
  @DisplayName("Testando o método setAltura")
  public void testSetAltura() {
    pessoa.setAltura(-1);
    assertEquals(0.0, pessoa.getAltura(), 0.1f);
    pessoa.setAltura(2.7f);
    assertEquals(0.0, pessoa.getAltura(), 0.1f);
    pessoa.setAltura(1.60f);
    assertEquals(1.60f, pessoa.getAltura(), 0.1f);
  }

  @Test
  @DisplayName("Testando o método setDataNascimento")
  public void testSetDataNascimento() {
    pessoa.setDataNascimento(LocalDate.now());
    assertEquals(null,pessoa.getDataNascimento());
    pessoa.setDataNascimento(LocalDate.now().plusYears(5));
    assertEquals(null, pessoa.getDataNascimento());
    pessoa.setDataNascimento(LocalDate.now().minusYears(5));
    assertEquals(LocalDate.now().minusYears(5), pessoa.getDataNascimento());
  }

  @Test
  @DisplayName("Testando o método setNome")
  public void testSetNome() {
    pessoa.setNome("Mar1!@");
    assertEquals(null,pessoa.getNome());
    pessoa.setNome("joão");
    assertEquals("joão", pessoa.getNome());
  }

  @Test
  @DisplayName("Testando o método setPeso")
  public void testSetPeso() {
    pessoa.setPeso(-1);

    assertEquals(0.0, pessoa.getPeso(), 0.01);

    pessoa.setPeso(605);
    assertEquals(0.0, pessoa.getPeso(), 0.01);

    pessoa.setPeso(60);
    assertEquals(60.0, pessoa.getPeso(), 0.01);
  }

  @Test
  @DisplayName("Testando o método setRenda")
  public void testSetRenda() {
    pessoa.setRenda(-1);
    assertEquals(0.0, pessoa.getRenda(), 0.1f);
    pessoa.setRenda(2652.42f);
    assertEquals(2652.42f, pessoa.getRenda(), 0.1f);
  }

  @Test
  @DisplayName("Testando o método setNaturalidade")
  public void testSetNaturalidade() {
    pessoa.setNaturalidade("Mineir1!@");
    assertEquals(null, pessoa.getNaturalidade());
    pessoa.setNaturalidade("Mineiro");
    assertEquals("Mineiro", pessoa.getNaturalidade());
  }
}
