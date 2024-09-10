package br.lpm.business;

public class DistanceMeasure {
  private Dataset dataset;
  private static final int QUANTIDADE_ATRIBUTO = 10;

  public DistanceMeasure(Dataset dataset) {
    this.dataset = dataset;
  }


  public float calcDistance(Pessoa first, Pessoa second) {
    if (first == null || second == null) {
      return 0.0f;
    }
    int[] distanciasAtributosCategorizados =
        calcularDistanciasAtributosCategorizados(first, second);
    int quantidadeAtributosNumerico = QUANTIDADE_ATRIBUTO - 6;
    float[] distanciasAtributosNumericos = new float[quantidadeAtributosNumerico];
    distanciasAtributosNumericos[0] = calcularDistanciaAtributosNumericos(first, second, "idade");
    distanciasAtributosNumericos[1] = calcularDistanciaAtributosNumericos(first, second, "peso");
    distanciasAtributosNumericos[2] = calcularDistanciaAtributosNumericos(first, second, "altura");
    distanciasAtributosNumericos[3] = calcularDistanciaAtributosNumericos(first, second, "renda");
    float somaQuadrado =
        calcularSomaQuadrados(distanciasAtributosCategorizados, distanciasAtributosNumericos);
    return (float) Math.sqrt(somaQuadrado / QUANTIDADE_ATRIBUTO);
  }

  private int[] calcularDistanciasAtributosCategorizados(Pessoa first, Pessoa second) {
    int[] distanciasAtributosCategorizados = {
      first.getEscolaridade().equals(second.getEscolaridade()) ? 0 : 1,
      first.getEstadoCivil().equals(second.getEstadoCivil()) ? 0 : 1,
      first.getGenero().equals(second.getGenero()) ? 0 : 1,
      first.getHobby().equals(second.getHobby()) ? 0 : 1,
      first.getMoradia().equals(second.getMoradia()) ? 0 : 1,
      first.isFeliz() == second.isFeliz() ? 0 : 1
    };
    return distanciasAtributosCategorizados;
  }

  private float calcularDistanciaAtributosNumericos(Pessoa first, Pessoa second, String nomeCampo) {
    float[] valoresNormalizados = dataset.normalizeField(nomeCampo);
    int posicaoPrimeiraPessoa = dataset.obterPosicaoPessoa(first);
    int posicaoSegundaPessoa = dataset.obterPosicaoPessoa(second);
    float distanciaAtributo =
        valoresNormalizados[posicaoPrimeiraPessoa] - valoresNormalizados[posicaoSegundaPessoa];
    return Math.abs(distanciaAtributo);
  }

  private float calcularSomaQuadrados(
      int[] distanciasAtributosCategorizados, float[] distanciasAtributosNumericos) {
    float soma = 0.0f;
    for (int i = 0; i < distanciasAtributosCategorizados.length; i++) {
      soma += (distanciasAtributosCategorizados[i] * distanciasAtributosCategorizados[i]);
    }
    for (int i = 0; i < distanciasAtributosNumericos.length; i++) {
      soma += (distanciasAtributosNumericos[i] * distanciasAtributosNumericos[i]);
    }
    return soma;
  }
}
