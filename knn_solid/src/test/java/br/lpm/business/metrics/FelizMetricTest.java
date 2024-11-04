package br.lpm.business.metrics;

import br.lpm.business.datamodel.Attribute;
import br.lpm.business.datamodel.DataPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FelizMetricTest {

  private FelizMetric felizMetric;
  private DataPoint pontoDados1;
  private DataPoint pontoDados2;
  private DataPoint pontoDadosComAtributoExtra;

  @BeforeEach
  void setUp() {
    felizMetric = new FelizMetric();

    pontoDados1 = new DataPoint();
    pontoDados1.addAttribute(new Attribute(1.75));
    pontoDados1.addAttribute(new Attribute(70.0));
    pontoDados1.addAttribute(new Attribute(5000.0));
    pontoDados1.addAttribute(new Attribute(2.0));
    pontoDados1.addAttribute(new Attribute(50.0));
    pontoDados1.addAttribute(new Attribute(8000.0));

    pontoDados2 = new DataPoint();
    pontoDados2.addAttribute(new Attribute(1.80));
    pontoDados2.addAttribute(new Attribute(75.0));
    pontoDados2.addAttribute(new Attribute(6000.0));
    pontoDados2.addAttribute(new Attribute(2.5));
    pontoDados2.addAttribute(new Attribute(55.0));
    pontoDados2.addAttribute(new Attribute(9000.0));

    pontoDadosComAtributoExtra = new DataPoint();
    pontoDadosComAtributoExtra.addAttribute(new Attribute(1.60));
    pontoDadosComAtributoExtra.addAttribute(new Attribute(60.0));
    pontoDadosComAtributoExtra.addAttribute(new Attribute(4000.0));
    pontoDadosComAtributoExtra.addAttribute(new Attribute(1.8));
    pontoDadosComAtributoExtra.addAttribute(new Attribute(65.0));
    pontoDadosComAtributoExtra.addAttribute(new Attribute(9500.0));
    pontoDadosComAtributoExtra.addAttribute(new Attribute("Extra"));
  }

  @Test
  void testDistance() {
    double distanciaEsperada = Math.pow(1.75 - 1.80, 2) +
        Math.pow(70.0 - 75.0, 2) +
        Math.pow(5000.0 - 6000.0, 2) +
        (Math.abs(2.0 - 2.5) / 2.60) +
        (Math.abs(50.0 - 55.0) / 600.00) +
        (Math.abs(8000.0 - 9000.0) / 10000.00);

    double distanciaCalculada = felizMetric.distance(pontoDados1, pontoDados2);
    assertEquals(distanciaEsperada, distanciaCalculada, 0.001,
        "A distância entre os pontos com altura, peso e renda não é a esperada.");

    double distanciaCalculadaInvalida = felizMetric.distance(pontoDados1, pontoDadosComAtributoExtra);
    assertEquals(-1, distanciaCalculadaInvalida,
        "A distância para pontos com diferentes números de atributos deveria ser inválida (-1).");
  }
}
