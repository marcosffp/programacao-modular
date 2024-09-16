package br.lpm;

public class Ave extends Animal {
  private EstadoAve estado;


  public EstadoAve getEstadoAve() {
    return estado;
  }

  public Ave(String nome, int idade, EstadoAve estado) {
    super(nome, idade);
    this.estado = estado;
  }

  public void setEstadoAve(EstadoAve estadoAve) {
    this.estado = estadoAve;
  }

  @Override
  public void fazerCheck() {
    System.out.println("Fazendo check na ave");
  }
}
