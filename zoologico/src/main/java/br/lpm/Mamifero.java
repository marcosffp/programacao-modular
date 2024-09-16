package br.lpm;

public final class Mamifero extends Animal {
  private EstadoMamifero estado;

  public Mamifero(String nome, int idade, EstadoMamifero estado) {
    super(nome, idade);
    this.estado = estado;
  }

  public EstadoMamifero getEstado() {
    return estado;
  }

  public void setEstado(EstadoMamifero estado) {
    this.estado = estado;
  }

  @Override
  public void fazerCheck() {
    System.out.println("Fazendo check animal mamifero");
  }
}
