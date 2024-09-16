package br.lpm.business;

public class Transito {
  private RotaEntrega[] rotaEntregas;
  private Veiculo[] veiculos;

  public Transito(int capacidadeRotaEntregas, int capacidadeVeiculos) {
    this.rotaEntregas = new RotaEntrega[capacidadeRotaEntregas];
    this.veiculos = new Veiculo[capacidadeVeiculos];
  }

  public RotaEntrega[] getRotaEntregas() {
    RotaEntrega[] resultado = new RotaEntrega[rotaEntregas.length];
    for (int i = 0; i < rotaEntregas.length; i++) {
      resultado[i] = rotaEntregas[i];
    }
    return resultado;
  }

  public void setRotaEntregas(RotaEntrega[] rotaEntregas) {
    this.rotaEntregas = rotaEntregas;
  }

  public Veiculo[] getVeiculos() {
    Veiculo[] resultado = new Veiculo[veiculos.length];
    for (int i = 0; i < veiculos.length; i++) {
      resultado[i] = veiculos[i];
    }
    return resultado;
  }

  public void setVeiculos(Veiculo[] veiculos) {
    this.veiculos = veiculos;
  }
}
