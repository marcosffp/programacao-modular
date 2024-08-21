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

  public void setNome(String nome) {
    if (nome == null || nome.isEmpty()) {
      System.out.println("Nome nulo ou vazio");
      return;
    }

    char[] nomeChar = nome.toCharArray();
    for (int i = 0; i < nomeChar.length; i++) {
      if (!((nomeChar[i] >= 'a' && nomeChar[i] <= 'z')
          || (nomeChar[i] >= 'A' && nomeChar[i] <= 'Z')
          || nomeChar[i] == ' ')) {
        System.out.println("Nome com símbolo ou número");
        return;
      }
    }

    this.nome = nome;
  }
  public static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    if (dataNascimento.isBefore(LocalDate.now())) {
      this.dataNascimento = dataNascimento;
    }
    return;
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

  public void setAltura(float altura) {
    if ((altura > 0) && (altura < 2.60)) {
      this.altura = altura;
    }
    return;
  }

  public int getPeso() {
    return peso;
  }

  public void setPeso(int peso) {
    if ((peso > 0) && (peso < 600)) {
      this.peso = peso;
    }
    return;
  }

  public float getRenda() {
    return renda;
  }

  public void setRenda(float renda) {
    if (renda >= 0) {
      this.renda = renda;
    }
    return;
  }

  public String getNaturalidade() {
    return naturalidade;
  }

  public void setNaturalidade(String naturalidade) {
    this.naturalidade = naturalidade;
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
    StringBuilder sb = new StringBuilder();
    sb.append("=============================================\n");
    sb.append("Nome: ").append(nome).append("\n");
    sb.append("Data de nascimento: ").append(formatadorData.format(dataNascimento)).append("\n");
    sb.append("Gênero: ").append(genero).append("\n");
    sb.append("Altura: ")
        .append(altura)
        .append(" m")
        .append("  |  Peso: ")
        .append(peso)
        .append(" kg\n");
    sb.append("Renda: R$ ")
        .append(String.format("%.2f", renda))
        .append("  |  Naturalidade: ")
        .append(naturalidade)
        .append("\n");
    sb.append("Hobby: ").append(hobby).append("\n");
    sb.append("Estado civil: ")
        .append(estadoCivil)
        .append("  |  Escolaridade: ")
        .append(escolaridade)
        .append("\n");
    sb.append("Feliz: ")
        .append(feliz ? "Sim" : "Não")
        .append("  |  Moradia: ")
        .append(moradia)
        .append("\n");
    sb.append("=============================================\n");
    return sb.toString();
  }
}
