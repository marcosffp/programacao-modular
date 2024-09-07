package br.lpm.business;

import java.time.LocalDate;

public class Dataset {

  private int quantidadePessoa;
  private static final int MAX_PESSOAS = 100;
  private static Pessoa[] pessoas = new Pessoa[MAX_PESSOAS];
  private DistanceMeasure distanceMeasure;

  // Construtor com DistanceMeasure
  public Dataset(DistanceMeasure distanceMeasure) {
    this.distanceMeasure = distanceMeasure;
  }

  // Construtor vazio que cria um DistanceMeasure
  public Dataset() {
    this.distanceMeasure = new DistanceMeasure(this);
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
    if (nome == null || nome.isEmpty()) {
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
    if (velha == null || nova == null||velha.equals(nova)) {
      return;
    }
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].equals(velha)) {
        for (int j = 0; j < quantidadePessoa; j++) {
          if (pessoas[i].equals(nova)) {
            return;
          }
        }
        pessoas[i] = nova;
        return;
      }
    }
  }

  public Pessoa getPessoaByName(String nome) {
    if (nome == null || nome.isEmpty()) {
      return null; 
    }
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

  public int calcularIdade(Pessoa pessoa) {
    if (pessoa == null || pessoa.getDataNascimento() == null) {
      return 0;
    }

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
    if (estadoCivil==null) {
      return 0.0f;
    }
    int estadoCivilDeterminado = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getEstadoCivil().equals(estadoCivil)) {
        estadoCivilDeterminado++;
      }
    }
    return calcularPorcentagem(estadoCivilDeterminado);
  }

  public float percentEscolaridade(Escolaridade escolaridade) {
    if (escolaridade == null) {
      return 0.0f;
    }
    int escolaridadeDeterminado = 0;
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i].getEscolaridade().equals(escolaridade)) {
        escolaridadeDeterminado++;
      }
    }
    return calcularPorcentagem(escolaridadeDeterminado);
  }

  public float percentMoradia(Moradia moradia) {
    if (moradia == null) {
      return 0.0f;
    }
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
      case "idade":
        return calcularIdade(pessoa);
      default:
        return 0;
    }
  }

  public int maxIdade() {
    return calcularMaximoInt(pessoas, "idade");
  }

  public int minIdade() {
    return calcularMinimoInt(pessoas, "idade");
  }

  public float[] calcDistanceVector(Pessoa pessoa) {
    if (pessoa == null || quantidadePessoa == 0) {
      return new float[0];
    }

    float[] distancias = new float[quantidadePessoa]; 
    for (int i = 0; i < quantidadePessoa; i++) {
        distancias[i] = distanceMeasure.calcDistance(pessoa, pessoas[i]);
    }

    return distancias; 
  }

  public float[][] calcDistanceMatrix() {
    if (distanceMeasure == null || quantidadePessoa == 0) {
      return new float[0][0];
    }

    float[][] distances = new float[quantidadePessoa][quantidadePessoa];
    for (int i = 0; i < quantidadePessoa; i++) {
      for (int j = 0; j < quantidadePessoa; j++) {
        if (i != j) {
          distances[i][j] = distanceMeasure.calcDistance(pessoas[i], pessoas[j]);
        } else {
          distances[i][j] = 0.0f;
        }
      }
    }
    return distances;
  }

  public Pessoa[] getSimilar(Pessoa pessoa, int N) {
    if (N <= 0 || N >= quantidadePessoa || pessoa == null) {
      return new Pessoa[0];
    }


    float[] targetDistances = calcDistanceVector(pessoa);
    float[] closestDistances = initializeMinDistances(N);
    Pessoa[] closestPeople = new Pessoa[N];

    findSimilarPeople(targetDistances, closestDistances, closestPeople, N, pessoa);

    return closestPeople;
  }

  private void findSimilarPeople(
      float[] targetDistances,
      float[] closestDistances,
      Pessoa[] closestPeople,
      int N,
      Pessoa pessoa) {
    for (int i = 0; i < size(); i++) {
      if (targetDistances[i] < closestDistances[N - 1] && !pessoas[i].equals(pessoa)) {
        updateSimilarPeople(targetDistances[i], i, closestDistances, closestPeople, N);
      }
    }
  }

  private void updateSimilarPeople(
      float currentDistance, int index, float[] closestDistances, Pessoa[] closestPeople, int N) {
    for (int j = N - 1; j > 0; j--) {
      if (currentDistance < closestDistances[j - 1]) {
        closestDistances[j] = closestDistances[j - 1];
        closestPeople[j] = closestPeople[j - 1];
      } else {
        closestDistances[j] = currentDistance;
        closestPeople[j] = pessoas[index];
        break;
      }
    }
    if (currentDistance < closestDistances[0]) {
      closestDistances[0] = currentDistance;
      closestPeople[0] = pessoas[index];
    }
  }

  private float[] initializeMinDistances(int numberOfSimilarPeople) {
    float[] minDistances = new float[numberOfSimilarPeople];
    for (int i = 0; i < minDistances.length; i++) {
      minDistances[i] = 43242;
    }
    return minDistances;
  }

  public int getPosicaoDaPessoa(Pessoa pessoa) {
    if (pessoa == null) {
      return -1;
    } 
    for (int i = 0; i < quantidadePessoa; i++) {
      if (pessoas[i] != null && pessoas[i].equals(pessoa)) {
        return i;
      }
    }
    return -1;
  }

  public void setDistanceMeasure(DistanceMeasure distanceMeasure) {
    this.distanceMeasure = distanceMeasure;
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
