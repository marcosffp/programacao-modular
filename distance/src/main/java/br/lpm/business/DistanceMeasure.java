package br.lpm.business;

public class DistanceMeasure {
  private Dataset dataset;
  private final int NUMERO_ATRIBUTO = 10;

  public DistanceMeasure(Dataset dataset) {
    this.dataset = dataset;
  }

  public DistanceMeasure() {
    
  }

  public float[] normalizeField(String fieldName) {
    if (fieldName == null || fieldName.isEmpty()) {
      return new float[0];
    }
    Pessoa[] pessoas = dataset.getAll();
    float[] valoresNormalizados = new float[dataset.size()];

    for (int i = 0; i < valoresNormalizados.length; i++) {
      valoresNormalizados[i] = normalizeAtributo(pessoas[i], fieldName);
    }

    return valoresNormalizados;
  }

  private float normalizeAtributo(Pessoa pessoa, String fieldName) {
    if (pessoa==null) {
      return 0.0f;
    }
    switch (fieldName.toUpperCase()) {
      case "PESO":
        return normalizeFloat(pessoa.getPeso(), dataset.minPeso(), dataset.maxPeso());
      case "ALTURA":
        return normalizeFloat(pessoa.getAltura(), dataset.minAltura(), dataset.maxAltura());
      case "RENDA":
        return normalizeFloat(pessoa.getRenda(), dataset.minRenda(), dataset.maxRenda());
      case "IDADE":
        return normalizeFloat(
            dataset.calcularIdade(pessoa), dataset.minIdade(), dataset.maxIdade());
      default:
        return 0.0f;
    }
  }

  public float calcDistance(Pessoa first, Pessoa second) {
    if (first == null || second == null) {
      return 0.0f;
    }
    int numeroEnumBoolean = 6;
    int[] distanciasEnumBoolean = calcularDistanciasEnumBoolean(first, second, numeroEnumBoolean);
    float[] distanciasReaisInteiros = calcularDistanciasNormalizados(first, second, numeroEnumBoolean, dataset);
    float somaDosQuadrado = calcularSomaDosQuadrado(distanciasEnumBoolean, distanciasReaisInteiros);
    return (float) Math.sqrt(somaDosQuadrado / NUMERO_ATRIBUTO);
  }


  private float normalizeFloat(float valor, float min, float max) {
    if (max == min) {
      return 0;
    }
    return (valor - min) / (max - min);
  }

  private float normalizeInt(int valor, int min, int max) {
    if (max == min) {
      return 0;
    }
    return (valor - min) / (max - min);
  }

  private int[] calcularDistanciasEnumBoolean(Pessoa first, Pessoa second, int numeroEnumBoolean) {
    if (first == null || second == null) {
      return new int[0];
    }

    int[] distancias = new int[numeroEnumBoolean];

    distancias[0] = first.getEscolaridade().equals(second.getEscolaridade()) ? 0 : 1;
    distancias[1] = first.getEstadoCivil().equals(second.getEstadoCivil()) ? 0 : 1;
    distancias[2] = first.getGenero().equals(second.getGenero()) ? 0 : 1;
    distancias[3] = first.getHobby().equals(second.getHobby()) ? 0 : 1;
    distancias[4] = first.getMoradia().equals(second.getMoradia()) ? 0 : 1;
    distancias[5] = first.isFeliz() == second.isFeliz() ? 0 : 1;

    return distancias;
  }

  private float[] calcularDistanciasNormalizados(Pessoa first, Pessoa second, int numeroReaisInteiro, Dataset dataset) {
    if (first == null || second == null || dataset == null) {
      return new float[0];
    }

    float[] distancias = new float[numeroReaisInteiro];

    float normalizadoIdadeFirst = normalizeInt(dataset.calcularIdade(first), dataset.minIdade(), dataset.maxIdade());
    float normalizadoIdadeSecond = normalizeInt(dataset.calcularIdade(second), dataset.minIdade(), dataset.maxIdade());
    distancias[0] = Math.abs(normalizadoIdadeFirst - normalizadoIdadeSecond);

    float normalizadoPesoFirst = normalizeInt(first.getPeso(), dataset.minPeso(), dataset.maxPeso());
    float normalizadoPesoSecond = normalizeInt(second.getPeso(), dataset.minPeso(), dataset.maxPeso());
    distancias[1] = Math.abs(normalizadoPesoFirst - normalizadoPesoSecond);

    float normalizadoAlturaFirst = normalizeFloat(first.getAltura(), dataset.minAltura(), dataset.maxAltura());
    float normalizadoAlturaSecond = normalizeFloat(second.getAltura(), dataset.minAltura(), dataset.maxAltura());
    distancias[2] = Math.abs(normalizadoAlturaFirst - normalizadoAlturaSecond);

    float normalizadoRendaFirst = normalizeFloat(first.getRenda(), dataset.minRenda(), dataset.maxRenda());
    float normalizadoRendaSecond = normalizeFloat(second.getRenda(), dataset.minRenda(), dataset.maxRenda());
    distancias[3] = Math.abs(normalizadoRendaFirst - normalizadoRendaSecond);

    return distancias;
  }

  private float calcularSomaDosQuadrado(int[] distanciasEnumBoolean, float[] distanciasReaisInteiros) {
    if (distanciasEnumBoolean == null || distanciasReaisInteiros == null) {
      return 0.0f;
    }

    int[] distanciasEbs = distanciasEnumBoolean;
    float[] distanciasRis = distanciasReaisInteiros;
    float somaDosQuadrado = 0.0F;
    for (int i = 0; i < distanciasRis.length; i++) {
      somaDosQuadrado += distanciasRis[i] * distanciasRis[i];
    }
    for (int i = 0; i < distanciasEbs.length; i++) {
      somaDosQuadrado += distanciasEbs[i] * distanciasEbs[i];
    }
    return somaDosQuadrado;
  }
}
