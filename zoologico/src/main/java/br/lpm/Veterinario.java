package br.lpm;

public class Veterinario {
  private String nome;
  private String especialidade;
  public Veterinario(String nome, String especialidade) {
    this.nome = nome;
    this.especialidade = especialidade;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getEspecialidade() {
    return especialidade;
  }

  public void setEspecialidade(String especialidade) {
    this.especialidade = especialidade;
  }
  
  public void realizarCheckUp(Animal animal) {
    animal.fazerCheck();
  }
}
