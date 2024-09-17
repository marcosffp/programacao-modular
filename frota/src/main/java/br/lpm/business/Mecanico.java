package br.lpm.business;

public class Mecanico extends Pessoa {
  private Oficina oficina;

  public Mecanico(String nome, int id, Oficina oficina) {
        super(nome, id);
      this.oficina = oficina;
      this.oficina.addMecanico(this);

  }

  public Oficina getOficina() {
    return oficina;
  }

  public void setOficina(Oficina oficina) {
    this.oficina = oficina;
  }
}
