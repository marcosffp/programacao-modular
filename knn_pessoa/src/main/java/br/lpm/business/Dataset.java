package br.lpm.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dataset {

  private static final int NOT_FOUND = -1;
  private static final int MAX_PESSOAS = 1000;
  private static Pessoa[] pessoas = new Pessoa[MAX_PESSOAS];
  private int quantidadePessoas = 0;
  private DistanceMeasure medidaDistancia = new DistanceMeasure(this);

  public static int getMaxPessoas() {
    return MAX_PESSOAS;
  }

  public void addPessoa(Pessoa pessoa) {
    if (pessoa == null || quantidadePessoas >= MAX_PESSOAS) {
      return;
    }
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].equals(pessoa)) {
        return;
      }
    }
    pessoas[quantidadePessoas++] = pessoa;
  }

  private void removerPessoaPorIndice(int indice) {
    for (int j = indice; j < quantidadePessoas - 1; j++) {
      pessoas[j] = pessoas[j + 1];
    }
    pessoas[--quantidadePessoas] = null;
  }

  public void removePessoa(Pessoa pessoa) {
    if (pessoa == null) {
      return;
    }
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].equals(pessoa)) {
        removerPessoaPorIndice(i);
        return;
      }
    }
  }

  public void removePessoaByName(String nome) {
    if (nome == null || nome.isEmpty()) {
      return;
    }
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].getNome().equalsIgnoreCase(nome)) {
        removerPessoaPorIndice(i);
        return;
      }
    }
  }

  public void replacePessoa(Pessoa pessoaAntiga, Pessoa pessoaNova) {
    if (pessoaAntiga == null || pessoaNova == null || pessoaAntiga.equals(pessoaNova)) {
      return;
    }
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].equals(pessoaAntiga)) {
        for (int j = 0; j < quantidadePessoas; j++) {
          if (pessoas[i].equals(pessoaNova)) {
            return;
          }
        }
        pessoas[i] = pessoaNova;
        return;
      }
    }
  }

  public Pessoa getPessoaByName(String nome) {
    if (nome == null || nome.isEmpty()) {
      return null;
    }
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].getNome().equalsIgnoreCase(nome)) {
        return pessoas[i];
      }
    }
    return null;
  }

  public Pessoa[] getAll() {
    return pessoas;
  }

  public void removeAll() {
    for (int i = 0; i < quantidadePessoas; i++) {
      pessoas[i] = null;
    }
    quantidadePessoas = 0;
  }

  public int size() {
    return quantidadePessoas;
  }

  public float maxAltura() {
    return calcularValorMaximoFloat(pessoas, "altura");
  }

  public float minAltura() {
    return calcularValorMinimoFloat(pessoas, "altura");
  }

  public float maxRenda() {
    return calcularValorMaximoFloat(pessoas, "renda");
  }

  public float minRenda() {
    return calcularValorMinimoFloat(pessoas, "renda");
  }

  private float calcularValorMaximoFloat(Pessoa[] pessoas, String tipo) {
    if (quantidadePessoas == 0) {
      return 0;
    }
    float valorMaximo = obterValorPorTipoFloat(pessoas[0], tipo);
    for (int i = 0; i < quantidadePessoas; i++) {
      float valorAtual = obterValorPorTipoFloat(pessoas[i], tipo);
      if (valorAtual > valorMaximo) {
        valorMaximo = valorAtual;
      }
    }
    return valorMaximo;
  }

  private float calcularValorMinimoFloat(Pessoa[] pessoas, String tipo) {
    if (quantidadePessoas == 0) {
      return 0;
    }
    float valorMinimo = obterValorPorTipoFloat(pessoas[0], tipo);
    for (int i = 0; i < quantidadePessoas; i++) {
      float valorAtual = obterValorPorTipoFloat(pessoas[i], tipo);
      if (valorAtual < valorMinimo) {
        valorMinimo = valorAtual;
      }
    }
    return valorMinimo;
  }

  private float obterValorPorTipoFloat(Pessoa pessoa, String tipo) {
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
    if (quantidadePessoas == 0) {
      return 0;
    }
    float somaAltura = 0.0f;
    for (int i = 0; i < quantidadePessoas; i++) {
      somaAltura += pessoas[i].getAltura();
    }
    return somaAltura / quantidadePessoas;
  }

  public float avgPeso() {
    if (quantidadePessoas == 0) {
      return 0;
    }
    float somaPeso = 0.0f;
    for (int i = 0; i < quantidadePessoas; i++) {
      somaPeso += pessoas[i].getPeso();
    }
    return somaPeso / quantidadePessoas;
  }

  public float avgRenda() {
    if (quantidadePessoas == 0) {
      return 0;
    }
    float somaRenda = 0.0f;
    for (int i = 0; i < quantidadePessoas; i++) {
      somaRenda += pessoas[i].getRenda();
    }
    return somaRenda / quantidadePessoas;
  }

  public int maxPeso() {
    return calcularValorMaximoInt(pessoas, "peso");
  }

  public int minPeso() {
    return calcularValorMinimoInt(pessoas, "peso");
  }

  private int calcularValorMaximoInt(Pessoa[] pessoas, String tipo) {
    if (quantidadePessoas == 0) {
      return 0;
    }
    int valorMaximo = obterValorPorTipoInt(pessoas[0], tipo);
    for (int i = 0; i < quantidadePessoas; i++) {
      int valorAtual = obterValorPorTipoInt(pessoas[i], tipo);
      if (valorAtual > valorMaximo) {
        valorMaximo = valorAtual;
      }
    }
    return valorMaximo;
  }

  private int calcularValorMinimoInt(Pessoa[] pessoas, String tipo) {
    if (quantidadePessoas == 0) {
      return 0;
    }
    int valorMinimo = obterValorPorTipoInt(pessoas[0], tipo);
    for (int i = 0; i < quantidadePessoas; i++) {
      int valorAtual = obterValorPorTipoInt(pessoas[i], tipo);
      if (valorAtual < valorMinimo) {
        valorMinimo = valorAtual;
      }
    }
    return valorMinimo;
  }

  private int obterValorPorTipoInt(Pessoa pessoa, String tipo) {
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
    return calcularValorMaximoInt(pessoas, "idade");
  }

  public int minIdade() {
    return calcularValorMinimoInt(pessoas, "idade");
  }

  public int calcularIdade(Pessoa pessoa) {
    if (pessoa == null || pessoa.getDataNascimento() == null) {
      return 0;
    }
    LocalDate dataNascimento = pessoa.getDataNascimento();
    LocalDate dataAtual = LocalDate.now();
    int idade = dataAtual.getYear() - dataNascimento.getYear();
    if (dataNascimento.getMonthValue() > dataAtual.getMonthValue()
        || (dataNascimento.getMonthValue() == dataAtual.getMonthValue()
            && dataNascimento.getDayOfMonth() > dataAtual.getDayOfMonth())) {
      idade--;
    }
    return idade;
  }

  private float calcularPorcentagem(int quantidade) {
    if (quantidadePessoas == 0) {
      return 0;
    }
    return ((float) quantidade / quantidadePessoas) * 100;
  }

  public float percentAdult() {
    int totalAdulto = 0;
    for (int i = 0; i < quantidadePessoas; i++) {
      if (calcularIdade(pessoas[i]) >= 18) {
        totalAdulto++;
      }
    }
    return calcularPorcentagem(totalAdulto);
  }

  public float percentEstadoCivil(EstadoCivil estadoCivil) {
    if (estadoCivil == null) {
      return 0.0f;
    }
    int totalEstadoCivil = 0;
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].getEstadoCivil().equals(estadoCivil)) {
        totalEstadoCivil++;
      }
    }
    return calcularPorcentagem(totalEstadoCivil);
  }

  public float percentEscolaridade(Escolaridade escolaridade) {
    if (escolaridade == null) {
      return 0.0f;
    }
    int totalEscolaridade = 0;
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].getEscolaridade().equals(escolaridade)) {
        totalEscolaridade++;
      }
    }
    return calcularPorcentagem(totalEscolaridade);
  }

  public float percentMoradia(Moradia moradia) {
    if (moradia == null) {
      return 0.0f;
    }
    int totalMoradia = 0;
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].getMoradia().equals(moradia)) {
        totalMoradia++;
      }
    }
    return calcularPorcentagem(totalMoradia);
  }

  public float percentHobby() {
    int totalComHobby = 0;
    for (int i = 0; i < quantidadePessoas; i++) {
      if (!(pessoas[i].getHobby().equals(Hobby.NENHUM))) {
        totalComHobby++;
      }
    }
    return calcularPorcentagem(totalComHobby);
  }

  public float percentGenero(Genero genero) {
    int totalGenero = 0;
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].getGenero().equals(genero)) {
        totalGenero++;
      }
    }
    return calcularPorcentagem(totalGenero);
  }

  public float percentFeliz() {
    int totalFelize = 0;
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i].isFeliz()) {
        totalFelize++;
      }
    }
    return calcularPorcentagem(totalFelize);
  }

  public EstadoCivil modeEstadoCivil() {
    int[] contagemEstados = new int[EstadoCivil.values().length];

    for (int i = 0; i < quantidadePessoas; i++) {
      EstadoCivil estadoCivil = pessoas[i].getEstadoCivil();
      contagemEstados[estadoCivil.getValue()]++;
    }

    int indiceMaximo = 0;
    for (int i = 1; i < contagemEstados.length; i++) {
      if (contagemEstados[i] > contagemEstados[indiceMaximo]) {
        indiceMaximo = i;
      }
    }

    return EstadoCivil.values()[indiceMaximo];
  }

  public Escolaridade modeEscolaridade() {
    int[] contagemEscolaridades = new int[Escolaridade.values().length];
    for (int i = 0; i < quantidadePessoas; i++) {
      Escolaridade escolaridade = pessoas[i].getEscolaridade();
      contagemEscolaridades[escolaridade.getValue()]++;
    }
    int indiceMaximo = 0;
    for (int i = 0; i < contagemEscolaridades.length; i++) {
      if (contagemEscolaridades[i] > contagemEscolaridades[indiceMaximo]) {
        indiceMaximo = i;
      }
    }
    return Escolaridade.values()[indiceMaximo];
  }

  public Moradia modeMoradia() {
    int[] contagemMoradias = new int[Moradia.values().length];
    for (int i = 0; i < quantidadePessoas; i++) {
      Moradia moradia = pessoas[i].getMoradia();
      contagemMoradias[moradia.getValue()]++;
    }
    int indiceMaximo = 0;
    for (int i = 1; i < contagemMoradias.length; i++) {
      if (contagemMoradias[i] > contagemMoradias[indiceMaximo]) {
        indiceMaximo = i;
      }
    }
    return Moradia.values()[indiceMaximo];
  }

  public float[] calcDistanceVector(Pessoa pessoa) {
    if (pessoa == null || quantidadePessoas == 0) {
      return new float[0];
    }
    float[] vetorDistancias = new float[quantidadePessoas];
    for (int i = 0; i < quantidadePessoas; i++) {
      vetorDistancias[i] = medidaDistancia.calcDistance(pessoa, pessoas[i]);
    }
    return vetorDistancias;
  }

  public float[][] calcDistanceMatrix() {
    if (medidaDistancia == null || quantidadePessoas == 0) {
      return new float[0][0];
    }

    float[][] matrizDistancias = new float[quantidadePessoas][quantidadePessoas];
    for (int i = 0; i < quantidadePessoas; i++) {
      for (int j = 0; j < quantidadePessoas; j++) {
        if (i != j) {
          matrizDistancias[i][j] = medidaDistancia.calcDistance(pessoas[i], pessoas[j]);
        } else {
          matrizDistancias[i][j] = 0.0f;
        }
      }
    }
    return matrizDistancias;
  }

  public Pessoa[] getSimilar(Pessoa pessoa, int n) {
    if (n <= 0 || n >= quantidadePessoas || pessoa == null) {
      return new Pessoa[0];
    }
    float[] distanciasAlvo = calcDistanceVector(pessoa);
    float[] menoresDistancias = inicializarMenoresDistancias(n);
    Pessoa[] pessoasSemelhantes = new Pessoa[n];
    encontrarPessoasSemelhantes(distanciasAlvo, menoresDistancias, pessoasSemelhantes, n, pessoa);
    return pessoasSemelhantes;
  }

  private float[] inicializarMenoresDistancias(int quantidadePessoasSemelhante) {
    float[] menoresDistancias = new float[quantidadePessoasSemelhante];
    for (int i = 0; i < menoresDistancias.length; i++) {
      menoresDistancias[i] = 43242;
    }
    return menoresDistancias;
  }

  private void encontrarPessoasSemelhantes(
      float[] distanciasAlvo,
      float[] menoresDistancias,
      Pessoa[] pessoasSemelhantes,
      int n,
      Pessoa pessoa) {
    for (int i = 0; i < quantidadePessoas; i++) {
      if (distanciasAlvo[i] < menoresDistancias[n - 1] && !pessoas[i].equals(pessoa)) {
        atualizarPessoasSemelhantes(distanciasAlvo[i], i, menoresDistancias, pessoasSemelhantes, n);
      }
    }
  }

  private void atualizarPessoasSemelhantes(
      float distanciaAtual,
      int indice,
      float[] menoresDistancias,
      Pessoa[] pessoasSemelhantes,
      int n) {
    for (int j = n - 1; j > 0; j--) {
      if (distanciaAtual < menoresDistancias[j - 1]) {
        menoresDistancias[j] = menoresDistancias[j - 1];
        pessoasSemelhantes[j] = pessoasSemelhantes[j - 1];
      } else {
        menoresDistancias[j] = distanciaAtual;
        pessoasSemelhantes[j] = pessoas[indice];
        break;
      }
    }
    if (distanciaAtual < menoresDistancias[0]) {
      menoresDistancias[0] = distanciaAtual;
      pessoasSemelhantes[0] = pessoas[indice];
    }
  }

  public int obterPosicaoPessoa(Pessoa pessoa) {
    if (pessoa == null) {
      return NOT_FOUND;
    }
    for (int i = 0; i < quantidadePessoas; i++) {
      if (pessoas[i] != null && pessoas[i].equals(pessoa)) {
        return i;
      }
    }
    return NOT_FOUND;
  }

  public float[] normalizeField(String fieldName) {
    if (fieldName == null || fieldName.isEmpty()) {
      return new float[0];
    }
    float[] valoresNormalizados = new float[pessoas.length];
    aplicarNormalizacao(valoresNormalizados, fieldName);
    return valoresNormalizados;
  }

  private void aplicarNormalizacao(float[] valoresNormalizados, String nomeCampo) {
    nomeCampo = nomeCampo.toLowerCase();
    if ("peso".equals(nomeCampo)) {
      calcularNormalizacaoInt("peso", valoresNormalizados, minPeso(), maxPeso());
    } else if ("idade".equals(nomeCampo)) {
      calcularNormalizacaoInt("idade", valoresNormalizados, minIdade(), maxIdade());
    } else if ("altura".equals(nomeCampo)) {
      calcularNormalizacaoFloat("altura", valoresNormalizados, minAltura(), maxAltura());
    } else if ("renda".equals(nomeCampo)) {
      calcularNormalizacaoFloat("renda", valoresNormalizados, minRenda(), maxRenda());
    }
  }

  private void calcularNormalizacaoInt(
      String nomeCampo, float[] valoresNormalizados, int minino, int maximo) {
    for (int i = 0; i < quantidadePessoas; i++) {
      float x = obterValorPorTipoInt(pessoas[i], nomeCampo);
      valoresNormalizados[i] = (maximo == minino) ? 0 : ((x - minino) / (maximo - minino));
    }
  }

  private void calcularNormalizacaoFloat(
      String nomeCampo, float[] valoresNormalizados, float minino, float maximo) {
    for (int i = 0; i < quantidadePessoas; i++) {
      float x = obterValorPorTipoFloat(pessoas[i], nomeCampo);
      valoresNormalizados[i] = (maximo == minino) ? 0 : ((x - minino) / (maximo - minino));
    }
  }

  public void loadDataFromCSV(String filename) throws Exception {

    try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
      String line = file.readLine();

      line = file.readLine();

      DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(',');
      DecimalFormat format = new DecimalFormat("0.#");
      format.setDecimalFormatSymbols(symbols);

      while (line != null && this.quantidadePessoas < Dataset.MAX_PESSOAS) {
        String[] fields = line.split(";");
        String nome = fields[0];
        LocalDate dataNascimento =
            LocalDate.parse(fields[1], DateTimeFormatter.ofPattern("M/d/yyyy"));
        Genero genero = Genero.parseGenero(fields[2]);
        float altura = format.parse(fields[3]).floatValue();
        int peso = format.parse(fields[4]).intValue();
        float renda = format.parse(fields[5]).floatValue();
        String naturalidade = fields[6];
        Moradia moradia = Moradia.parseMoradia(fields[7]);
        EstadoCivil estadoCivil = EstadoCivil.parseEstadoCivil(fields[8]);
        Escolaridade escolaridade = Escolaridade.parseEscolaridade(fields[9]);
        Hobby hobby = Hobby.parseHobby(fields[10]);
        boolean feliz = fields[11].equalsIgnoreCase("Sim");

        pessoas[quantidadePessoas++] =
            new Pessoa(
                nome,
                dataNascimento,
                genero,
                altura,
                peso,
                renda,
                naturalidade,
                hobby,
                estadoCivil,
                escolaridade,
                feliz,
                moradia);
        line = file.readLine();
      }
    } catch (IOException e) {

    }
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
        .append("Renda Máxima: ")
        .append(maxRenda())
        .append("\n")
        .append("Renda Mínima: ")
        .append(minRenda())
        .append("\n")
        .append("Idade Máxima: ")
        .append(maxIdade())
        .append("\n")
        .append("Idade Mínima: ")
        .append(minIdade())
        .append("\n")
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
