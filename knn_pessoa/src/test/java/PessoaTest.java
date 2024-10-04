import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PessoaTest {
  public static Pessoa pessoa;

  @BeforeEach
  public void setUp() throws Exception {
    pessoa =
        new Pessoa(
            "Pessoa Um",
            LocalDate.of(1990, 1, 1),
            Genero.MASCULINO,
            2f,
            100,
            5000f,
            "Cidade Um",
            Hobby.NENHUM,
            EstadoCivil.CASADO,
            Escolaridade.SUPERIOR,
            false,
            Moradia.CASA_PROPRIA);
  }

  @Test
  @DisplayName("Testando o método setAltura")
  public void testSetAltura() {
    pessoa.setAltura(-1);
    assertEquals(2.0f, pessoa.getAltura(), 0.1f, "Altura negativa deve ser ajustada para 0.0");

    pessoa.setAltura(2.7f);
    assertEquals(2.0f, pessoa.getAltura(), 0.1f, "Altura acima de 2.60 deve ser ajustada para 0.0");

    pessoa.setAltura(1.60f);
    assertEquals(1.60f, pessoa.getAltura(), 0.1f, "Altura válida deve ser aceita corretamente");
  }

  @Test
  @DisplayName("Testando o método setDataNascimento")
  public void testSetDataNascimento() {
    pessoa.setDataNascimento(LocalDate.now());
    assertEquals(
        LocalDate.of(1990, 1, 1),
        pessoa.getDataNascimento(),
        "Data de nascimento no presente deve ser nula");

    pessoa.setDataNascimento(LocalDate.now().plusYears(5));
    assertEquals(
        LocalDate.of(1990, 1, 1),
        pessoa.getDataNascimento(),
        "Data de nascimento no futuro deve ser nula");

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
    assertEquals(
        "Pessoa Um", pessoa.getNome(), "Nome com caracteres especiais e números deve ser nulo");

    pessoa.setNome("joão");
    assertEquals("joão", pessoa.getNome(), "Nome com acento deve ser aceito corretamente");

    pessoa.setNome("Marcos Alberto");
    assertEquals("Marcos Alberto", pessoa.getNome(), "Nome válido deve ser aceito corretamente");
  }

  @Test
  @DisplayName("Testando o método setPeso")
  public void testSetPeso() {
    pessoa.setPeso(-1);
    assertEquals(100.0f, pessoa.getPeso(), "Peso negativo deve ser ajustado para 0.0");

    pessoa.setPeso(605);
    assertEquals(100.0f, pessoa.getPeso(), "Peso acima de 600 deve ser ajustado para 0.0");

    pessoa.setPeso(60);
    assertEquals(60.0f, pessoa.getPeso(), "Peso válido deve ser aceito corretamente");
  }

  @Test
  @DisplayName("Testando o método setRenda")
  public void testSetRenda() {
    pessoa.setRenda(-1);
    assertEquals(5000.0, pessoa.getRenda(), "Renda negativa deve ser ajustada para 0.0");

    pessoa.setRenda(2652.42f);
    assertEquals(2652.42f, pessoa.getRenda(), "Renda válida deve ser aceita corretamente");
  }

  @Test
  @DisplayName("Testando o método setNaturalidade")
  public void testSetNaturalidade() {
    pessoa.setNaturalidade("Mineir1!@");
    assertEquals(
        "Cidade Um",
        pessoa.getNaturalidade(),
        "Naturalidade com caracteres especiais e números deve ser nula");

    pessoa.setNaturalidade("Mineiro");
    assertEquals(
        "Mineiro", pessoa.getNaturalidade(), "Naturalidade válida deve ser aceita corretamente");
  }
}
