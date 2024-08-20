package business;

import java.time.LocalDate;

public class Pessoa {
  private String nome;
  private float altura;
  private float peso;
  private LocalDate dataNascimento;
  private EstadoCivil estadoCivil;
  private FormacaoAcademica formacaoAcademica;
  private Profissao profissao;
  private boolean vidaSocial;
  private boolean hobby;
  private int atividadeFisica;
  private int saude;

  public Pessoa() {}

  public Pessoa(
      String nome,
      float altura,
      float peso,
      LocalDate dataNascimento,
      EstadoCivil estadoCivil,
      FormacaoAcademica formacaoAcademica,
      Profissao profissao,
      boolean vidaSocial,
      boolean hobby,
      int atividadeFisica,
      int saude) {

    this.setNome(nome);
    this.setAltura(altura);
    this.setPeso(peso);
    this.setDataNascimento(dataNascimento);
    this.setEstadoCivil(estadoCivil);
    this.setFormacaoAcademica(formacaoAcademica);
    this.setProfissao(profissao);
    this.setVidaSocial(vidaSocial);
    this.setHobby(hobby);
    this.setAtividadeFisica(atividadeFisica);
    this.setSaude(saude);
  }

  public void setNome(String nome) {
    if (nome != null
        && nome.chars()
            .allMatch(n -> (n >= 'a' && n <= 'z') || (n >= 'A' && n <= 'Z') || (n == ' '))
        && !nome.isEmpty()) {
      this.nome = nome;
    } else {
      throw new IllegalArgumentException(
          "Nome inválido. O nome deve conter apenas letras e espaço");
    }
  }

  public String getNome() {
    return nome;
  }

  public void setAltura(float altura) {
    if ((altura > 0) && (altura < 3)) {
      this.altura = altura;
    } else {
      throw new IllegalArgumentException(
          "Altura inválida. A altura deve ser maior que 0 e menor que 3 metros");
    }
  }

  public float getAltura() {
    return altura;
  }

  public void setPeso(float peso) {
    if ((peso > 0) && (peso < 500)) {
      this.peso = peso;
    } else {
      throw new IllegalArgumentException(
          "Peso inválido. O peso deve ser maior que 0 e menor que 500 quilos");
    }
  }

  public float getPeso() {
    return peso;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    if (dataNascimento.isBefore(LocalDate.now())) {
      this.dataNascimento = dataNascimento;
    } else {
      throw new IllegalArgumentException(
          "Data de nascimento inválido. A data deve ser menor que a data atual");
    }
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setEstadoCivil(EstadoCivil estadoCivil) {
    if (estadoCivil != null) {
      this.estadoCivil = estadoCivil;
    } else {
      throw new IllegalArgumentException("Estado civil nulo");
    }
  }

  public EstadoCivil getEstadoCivil() {
    return estadoCivil;
  }

  public void setFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
    if (formacaoAcademica != null) {
      this.formacaoAcademica = formacaoAcademica;
    } else {
      throw new IllegalArgumentException("Formação acadêmica nulo");
    }
  }

  public FormacaoAcademica getFormacaoAcademica() {
    return formacaoAcademica;
  }

  public void setProfissao(Profissao profissao) {
    if (profissao != null) {
      this.profissao = profissao;
    } else {
      throw new IllegalArgumentException("Profissão nulo");
    }
  }

  public Profissao getProfissao() {
    return profissao;
  }

  public void setVidaSocial(boolean vidaSocial) {
    this.vidaSocial = vidaSocial;
  }

  public boolean isVidaSocial() {
    return vidaSocial;
  }

  public void setHobby(boolean hobby) {
    this.hobby = hobby;
  }

  public boolean isHobby() {
    return hobby;
  }

  public void setAtividadeFisica(int atividadeFisica) {
    if ((atividadeFisica >= 0) && (atividadeFisica <= 7)) {
      this.atividadeFisica = atividadeFisica;
    } else {
      throw new IllegalArgumentException(
          "Atividade Física inválido. O valor da atividade Física deve ser maior ou igual a 0 e"
              + " menor ou igual a 7 dias");
    }
  }

  public int getAtividadeFisica() {
    return atividadeFisica;
  }

  public void setSaude(int saude) {
    if ((saude >= 1) && (saude <= 10)) {
      this.saude = saude;
    } else {
      throw new IllegalArgumentException(
          "Valor de escala inválida. O valor deve que representa o estado de saúde deve ser em uma"
              + " escala entre 1 e 10");
    }
  }

  public int getSaude() {
    return saude;
  }

  @Override
  public String toString() {
    return "Nome: "
        + nome
        + "\n"
        + "Altura: "
        + String.format("%.2f", altura)
        + " m\n"
        + "Peso: "
        + String.format("%.2f", peso)
        + " kg\n"
        + "Data de nascimento: "
        + dataNascimento
        + "\n"
        + "Estado civil: "
        + estadoCivil
        + "\n"
        + "Formação acadêmica: "
        + formacaoAcademica
        + "\n"
        + "Profissão: "
        + profissao
        + "\n"
        + "Vida social: "
        + vidaSocial
        + "\n"
        + "Hobby: "
        + hobby
        + "\n"
        + "Atividade física: "
        + atividadeFisica
        + "\n"
        + "Saúde: "
        + saude;
  }
}
