package br.lpm.business;

import java.time.LocalDate;

public class Dataset {
  private int count;
  private static final int MAX_PESSOAS = 100;
  private Pessoa[] pessoas = new Pessoa[MAX_PESSOAS];

  public Pessoa[] getPessoas() {
    return pessoas;
  }

  public Dataset() {
    this.count = 0;
  }

  public void addPessoa(Pessoa pessoa) {
    if (pessoa==null) {
      return;
    }
    if (count >= MAX_PESSOAS) {
      return;
    }
    pessoas[count++] = pessoa;
  }

  public void executarRemocao(int i) {
    for (int j = i; j < count - 1; j++) {
      pessoas[j] = pessoas[j + 1];
    }
    pessoas[--count] = null;
  }

  public void removerPessoa(Pessoa pessoa) {
    if (pessoa == null) {
      return;
    }
    for (int i = 0; i < count; i++) {
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
    for (int i = 0; i < count; i++) {
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
    for (int i = 0; i < count; i++) {
      if (pessoas[i].equals(velha)) {
        pessoas[i] = nova;
        break;
      }
    }
  }

  public Pessoa getPessoaByName(String nome) {
    for (int i = 0; i < count; i++) {
      if (pessoas[i].getNome().equals(nome)) {
        return pessoas[i];
      }
    }
    return null;
  }

  public Pessoa[] getAll() {
    return pessoas;
  }

  public void removeAll() {
    for (int i = 0; i < count; i++) {
      pessoas[i] = null;
    }
    count = 0;
  }

  public int size() {
    return count;
  }

  public float maxAltura() {
    if (count == 0) {
      return 0;
    }
    float maior = pessoas[0].getAltura();
    for (int i = 0; i < count; i++) {
      if (pessoas[i].getAltura() > maior) {
        maior = pessoas[i].getAltura();
      }
    }
    return maior;
  }

  public float minAltura() {
    if (count == 0) {
      return 0;
    }
    float menor = pessoas[0].getAltura();
    for (int i = 0; i < count; i++) {
      if (pessoas[i].getAltura() < menor) {
        menor = pessoas[i].getAltura();
      }
    }
    return menor;
  }

  public float avgAltura() {
    if (count == 0) {
      return 0;
    }
    float soma = 0.0f;
    int quantidade = count;
    for (int i = 0; i < count; i++) {
      soma += pessoas[i].getAltura();
    }
    return soma / (float) quantidade;
  }

  public float maxPeso() {
    if (count == 0) {
      return 0;
    }
    float maior = pessoas[0].getPeso();
    for (int i = 0; i < count; i++) {
      if (pessoas[i].getPeso() > maior) {
        maior = pessoas[i].getPeso();
      }
    }
    return maior;
  }

  public float minPeso() {
    if (count == 0) {
      return 0;
    }
    float menor = pessoas[0].getPeso();
    for (int i = 0; i < count; i++) {
      if (pessoas[i].getPeso() < menor) {
        menor = pessoas[i].getPeso();
      }
    }
    return menor;
  }

  public float avgPeso() {
    if (count == 0) {
      return 0;
    }
    float soma = 0.0f;
    int quantidade = count;
    for (int i = 0; i < count; i++) {
      soma += pessoas[i].getPeso();
    }
    return soma / (float) quantidade;
  }

  public int calcularIdade(Pessoa pessoa) {
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

  public float percentAdult() {
    if (count == 0) {
      return 0;
    }
    int quantidadeAdultos = 0;
    for (int i = 0; i < count; i++) {
      if (calcularIdade(pessoas[i]) >= 18) {
        quantidadeAdultos++;
      }
    }
    float porcentagemAdulto = (float) (quantidadeAdultos / count)*100;
    return porcentagemAdulto;
  }

  public float percentEstadoCivil(EstadoCivil estadoCivil) {
    if (count == 0) {
      return 0;
    }
    int estadoCivilDeterminado = 0;

    for (int i = 0; i < count; i++) {
      if (pessoas[i].getEstadoCivil().equals(estadoCivil)) {
        estadoCivilDeterminado++;
      }
    }

    float porcentagemEstadoCivil = ((float) estadoCivilDeterminado / count) * 100;
    return porcentagemEstadoCivil;
  }

  public EstadoCivil modeEstadoCivil() {
    int casado = 0;
    int divorciado = 0;
    int separado = 0;
    int solteiro = 0;
    int viuvo = 0;

    for (int i = 0; i < count; i++) {
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
    int maxCount = casado;
    EstadoCivil mode = EstadoCivil.CASADO;

    if (divorciado > maxCount) {
      maxCount = divorciado;
      mode = EstadoCivil.DIVORCIADO;
    }
    if (separado > maxCount) {
      maxCount = separado;
      mode = EstadoCivil.SEPARADO;
    }
    if (solteiro > maxCount) {
      maxCount = solteiro;
      mode = EstadoCivil.SOLTEIRO;
    }
    if (viuvo > maxCount) {
      maxCount = viuvo;
      mode = EstadoCivil.VIUVO;
    }

    return mode;
  }

  public float percentEscolaridade(Escolaridade escolaridade) {
    if (count == 0) {
      return 0;
    }
    int escolaridadeDeterminado = 0;

    for (int i = 0; i < count; i++) {
      if (pessoas[i].getEscolaridade().equals(escolaridade)) {
        escolaridadeDeterminado++;
      }
    }

    float porcentagemEscolaridade = ((float) escolaridadeDeterminado / count) * 100;
    return porcentagemEscolaridade;
  }

  public Escolaridade modeEscolaridade() {
    int nenhuma = 0;
    int fundamental = 0;
    int medio = 0;
    int superior = 0;
    int posGraduacao = 0;

    for (int i = 0; i < count; i++) {
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

    int maxCount = nenhuma;
    Escolaridade mode = Escolaridade.NENHUMA;

    if (fundamental > maxCount) {
      maxCount = fundamental;
      mode = Escolaridade.FUNDAMENTAL;
    }
    if (medio > maxCount) {
      maxCount = medio;
      mode = Escolaridade.MEDIO;
    }
    if (superior > maxCount) {
      maxCount = superior;
      mode = Escolaridade.SUPERIOR;
    }
    if (posGraduacao > maxCount) {
      maxCount = posGraduacao;
      mode = Escolaridade.POS_GRADUACAO;
    }

    return mode;
  }

  public float percentMoradia(Moradia moradia) {
    if (count == 0) {
      return 0;
    }
    int moradiaDeterminado = 0;

    for (int i = 0; i < count; i++) {
      if (pessoas[i].getMoradia().equals(moradia)) {
        moradiaDeterminado++;
      }
    }

    float porcentagemMoradia = ((float) moradiaDeterminado / count) * 100;
    return porcentagemMoradia;
  }

  public Moradia modeMoradia() {
    int comFamilia = 0;
    int aluguel = 0;
    int casaPropria = 0;

    for (int i = 0; i < count; i++) {
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

    int maxCount = comFamilia;
    Moradia mode = Moradia.COM_FAMILIA;

    if (aluguel > maxCount) {
      maxCount = aluguel;
      mode = Moradia.ALUGUEL;
    }
    if (casaPropria > maxCount) {
      maxCount = casaPropria;
      mode = Moradia.CASA_PROPRIA;
    }

    return mode;
  }

  public float percentHobby() {
    if (count == 0) {
      return 0;
    }
    int hobby = 0;

    for (int i = 0; i < count; i++) {
      if (!(pessoas[i].getHobby().equals(Hobby.NENHUM))) {
        hobby++;
      }
    }
    float porcentagemHobby = ((float) hobby / count) * 100;
    return porcentagemHobby;
  }

  public float percentFeliz() {
    if (count == 0) {
      return 0;
    }
    int felizCount = 0;

    for (int i = 0; i < count; i++) {
      if (pessoas[i].isFeliz()) {
        felizCount++;
      }
    }

    float porcentagemFeliz = ((float) felizCount / count) * 100;
    return porcentagemFeliz;
  }
}
