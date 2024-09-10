import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Dataset;
import br.lpm.business.DistanceMeasure;
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

public class DistanceMeasureTest {

  private Dataset dataset;
  private Pessoa pessoa1;
  private Pessoa pessoa2;
  private Pessoa pessoaIdenticaApessoa1;
  private DistanceMeasure distanceMeasure;

  @BeforeEach
  public void setUp() {
    pessoa1 =
        new Pessoa(
            "Marcos",
            LocalDate.of(2005, 10, 3),
            Genero.MASCULINO,
            1.70f,
            70,
            600.54f,
            "Santa Luzia",
            Hobby.NENHUM,
            EstadoCivil.CASADO,
            Escolaridade.SUPERIOR,
            true,
            Moradia.CASA_PROPRIA);

    pessoa2 =
        new Pessoa(
            "Jamilly",
            LocalDate.of(2006, 2, 24),
            Genero.FEMININO,
            1.40f,
            80,
            100.12f,
            "Santa Luzia",
            Hobby.ESPORTE,
            EstadoCivil.SOLTEIRO,
            Escolaridade.MEDIO,
            true,
            Moradia.ALUGUEL);

    pessoaIdenticaApessoa1 =
        new Pessoa(
            "Marcos",
            LocalDate.of(2005, 10, 3),
            Genero.MASCULINO,
            1.70f,
            70,
            600.54f,
            "Santa Luzia",
            Hobby.NENHUM,
            EstadoCivil.CASADO,
            Escolaridade.SUPERIOR,
            true,
            Moradia.CASA_PROPRIA);

    dataset = new Dataset();
    distanceMeasure = new DistanceMeasure(dataset);
  }
  @Test
  @DisplayName("Testando a distância calculada entre duas pessoas")
  public void testCalcDistance() {
      dataset.removeAll();
      dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    float distance1 = distanceMeasure.calcDistance(pessoa1, pessoa2);
    assertEquals(
        0.894f,
        distance1,
        0.01f,
        "A distância calculada entre pessoa1 e pessoa2 deve ser 0.84079534");
    dataset.addPessoa(pessoaIdenticaApessoa1);
    float distance2 = distanceMeasure.calcDistance(pessoa1, pessoaIdenticaApessoa1);
    assertEquals(0.0f, distance2, 0.01f, "A distância entre pessoas idênticas deve ser 0.0");
  }
}
