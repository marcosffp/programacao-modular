package br.lpm.business.metrics;

import java.util.List;

import br.lpm.business.datamodel.Attribute;
import br.lpm.business.datamodel.DataPoint;

public class FelizMetric implements Metric {
  private static final double MAX_ALTURA = 2.60;
  private static final double MAX_PESO = 600.00;
  private static final double MAX_RENDA = 10000.00;
  private static final int INVALID = -1;

  @Override
  public double distance(DataPoint p1, DataPoint p2) {
    List<Attribute> atributosPonto1 = p1.getAttributes();
    List<Attribute> atributosPonto2 = p2.getAttributes();

    if (atributosPonto1.size() != atributosPonto2.size()) {
      return INVALID;
    }

    double distance = 0;
    for (int i = 0; i < atributosPonto1.size(); i++) {
      Object valorPonto1 = atributosPonto1.get(i).getValue();
      Object valorPonto2 = atributosPonto2.get(i).getValue();

      if (valorPonto1 instanceof Number && valorPonto2 instanceof Number) {
        double val1 = ((Number) valorPonto1).doubleValue();
        double val2 = ((Number) valorPonto2).doubleValue();

        // Usando um método separado para calcular a distância baseada no índice
        distance += calculateDistance(i, val1, val2);
      } else {
        distance += valorPonto1.equals(valorPonto2) ? 0 : 1;
      }
    }
    return distance;
  }

  private double calculateDistance(int index, double val1, double val2) {
    switch (index) {
      case 3: // Altura
        return Math.abs(val1 - val2) / MAX_ALTURA;
      case 4: // Peso
        return Math.abs(val1 - val2) / MAX_PESO;
      case 5: // Renda
        return Math.abs(val1 - val2) / MAX_RENDA;
      default:
        return Math.pow(val1 - val2, 2);
    }
  }
}