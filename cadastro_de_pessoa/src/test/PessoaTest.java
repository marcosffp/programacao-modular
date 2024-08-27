package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    assertEquals(0.0f, pessoa.getAltura(), 0.1f, "Teste altura com valor negativo");
    pessoa.setAltura(2.7f);
    assertEquals(0.0f, pessoa.getAltura(), 0.1f, "Teste altura com valor acima de 2.60");
    pessoa.setAltura(1.60f);
    assertEquals(1.60f, pessoa.getAltura(), 0.1f, "Teste altura com valor válido");
  }

  @Test
  @DisplayName("Testando o método setDataNascimento")
  public void testSetDataNascimento() {
    pessoa.setDataNascimento(LocalDate.now());
    assertEquals(null, pessoa.getDataNascimento(), "Teste data de nascimento com data presente");
    pessoa.setDataNascimento(LocalDate.now().plusYears(5));
    assertEquals(null, pessoa.getDataNascimento(), "Teste data de nascimento com data futuro");
    pessoa.setDataNascimento(LocalDate.now().minusYears(5));
    assertEquals(
        LocalDate.now().minusYears(5),
        pessoa.getDataNascimento(),
        "Teste data de nascimento com data válido");
  }

  @Test
  @DisplayName("Testando o método setNome")
  public void testSetNome() {
    pessoa.setNome("Mar1!@");
    assertEquals(null, pessoa.getNome(), "Teste nome com caracteres especiais e números");
    pessoa.setNome("joão");
    assertEquals("joão", pessoa.getNome(), "Teste nome com acentos");
    pessoa.setNome("Marcos Alberto");
    assertEquals("Marcos Alberto", pessoa.getNome(), "Teste nome com valor válido");
  }

  @Test
  @DisplayName("Testando o método setPeso")
  public void testSetPeso() {
    pessoa.setPeso(-1);

    assertEquals(0.0f, pessoa.getPeso(), "Teste peso com valor negativo");

    pessoa.setPeso(605);
    assertEquals(0.0f, pessoa.getPeso(), "Teste peso com valor acima de 600");

    pessoa.setPeso(60);
    assertEquals(60.0f, pessoa.getPeso(), "Teste peso com valor válido");
  }

  @Test
  @DisplayName("Testando o método setRenda")
  public void testSetRenda() {
    pessoa.setRenda(-1);
    assertEquals(0.0, pessoa.getRenda(), "Teste renda com valor negativo");
    pessoa.setRenda(2652.42f);
    assertEquals(2652.42f, pessoa.getRenda(), "Teste renda com valor válido");
  }

  @Test
  @DisplayName("Testando o método setNaturalidade")
  public void testSetNaturalidade() {
    pessoa.setNaturalidade("Mineir1!@");
    assertEquals(
        null, pessoa.getNaturalidade(), "Teste naturalidade com caracteres especiais e números");
    pessoa.setNaturalidade("Mineiro");
    assertEquals("Mineiro", pessoa.getNaturalidade(), "Teste naturalidade com valor válido");
  }
}
