import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Dataset;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Knn;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnnTest {
  public  Knn knn;
  private Dataset dataset;
  private Pessoa pessoa1;
  private Pessoa pessoa2;
  private Pessoa pessoa3;

  @BeforeEach
  public void setUp() {
    pessoa1 = new Pessoa(
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

    pessoa2 = new Pessoa(
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
    
    pessoa3 = new Pessoa(
        "Joao",
        LocalDate.of(2006, 2, 24),
        Genero.MASCULINO,
        1.40f,
        80,
        100.12f,
        "Santa Luzia",
        Hobby.ESPORTE,
        EstadoCivil.SOLTEIRO,
        Escolaridade.MEDIO,
        true,
        Moradia.ALUGUEL); 

    dataset = new Dataset();
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    knn= new Knn(2,dataset);
  }
  
  @Test
  @DisplayName("Testando o método classifyFeliz")
  public void testClassifyFeliz() {
    assertEquals(true, knn.classifyFeliz(pessoa1));
    assertEquals(true, knn.classifyFeliz(pessoa2));
    assertEquals(true, knn.classifyFeliz(pessoa3));
  }
  
}
