package br.lpm.business;

import java.time.LocalDate;

public class Dataset {

  private int quantidadePessoa;
  private static final int MAX_PESSOAS = 100;
  private Pessoa[] pessoas = new Pessoa[MAX_PESSOAS];

  public Dataset() {
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
      if (pessoas[i].getNome().equals(nome)) {
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
      if (pessoas[i].getNome().equals(nome)) {
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
    if (quantidadePessoa == 0) {
      return 0;
    }
    float maior = pessoas[0].getAltura();
    for (int i = 0; i < quantidadePessoa; i++) {
      float valorAtual = pessoas[i].getAltura();
      if (valorAtual > maior) {
        maior = valorAtual;
      }
    }
    return maior;
  }

  public float minAltura() {
    if (quantidadePessoa == 0) {
      return 0;
    }
    float menor = pessoas[0].getAltura();
    for (int i = 0; i < quantidadePessoa; i++) {
      float valorAtual = pessoas[i].getAltura();
      if (valorAtual < menor) {
        menor = valorAtual;
      }
    }
    return menor;
  }
  public float avgAltura() {
    if (quantidadePessoa == 0) {
      return 0;
    }
    float soma = 0.0f;
    int quantidade = quantidadePessoa;
    for (int i = 0; i < quantidadePessoa; i++) {
      soma += pessoas[i].getAltura();
    }
    return soma / quantidade;
  }

  public int  maxPeso() {
    if (quantidadePessoa == 0) {
      return 0;
    }
    int maior = pessoas[0].getPeso();
    for (int i = 0; i < quantidadePessoa; i++) {
      int valorAtual = pessoas[i].getPeso();
      if (valorAtual > maior) {
        maior = valorAtual;
      }
    }
    return maior;
  }

  public int minPeso() {
    if (quantidadePessoa == 0) {
      return 0;
    }
    int menor = pessoas[0].getPeso();
    for (int i = 0; i < quantidadePessoa; i++) {
      int valorAtual = pessoas[i].getPeso();
      if (valorAtual < menor) {
        menor = valorAtual;
      }
    }
    return menor;
  }

  public float avgPeso() {
    if (quantidadePessoa == 0) {
      return 0;
    }
    float soma = 0.0f;
    int quantidade = quantidadePessoa;
    for (int i = 0; i < quantidadePessoa; i++) {
      soma += pessoas[i].getPeso();
    }
    return soma / quantidade;
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
    int maxquantidadePessoa = casado;
    EstadoCivil mode = EstadoCivil.CASADO;
    if (divorciado > maxquantidadePessoa) {
      maxquantidadePessoa = divorciado;
      mode = EstadoCivil.DIVORCIADO;
    }
    if (separado > maxquantidadePessoa) {
      maxquantidadePessoa = separado;
      mode = EstadoCivil.SEPARADO;
    }
    if (solteiro > maxquantidadePessoa) {
      maxquantidadePessoa = solteiro;
      mode = EstadoCivil.SOLTEIRO;
    }
    if (viuvo > maxquantidadePessoa) {
      maxquantidadePessoa = viuvo;
      mode = EstadoCivil.VIUVO;
    }

    return mode;
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
    int maxquantidadePessoa = nenhuma;
    Escolaridade mode = Escolaridade.NENHUMA;
    if (fundamental > maxquantidadePessoa) {
      maxquantidadePessoa = fundamental;
      mode = Escolaridade.FUNDAMENTAL;
    }
    if (medio > maxquantidadePessoa) {
      maxquantidadePessoa = medio;
      mode = Escolaridade.MEDIO;
    }
    if (superior > maxquantidadePessoa) {
      maxquantidadePessoa = superior;
      mode = Escolaridade.SUPERIOR;
    }
    if (posGraduacao > maxquantidadePessoa) {
      maxquantidadePessoa = posGraduacao;
      mode = Escolaridade.POS_GRADUACAO;
    }
    return mode;
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
    int maxquantidadePessoa = comFamilia;
    Moradia mode = Moradia.COM_FAMILIA;
    if (aluguel > maxquantidadePessoa) {
      maxquantidadePessoa = aluguel;
      mode = Moradia.ALUGUEL;
    }
    if (casaPropria > maxquantidadePessoa) {
      maxquantidadePessoa = casaPropria;
      mode = Moradia.CASA_PROPRIA;
    }
    return mode;
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
}
