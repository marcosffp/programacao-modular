package br.lpm.business;

public class DistanceMeasure {
  private Dataset dataset;

  public DistanceMeasure(Dataset dataset) {
    this.dataset = dataset;
  }

  public float[] normalizeField(String fieldName) {
    Pessoa[] pessoas = dataset.getAll();
    float[] valoresNormalizados = new float[dataset.size()];

    for (int i = 0; i < valoresNormalizados.length; i++) {
      valoresNormalizados[i] = normalizeAtributo(pessoas[i], fieldName);
    }
    return valoresNormalizados;
  }

  private float normalizeAtributo(Pessoa pessoa, String fieldName) {
    switch (fieldName.toUpperCase()) {
      case "PESO":
        return normalize(pessoa.getPeso(), dataset.minPeso(), dataset.maxPeso());
      case "ALTURA":
        return normalize(pessoa.getAltura(), dataset.minAltura(), dataset.maxAltura());
      case "RENDA":
        return normalize(pessoa.getRenda(), dataset.minRenda(), dataset.maxRenda());
      case "ESCOLARIDADE":
        return normalizeEnum(
            pessoa.getEscolaridade().ordinal(),
            dataset.minEscolaridade(),
            dataset.maxEscolaridade());
      case "ESTADOCIVIL":
        return normalizeEnum(
            pessoa.getEstadoCivil().ordinal(), dataset.minEstadoCivil(), dataset.maxEstadoCivil());
      case "MORADIA":
        return normalizeEnum(
            pessoa.getMoradia().ordinal(), dataset.minMoradia(), dataset.maxMoradia());
      case "GENERO":
        return normalizeEnum(
            pessoa.getGenero().ordinal(), dataset.minGenero(), dataset.maxGenero());
      case "HOBBY":
        return normalizeEnum(pessoa.getHobby().ordinal(), dataset.minHobby(), dataset.maxHobby());
      case "FELIZ":
        return pessoa.isFeliz() ? 1.0f : 0.0f;
      default:
        return 0.0f;
    }
  }

  private float normalize(float x, float minx, float maxx) {
    if (maxx == minx) {
      return 0;
    }
    return (x - minx) / (maxx - minx);
  }

  private float normalizeEnum(int ordinal, int minOrdinal, int maxOrdinal) {
    return normalize(ordinal, minOrdinal, maxOrdinal);
  }

  public float calcDistance(Pessoa first, Pessoa second) {
    if (first == null || second == null) {
      return 0.0f;
    }

    float[] diferencas = calcularDiferenças(first, second);
    float somaDosQuadrados = calcularSomaDosQuadrados(diferencas);
    return calcularRaizQuadrada(somaDosQuadrados / diferencas.length);
  }

  private float[] calcularDiferenças(Pessoa first, Pessoa second) {
    return new float[] {
      calcularDistanciaFloat(
          first.getPeso(), second.getPeso(), dataset.minPeso(), dataset.maxPeso()),
      calcularDistanciaFloat(
          first.getAltura(), second.getAltura(), dataset.minAltura(), dataset.maxAltura()),
      calcularDistanciaFloat(
          first.getRenda(), second.getRenda(), dataset.minRenda(), dataset.maxRenda()),
      calcularDistanciaEnum(first.getEscolaridade(), second.getEscolaridade()),
      calcularDistanciaEnum(first.getHobby(), second.getHobby()),
      calcularDistanciaEnum(first.getEstadoCivil(), second.getEstadoCivil()),
      calcularDistanciaBoolean(first.isFeliz(), second.isFeliz()),
      calcularDistanciaEnum(first.getMoradia(), second.getMoradia()),
      calcularDistanciaEnum(first.getGenero(), second.getGenero())
    };
  }

  private float calcularDistanciaFloat(float valor1, float valor2, float min, float max) {
    return Math.abs(normalize(valor1, min, max) - normalize(valor2, min, max));
  }

  private float calcularDistanciaEnum(Enum<?> first, Enum<?> second) {
    return (first == second) ? 0.0f : 1.0f;
  }

  private int calcularDistanciaBoolean(boolean first, boolean second) {
    return first == second ? 0 : 1;
  }

  private float calcularSomaDosQuadrados(float[] diferencas) {
    float soma = 0;
    for (float diferenca : diferencas) {
      soma += quadrarDistancia(diferenca);
    }
    return soma;
  }

  private float calcularRaizQuadrada(float valor) {
    return (float) Math.sqrt(valor);
  }

  private float quadrarDistancia(float distancia) {
    return distancia * distancia;
  }
}
