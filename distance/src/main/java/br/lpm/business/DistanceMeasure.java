package br.lpm.business;

public class DistanceMeasure {
  private Dataset dataset;
  private static final int QUANTIDADE_ATRIBUTO = 10;

  public DistanceMeasure(Dataset dataset) {
    this.dataset = dataset;
  }

  public DistanceMeasure() {}

  public float[] normalizeField(String fieldName) {
    if (fieldName == null || fieldName.isEmpty()) {
      return new float[0];
    }
    
    if (this.dataset == null) {
      throw new IllegalStateException("Dataset não foi inicializado.");
    }

    Pessoa[] pessoas = dataset.getAll();
    float[] valoresNormalizados = new float[pessoas.length];
    normalizarCampo(pessoas, valoresNormalizados, fieldName);
    return valoresNormalizados;
  }

  private void normalizarCampo(Pessoa[] pessoas, float[] valoresNormalizados, String fieldName) {
    switch (fieldName.toLowerCase()) {
      case "peso":
        calcularNormalize(
            pessoas, valoresNormalizados, dataset.minPeso(), dataset.maxPeso(), "peso");
        return; 
      case "altura":
        calcularNormalize(
            pessoas, valoresNormalizados, dataset.minAltura(), dataset.maxAltura(), "altura");
        return;
      case "renda":
        calcularNormalize(
            pessoas, valoresNormalizados, dataset.minRenda(), dataset.maxRenda(), "renda");
        return;
      case "idade":
        calcularNormalize(
            pessoas, valoresNormalizados, dataset.minIdade(), dataset.maxIdade(), "idade");
        return; 
      default:
        for (int i = 0; i < pessoas.length; i++) {
          valoresNormalizados[i] = 0.0f;
        }
        return; 
    }
  }

  private float getField(Pessoa pessoa, String fieldName) {
    switch (fieldName) {
      case "peso":
        return (float) pessoa.getPeso();
      case "idade":
        return (float) dataset.calcularIdade(pessoa);
      case "altura":
        return pessoa.getAltura();
      case "renda":
        return pessoa.getRenda();
      default:
        return 0.0f;
    }
  }
  
  private void calcularNormalize(Pessoa[] pessoas, float[] valoresNormalizados, float minino, float maximo,String fieldName) {
    for (int i = 0; i < dataset.size(); i++) {
      float valor = getField(pessoas[i], fieldName);
      valoresNormalizados[i] = (maximo == minino) ? 0 : ((valor - minino) / (maximo - minino));
    }
  }

  public float calcDistance(Pessoa first, Pessoa second) {
    if (first == null || second == null) {
      return 0.0f;
    }
    int[] distanciasEnumBooleans = calcularDistanciasEnunsBoolenan(first, second);
    int qtdAtributosNumerico=QUANTIDADE_ATRIBUTO-6;
    float[] distanciasCamposNumericos =new float[qtdAtributosNumerico];
      distanciasCamposNumericos[0] = calcularDistanciasCamposNumericos(first, second, "idade");
      distanciasCamposNumericos[1] = calcularDistanciasCamposNumericos(first, second, "peso");
      distanciasCamposNumericos[2] = calcularDistanciasCamposNumericos(first, second, "altura");
      distanciasCamposNumericos[3] = calcularDistanciasCamposNumericos(first, second, "renda");
    float somaDosQuadrados = somaAoQuadrado(distanciasEnumBooleans, distanciasCamposNumericos);
    return (float) Math.sqrt(
        somaDosQuadrados / QUANTIDADE_ATRIBUTO);
  }
  
  private int[] calcularDistanciasEnunsBoolenan(Pessoa first, Pessoa second) {
    int[] distanciasEnumBoolean = {
        first.getEscolaridade().equals(second.getEscolaridade()) ? 0 : 1,
        first.getEstadoCivil().equals(second.getEstadoCivil()) ? 0 : 1,
        first.getGenero().equals(second.getGenero()) ? 0 : 1,
        first.getHobby().equals(second.getHobby()) ? 0 : 1,
        first.getMoradia().equals(second.getMoradia()) ? 0 : 1,
        first.isFeliz() == second.isFeliz() ? 0 : 1
    };
    return distanciasEnumBoolean;
  }
  
  private float calcularDistanciasCamposNumericos(Pessoa first, Pessoa second, String fieldName) {
    float[] valorNormalizados = normalizeField(fieldName);
    int posicaoPessoaFirst = dataset.getPosicaoDaPessoa(first);
    int posicaoPessoaSecond = dataset.getPosicaoDaPessoa(second);
    float valorDistancia = valorNormalizados[posicaoPessoaFirst] - valorNormalizados[posicaoPessoaSecond];
    return Math.abs(valorDistancia);
  }
  
  private float somaAoQuadrado(int[] distanciasEnumBooleans, float[] distanciasCamposNumericos) {
    float soma = 0.0f;
    for (int i = 0; i < distanciasEnumBooleans.length; i++) {
      soma += (distanciasEnumBooleans[i] * distanciasEnumBooleans[i]);
    }
    for (int i = 0; i < distanciasCamposNumericos.length; i++) {
      soma += (distanciasCamposNumericos[i] * distanciasCamposNumericos[i]);
    }
    return soma;
  }
}
