package br.lpm.business;

import java.time.LocalDate;

public class Manutencao {
  private LocalDate dataTermino;
  private Veiculo veiculo;

  public Manutencao(LocalDate dataTermino, Veiculo veiculo) {
    this.dataTermino = dataTermino;
    this.veiculo = veiculo;
  }

  public LocalDate getDataTermino() {
    return dataTermino;
  }

  public void setDataTermino(LocalDate dataTermino) {
    this.dataTermino = dataTermino;
  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }
}
