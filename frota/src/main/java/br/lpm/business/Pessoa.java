package br.lpm.business;

public class Pessoa {
  private String nome;
  private int id;
  private static int cont = 0;

  public Pessoa(String nome, int id) {
    this.nome = nome;
    this.id = id;
    cont++;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public static int getCont() {
    return cont;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Nome: ").append(nome).append("\n");
    sb.append("ID: ").append(id).append("\n");
    sb.append("Contador Total de Pessoas: ").append(cont).append("\n");

    return sb.toString();
  }
}
