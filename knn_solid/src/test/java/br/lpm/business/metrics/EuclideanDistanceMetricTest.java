package br.lpm.business.metrics;

import org.junit.jupiter.api.Test;

import br.lpm.business.datamodel.DataPoint;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.datamodel.Attribute;

public class EuclideanDistanceMetricTest {

  private EuclideanDistanceMetric medidaDistancia;
  private DataPoint pontoDados1;
  private DataPoint pontoDados2;
  private DataPoint pontoDados3;
  private DataPoint pontoDadosComAtributoExtra;

  @BeforeEach
  void setUp() {
    medidaDistancia = new EuclideanDistanceMetric();
    pontoDados1 = new DataPoint();
    pontoDados1.addAttribute(new Attribute(5));
    pontoDados1.addAttribute(new Attribute(3.0));
    pontoDados1.setState("Ponto1");

    pontoDados2 = new DataPoint();
    pontoDados2.addAttribute(new Attribute(8));
    pontoDados2.addAttribute(new Attribute(6.0));
    pontoDados2.setState("Ponto2");

    pontoDados3 = new DataPoint();
    pontoDados3.addAttribute(new Attribute(5));
    pontoDados3.addAttribute(new Attribute("Sim"));
    pontoDados3.setState("Ponto3");

    pontoDadosComAtributoExtra = new DataPoint();
    pontoDadosComAtributoExtra.addAttribute(new Attribute(1));
    pontoDadosComAtributoExtra.addAttribute(new Attribute(2.0));
    pontoDadosComAtributoExtra.addAttribute(new Attribute("Extra"));
  }

  @Test
  void testDistance() {
    double distanciaEsperada1 = Math.sqrt(Math.pow(8 - 5, 2) + Math.pow(6.0 - 3.0, 2)) / Math.sqrt(2);
    double distanciaCalculada1 = medidaDistancia.distance(pontoDados1, pontoDados2);
    assertEquals(distanciaEsperada1, distanciaCalculada1, 0.001,
        "A distância entre pontos numéricos não é a esperada.");

    double distanciaEsperada2 = Math.sqrt(1.0) / Math.sqrt(2);
    double distanciaCalculada2 = medidaDistancia.distance(pontoDados1, pontoDados3);
    assertEquals(distanciaEsperada2, distanciaCalculada2, 0.001, "A distância entre tipos mistos não é a esperada.");

    double distanciaCalculadaInvalida = medidaDistancia.distance(pontoDados1, pontoDadosComAtributoExtra);
    assertEquals(-1, distanciaCalculadaInvalida,
        "A distância para pontos com diferentes números de atributos deveria ser inválida (-1).");
  }

}
