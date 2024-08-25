package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
  private String nome;
  private LocalDate dataNascimento;
  private Genero genero;
  private float altura;
  private int peso;
  private float renda;
  private String naturalidade;
  private Hobby hobby;
  private EstadoCivil estadoCivil;
  private Escolaridade escolaridade;
  private boolean feliz;
  private Moradia moradia;

  public Pessoa() {}

  public Pessoa(
      String nome,
      LocalDate dataNascimento,
      Genero genero,
      float altura,
      int peso,
      float renda,
      String naturalidade,
      Hobby hobby,
      EstadoCivil estadoCivil,
      Escolaridade escolaridade,
      boolean feliz,
      Moradia moradia) {

    this.setNome(nome);
    this.setDataNascimento(dataNascimento);
    this.setGenero(genero);
    this.setAltura(altura);
    this.setPeso(peso);
    this.setRenda(renda);
    this.setNaturalidade(naturalidade);
    this.setHobby(hobby);
    this.setEstadoCivil(estadoCivil);
    this.setEscolaridade(escolaridade);
    this.setFeliz(feliz);
    this.setMoradia(moradia);
  }

  public String getNome() {
    return nome;
  }

  public boolean isStringValido(String nome) {
    if (nome == null || nome.isEmpty()) {
      System.out.println("Nome nulo ou vazio");
      return false;
    }
    char[] nomeChar = nome.toCharArray();
    for (int i = 0; i < nomeChar.length; i++) {
      if (!(Character.isLetter(nomeChar[i]) || nomeChar[i] == ' ')) {
        System.out.println("Nome com símbolo ou número");
        return false;
      }
    }
    return true;
  }

  public void setNome(String nome) {
    if (isStringValido(nome)) {
      this.nome = nome;
    }
  }

  public static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public boolean isDataNascimentoValido(LocalDate dataNascimento) {
    return dataNascimento.isBefore(LocalDate.now());
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    if (isDataNascimentoValido(dataNascimento)) {
      this.dataNascimento = dataNascimento;
    }
  }

  public Genero getGenero() {
    return genero;
  }

  public void setGenero(Genero genero) {
    this.genero = genero;
  }

  public float getAltura() {
    return altura;
  }

  public boolean isAlturaValida(float altura) {
    return altura > 0 && altura < 2.60f;
  }

  public void setAltura(float altura) {
    if (isAlturaValida(altura)) {
      this.altura = altura;
    }
  }

  public int getPeso() {
    return peso;
  }

  public boolean isPesoValido(int peso) {
    return peso > 0 && peso < 600;
  }

  public void setPeso(int peso) {
    if (isPesoValido(peso)) {
      this.peso = peso;
    }
  }

  public float getRenda() {
    return renda;
  }

  public boolean isRendaValida(float renda) {
    return renda >= 0;
  }

  public void setRenda(float renda) {
    if (isRendaValida(renda)) {
      this.renda = renda;
    }
  }

  public String getNaturalidade() {
    return naturalidade;
  }

  public void setNaturalidade(String naturalidade) {
    if (isStringValido(naturalidade)) {
      this.naturalidade = naturalidade;
    }
  }

  public Hobby getHobby() {
    return hobby;
  }

  public void setHobby(Hobby hobby) {
    this.hobby = hobby;
  }

  public EstadoCivil getEstadoCivil() {
    return estadoCivil;
  }

  public void setEstadoCivil(EstadoCivil estadoCivil) {
    this.estadoCivil = estadoCivil;
  }

  public Escolaridade getEscolaridade() {
    return escolaridade;
  }

  public void setEscolaridade(Escolaridade escolaridade) {
    this.escolaridade = escolaridade;
  }

  public boolean isFeliz() {
    return feliz;
  }

  public void setFeliz(boolean feliz) {
    this.feliz = feliz;
  }

  public Moradia getMoradia() {
    return moradia;
  }

  public void setMoradia(Moradia moradia) {
    this.moradia = moradia;
  }

  @Override
  public String toString() {

    return "=============================================\n" + "Nome: " + nome != null
        ? nome
        : "Não informado"
                    + "\n"
                    + "Data de nascimento: "
                    + (dataNascimento != null
                        ? formatadorData.format(dataNascimento)
                        : "Não informada")
                    + "\n"
                    + "Gênero: "
                    + genero
                    + "\n"
                    + "Altura: "
                    + altura
                    + " m  |  Peso: "
                    + peso
                    + " kg\n"
                    + "Renda: R$ "
                    + String.format("%.2f", renda)
                    + "  |  Naturalidade: "
                    + naturalidade
                != null
            ? naturalidade
            : "Não informada"
                + "\n"
                + "Hobby: "
                + hobby
                + "\n"
                + "Estado civil: "
                + estadoCivil
                + "  |  Escolaridade: "
                + escolaridade
                + "\n"
                + "Feliz: "
                + (feliz ? "Sim" : "Não")
                + "  |  Moradia: "
                + moradia
                + "\n"
                + "=============================================\n";
  }
}
