package demo;

public class Pessoa {
  private String nome;
  private int idade;
  private String cpf;
  private String sexo;

  public Pessoa() {

  }
  
  public Pessoa(String nome, int idade, String cpf, String sexo) {
    this.nome = nome;
    this.idade = idade;
    this.cpf = cpf;
    this.sexo = sexo;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getNome() {
    return nome;
  }

  public int getIdade() {
    return idade;
  }

  public String getCpf() {
    return cpf;
  }

  public String getSexo() {
    return sexo;
  }

  public boolean isMaiorIdade() {
    return idade >= 18;
  }

  
}
