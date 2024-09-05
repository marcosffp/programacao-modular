package br.lpm.business;

import java.time.LocalDate;

public class Dataset {

  private int quantidadePessoa;
  private static final int MAX_PESSOAS = 100;
  private static Pessoa[] pessoas = new Pessoa[MAX_PESSOAS];
  private DistanceMeasure distanceMeasure;

  public Dataset(){}
  public Dataset(DistanceMeasure distanceMeasure) {
    this.distanceMeasure = distanceMeasure;
  }

  public static int getMaxPessoas() {
    return MAX_PESSOAS;
  }

  public void addPessoa(Pessoa pessoa) {
    if (pessoa == null) {
      return;
    }
    if (quantidadePessoa >= MAX_PESSOAS) {
      return;
    }
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].equals(pessoa)) {
        return;
      }
    }
    pessoas[quantidadePessoa++] = pessoa;
  }

  private void executarRemocao(int i) {
    for (int j = i; j < quantidadePessoa - 1; j++) {
      pessoas[j] = pessoas[j + 1];
    }
    pessoas[--quantidadePessoa] = null;
  }

  public void removePessoa(Pessoa pessoa) {
    if (pessoa == null) {
      return;
    }
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].equals(pessoa)) {
        executarRemocao(i);
        return;
      }
    }
  }

  public void removePessoaByName(String nome) {
    if (nome == null) {
      return;
    }
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getNome().equalsIgnoreCase(nome)) {
        executarRemocao(i);
        return;
      }
    }
  }

  public void replacePessoa(Pessoa velha, Pessoa nova) {
    if (velha == null || nova == null) {
      return;
    }
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].equals(velha)) {
        pessoas[i] = nova;
        return;
      }
    }
  }

  public Pessoa getPessoaByName(String nome) {
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getNome().equalsIgnoreCase(nome)) {
        return pessoas[i];
      }
    }
    return null;
  }

  public Pessoa[] getAll() {
    Pessoa[] vetor = new Pessoa[quantidadePessoa];
    for (int i = 0; i < quantidadePessoa; i++) {
      vetor[i] = pessoas[i];
    }
    return vetor;
  }

  public void removeAll() {
    for (int i = 0; i < quantidadePessoa; i++) {
      pessoas[i] = null;
    }
    quantidadePessoa = 0;
  }

  public int size() {
    return quantidadePessoa;
  }

  public float maxAltura() {
    return calcularMaximoFloat(pessoas, "altura");
  }

  public float minAltura() {
    return calcularMinimoFloat(pessoas, "altura");
  }

  public float maxRenda() {
    return calcularMaximoFloat(pessoas, "renda");
  }

  public float minRenda() {
    return calcularMinimoFloat(pessoas, "renda");
  }

  private float calcularMaximoFloat(Pessoa[] pessoas, String tipo) {
    if (quantidadePessoa == 0) {
      return 0;
    }
    float maximo = obterValorFloat(pessoas[0], tipo);
    for (int i = 0; i < quantidadePessoa; i++) {
      float valorAtual = obterValorFloat(pessoas[i], tipo);
      if (valorAtual > maximo) {
        maximo = valorAtual;
      }
    }
    return maximo;
  }

  private float calcularMinimoFloat(Pessoa[] pessoas, String tipo) {
    if (quantidadePessoa == 0) {
      return 0;
    }
    float minimo = obterValorFloat(pessoas[0], tipo);
    for (int i = 0; i < quantidadePessoa; i++) {
      float valorAtual = obterValorFloat(pessoas[i], tipo);
      if (valorAtual < minimo) {
        minimo = valorAtual;
      }
    }
    return minimo;
  }

  private float obterValorFloat(Pessoa pessoa, String tipo) {
    switch (tipo) {
      case "altura":
        return pessoa.getAltura();
      case "renda":
        return pessoa.getRenda();
      default:
        return 0.0f;
    }
  }

  public float avgAltura() {
    if (quantidadePessoa == 0) {
      return 0;
    }
    float soma = 0.0f;
    for (int i = 0; i < quantidadePessoa; i++) {
      soma += pessoas[i].getAltura();
    }
    return soma / quantidadePessoa;

  }

  public float avgPeso() {
    if (quantidadePessoa == 0) {
      return 0;
    }
    float soma = 0.0f;
    for (int i = 0; i < quantidadePessoa; i++) {
      soma += pessoas[i].getPeso();
    }
    return soma / quantidadePessoa;
  }

  public int maxPeso() {
    return calcularMaximoInt(pessoas, "peso");
  }

  public int minPeso() {
    return calcularMinimoInt(pessoas, "peso");
  }


  private int calcularIdade(Pessoa pessoa) {
    LocalDate dataNascimento = pessoa.getDataNascimento();
    LocalDate dataAtual = LocalDate.now();
    int idade = dataAtual.getYear() - dataNascimento.getYear();
    if (dataNascimento.getMonthValue() > dataAtual.getMonthValue()
        || dataNascimento.getMonthValue() == dataAtual.getMonthValue()
            && dataNascimento.getDayOfMonth() > dataAtual.getDayOfMonth()) {
      idade--;
    }
    return idade;
  }

  private float calcularPorcentagem(int quantidade) {
    if (quantidadePessoa == 0) {
      return 0;
    }
    return ((float) quantidade / quantidadePessoa) * 100;
  }

  public float percentAdult() {
    int quantidadeAdultos = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (calcularIdade(pessoas[i]) >= 18) {
        quantidadeAdultos++;
      }
    }
    return calcularPorcentagem(quantidadeAdultos);
  }

  public float percentEstadoCivil(EstadoCivil estadoCivil) {
    int estadoCivilDeterminado = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getEstadoCivil().equals(estadoCivil)) {
        estadoCivilDeterminado++;
      }
    }
    return calcularPorcentagem(estadoCivilDeterminado);
  }

  public float percentEscolaridade(Escolaridade escolaridade) {
    int escolaridadeDeterminado = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getEscolaridade().equals(escolaridade)) {
        escolaridadeDeterminado++;
      }
    }
    return calcularPorcentagem(escolaridadeDeterminado);
  }

  public float percentMoradia(Moradia moradia) {
    int moradiaDeterminado = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getMoradia().equals(moradia)) {
        moradiaDeterminado++;
      }
    }
    return calcularPorcentagem(moradiaDeterminado);
  }

  public float percentHobby() {
    int hobby = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (!(pessoas[i].getHobby().equals(Hobby.NENHUM))) {
        hobby++;
      }
    }
    return calcularPorcentagem(hobby);
  }

  public float percentFeliz() {
    int felizquantidadePessoa = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].isFeliz()) {
        felizquantidadePessoa++;
      }
    }
    return calcularPorcentagem(felizquantidadePessoa);
  }

  public EstadoCivil modeEstadoCivil() {
    int casado = 0;
    int divorciado = 0;
    int separado = 0;
    int solteiro = 0;
    int viuvo = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getEstadoCivil().equals(EstadoCivil.CASADO)) {
        casado++;
      }
      if (pessoas[i].getEstadoCivil().equals(EstadoCivil.DIVORCIADO)) {
        divorciado++;
      }
      if (pessoas[i].getEstadoCivil().equals(EstadoCivil.SEPARADO)) {
        separado++;
      }
      if (pessoas[i].getEstadoCivil().equals(EstadoCivil.SOLTEIRO)) {
        solteiro++;
      }
      if (pessoas[i].getEstadoCivil().equals(EstadoCivil.VIUVO)) {
        viuvo++;
      }
    }
    int quantMaxPessoa = casado;
    EstadoCivil mode = EstadoCivil.CASADO;
    if (divorciado > quantMaxPessoa) {
      quantMaxPessoa = divorciado;
      mode = EstadoCivil.DIVORCIADO;
    }
    if (separado > quantMaxPessoa) {
      quantMaxPessoa = separado;
      mode = EstadoCivil.SEPARADO;
    }
    if (solteiro > quantMaxPessoa) {
      quantMaxPessoa = solteiro;
      mode = EstadoCivil.SOLTEIRO;
    }
    if (viuvo > quantMaxPessoa) {
      quantMaxPessoa = viuvo;
      mode = EstadoCivil.VIUVO;
    }

    return mode;
  }

  public Escolaridade modeEscolaridade() {
    int nenhuma = 0;
    int fundamental = 0;
    int medio = 0;
    int superior = 0;
    int posGraduacao = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getEscolaridade().equals(Escolaridade.NENHUMA)) {
        nenhuma++;
      }
      if (pessoas[i].getEscolaridade().equals(Escolaridade.FUNDAMENTAL)) {
        fundamental++;
      }
      if (pessoas[i].getEscolaridade().equals(Escolaridade.MEDIO)) {
        medio++;
      }
      if (pessoas[i].getEscolaridade().equals(Escolaridade.SUPERIOR)) {
        superior++;
      }
      if (pessoas[i].getEscolaridade().equals(Escolaridade.POS_GRADUACAO)) {
        posGraduacao++;
      }
    }
    int quantMaxPessoa = nenhuma;
    Escolaridade mode = Escolaridade.NENHUMA;
    if (fundamental > quantMaxPessoa) {
      quantMaxPessoa = fundamental;
      mode = Escolaridade.FUNDAMENTAL;
    }
    if (medio > quantMaxPessoa) {
      quantMaxPessoa = medio;
      mode = Escolaridade.MEDIO;
    }
    if (superior > quantMaxPessoa) {
      quantMaxPessoa = superior;
      mode = Escolaridade.SUPERIOR;
    }
    if (posGraduacao > quantMaxPessoa) {
      quantMaxPessoa = posGraduacao;
      mode = Escolaridade.POS_GRADUACAO;
    }
    return mode;
  }

  public Moradia modeMoradia() {
    int comFamilia = 0;
    int aluguel = 0;
    int casaPropria = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getMoradia().equals(Moradia.COM_FAMILIA)) {
        comFamilia++;
      }
      if (pessoas[i].getMoradia().equals(Moradia.ALUGUEL)) {
        aluguel++;
      }
      if (pessoas[i].getMoradia().equals(Moradia.CASA_PROPRIA)) {
        casaPropria++;
      }
    }
    int quantMaxPessoa = comFamilia;
    Moradia mode = Moradia.COM_FAMILIA;
    if (aluguel > quantMaxPessoa) {
      quantMaxPessoa = aluguel;
      mode = Moradia.ALUGUEL;
    }
    if (casaPropria > quantMaxPessoa) {
      quantMaxPessoa = casaPropria;
      mode = Moradia.CASA_PROPRIA;
    }
    return mode;
  }

  private int calcularMaximoInt(Pessoa[] pessoas, String tipo) {
    if (quantidadePessoa == 0) {
      return 0;
    }
    int maximo = obterValorInt(pessoas[0], tipo);
    for (int i = 0; i < quantidadePessoa; i++) {
      int valorAtual = obterValorInt(pessoas[i], tipo);
      if (valorAtual > maximo) {
        maximo = valorAtual;
      }
    }
    return maximo;
  }

  private int calcularMinimoInt(Pessoa[] pessoas, String tipo) {
    if (quantidadePessoa == 0) {
      return 0;
    }
    int minimo = obterValorInt(pessoas[0], tipo);
    for (int i = 0; i < quantidadePessoa; i++) {
      int valorAtual = obterValorInt(pessoas[i], tipo);
      if (valorAtual < minimo) {
        minimo = valorAtual;
      }
    }
    return minimo;
  }

  private int obterValorInt(Pessoa pessoa, String tipo) {
    switch (tipo) {
      case "peso":
        return pessoa.getPeso();
      default:
        return 0;
    }
  }

  public float[] calcDistanceVector(Pessoa pessoa) {
    float[] distances = new float[quantidadePessoa];
    for (int i = 0; i < quantidadePessoa; i++) {
      distances[i] =distanceMeasure.calcDistance(pessoa, pessoas[i]);
    }
    return distances; 
  }

  public float[][] calcDistanceMatrix() {
    float[][] distances = new float[quantidadePessoa][quantidadePessoa];
    for (int i = 0; i < quantidadePessoa; i++) {
      for (int j = 0; j < quantidadePessoa; j++) {
        if (i != j) {
          distances[i][j] = distanceMeasure.calcDistance(pessoas[i], pessoas[j]);
        } else {
          distances[i][j] = 0;
        }
      }
    }
    return distances;
  }

  private float[] calcularDistancias(Pessoa pessoa, Pessoa[] pessoas) {
    float[] similares = new float[quantidadePessoa];
    for (int i = 0; i < quantidadePessoa; i++) {
      similares[i] = distanceMeasure.calcDistance(pessoa, pessoas[i]);
    }
    return similares;
  }

  private void ordenarPessoas(Pessoa[] pessoas, float[] similares) {
    for (int i = 1; i < quantidadePessoa; i++) {
      for (int j = 0; j < i; j++) {
        if (similares[i] > similares[j]) {
          float aux = similares[i];
          similares[i] = similares[j];
          similares[j] = aux;

          Pessoa temp = pessoas[i];
          pessoas[i] = pessoas[j];
          pessoas[j] = temp;
        }
      }
    }
  }

  private Pessoa[] selecionarMaisSimilares(Pessoa[] pessoas, int n) {
    Pessoa[] maisSimilares = new Pessoa[n];
    for (int i = 0; i < n; i++) {
      maisSimilares[i] = pessoas[i];
    }
    return maisSimilares;
  }

  public Pessoa[] getSimilar(Pessoa pessoa, int n) {
    if (n <= 0 || quantidadePessoa == 0) {
      return new Pessoa[0];
    }

    Pessoa[] pessoas = getAll();
    float[] similares = calcularDistancias(pessoa, pessoas);
    ordenarPessoas(pessoas, similares);

    return selecionarMaisSimilares(pessoas, n);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n")
        .append("Altura Máxima: ")
        .append(String.format("%.2f", maxAltura()))
        .append("\n")
        .append("Altura Mínima: ")
        .append(String.format("%.2f", minAltura()))
        .append("\n")
        .append("Altura Média: ")
        .append(String.format("%.2f", avgAltura()))
        .append("\n")
        .append("Peso Máximo: ")
        .append(maxPeso())
        .append("\n")
        .append("Peso Mínimo: ")
        .append(minPeso())
        .append("\n")
        .append("Peso Médio: ")
        .append(String.format("%.2f", avgPeso()))
        .append("\n")
        .append("Percentual de Adultos: ")
        .append(String.format("%.2f", percentAdult()))
        .append("%\n")
        .append("Percentual de Pessoas com Hobbies: ")
        .append(String.format("%.2f", percentHobby()))
        .append("%\n")
        .append("Percentual de Pessoas Felizes: ")
        .append(String.format("%.2f", percentFeliz()))
        .append("%\n")
        .append("**************************************************************\n");
    return sb.toString();
  }
}
