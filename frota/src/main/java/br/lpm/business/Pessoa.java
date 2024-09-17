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
}
