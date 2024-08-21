package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import business.Pessoa;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PessoaTest {
  public static Pessoa pessoa;

  @BeforeEach
  public void setUp() throws Exception {
    pessoa = new Pessoa();
  }

  @Test
  public void testSetAltura() {
    pessoa.setAltura(-1);
    assertEquals(0.0, pessoa.getAltura(), 0.1f);
    pessoa.setAltura(2.7f);
    assertEquals(0.0, pessoa.getAltura(), 0.1f);
    pessoa.setAltura(1.60f);
    assertEquals(1.60f, pessoa.getAltura(), 0.1f);
  }

  @Test
  public void testSetDataNascimento() {
    pessoa.setDataNascimento(LocalDate.now());
    assertNull(pessoa.getDataNascimento());
    pessoa.setDataNascimento(LocalDate.now().plusYears(5));
    assertNull(pessoa.getDataNascimento());
    pessoa.setDataNascimento(LocalDate.now().minusYears(5));
    assertEquals(LocalDate.now().minusYears(5), pessoa.getDataNascimento());
  }

  @Test
  public void testSetNome() {
    pessoa.setNome("Mar1!@");
    assertNull(pessoa.getNome());
    pessoa.setNome("Marcos Alberto");
    assertEquals("Marcos Alberto", pessoa.getNome());
  }

  @Test
  public void testSetPeso() {
    pessoa.setPeso(-1);

    assertEquals(0.0, pessoa.getPeso(), 0.01);

    pessoa.setPeso(605);
    assertEquals(0.0, pessoa.getPeso(), 0.01);

    pessoa.setPeso(60);
    assertEquals(60.0, pessoa.getPeso(), 0.01);
  }

  @Test
  public void testSetRenda() {
    pessoa.setRenda(-1);
    assertEquals(0.0, pessoa.getRenda(), 0.1f);
    pessoa.setRenda(2652.42f);
    assertEquals(2652.42f, pessoa.getRenda(), 0.1f);
  }

  @Test
  public void testSetNaturalidade() {
    pessoa.setNaturalidade("Mineir1!@");
    assertNull(pessoa.getNaturalidade());
    pessoa.setNaturalidade("Mineiro");
    assertEquals("Mineiro", pessoa.getNaturalidade());
  }
}
