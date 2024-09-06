import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Pessoa;
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
    assertEquals(0.0f, pessoa.getAltura(), 0.1f, "Altura negativa deve ser ajustada para 0.0");

    pessoa.setAltura(2.7f);
    assertEquals(0.0f, pessoa.getAltura(), 0.1f, "Altura acima de 2.60 deve ser ajustada para 0.0");

    pessoa.setAltura(1.60f);
    assertEquals(1.60f, pessoa.getAltura(), 0.1f, "Altura válida deve ser aceita corretamente");
  }

  @Test
  @DisplayName("Testando o método setDataNascimento")
  public void testSetDataNascimento() {
    pessoa.setDataNascimento(LocalDate.now());
    assertEquals(null, pessoa.getDataNascimento(), "Data de nascimento no presente deve ser nula");

    pessoa.setDataNascimento(LocalDate.now().plusYears(5));
    assertEquals(null, pessoa.getDataNascimento(), "Data de nascimento no futuro deve ser nula");

    pessoa.setDataNascimento(LocalDate.now().minusYears(5));
    assertEquals(
        LocalDate.now().minusYears(5),
        pessoa.getDataNascimento(),
        "Data de nascimento válida deve ser aceita corretamente");
  }

  @Test
  @DisplayName("Testando o método setNome")
  public void testSetNome() {
    pessoa.setNome("Mar1!@");
    assertEquals(null, pessoa.getNome(), "Nome com caracteres especiais e números deve ser nulo");

    pessoa.setNome("joão");
    assertEquals("joão", pessoa.getNome(), "Nome com acento deve ser aceito corretamente");

    pessoa.setNome("Marcos Alberto");
    assertEquals("Marcos Alberto", pessoa.getNome(), "Nome válido deve ser aceito corretamente");
  }

  @Test
  @DisplayName("Testando o método setPeso")
  public void testSetPeso() {
    pessoa.setPeso(-1);
    assertEquals(0.0f, pessoa.getPeso(), "Peso negativo deve ser ajustado para 0.0");

    pessoa.setPeso(605);
    assertEquals(0.0f, pessoa.getPeso(), "Peso acima de 600 deve ser ajustado para 0.0");

    pessoa.setPeso(60);
    assertEquals(60.0f, pessoa.getPeso(), "Peso válido deve ser aceito corretamente");
  }

  @Test
  @DisplayName("Testando o método setRenda")
  public void testSetRenda() {
    pessoa.setRenda(-1);
    assertEquals(0.0, pessoa.getRenda(), "Renda negativa deve ser ajustada para 0.0");

    pessoa.setRenda(2652.42f);
    assertEquals(2652.42f, pessoa.getRenda(), "Renda válida deve ser aceita corretamente");
  }

  @Test
  @DisplayName("Testando o método setNaturalidade")
  public void testSetNaturalidade() {
    pessoa.setNaturalidade("Mineir1!@");
    assertEquals(
        null,
        pessoa.getNaturalidade(),
        "Naturalidade com caracteres especiais e números deve ser nula");

    pessoa.setNaturalidade("Mineiro");
    assertEquals(
        "Mineiro", pessoa.getNaturalidade(), "Naturalidade válida deve ser aceita corretamente");
  }
}
