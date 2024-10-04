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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Detalhes do Mecânico:\n");
    sb.append("***********************************\n");

    sb.append(super.toString());

    sb.append("\n");
    sb.append("Oficina: ")
        .append(oficina != null ? oficina : "Nenhuma oficina associada")
        .append("\n");

    sb.append("***********************************\n");
    return sb.toString();
  }
}
