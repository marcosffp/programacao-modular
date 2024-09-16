package br.lpm.business;

import java.time.LocalDate;

public class Manutencao {
  private LocalDate previsao;
  private Veiculo veiculo;
  private Oficina oficina;

  public Manutencao(LocalDate previsao) {
    this.previsao = previsao;
  }

  public LocalDate getPrevisao() {
    return previsao;
  }

  public void setPrevisao(LocalDate previsao) {
    this.previsao = previsao;
  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }

  public Oficina getOficina() {
    return oficina;
  }

  public void setOficina(Oficina oficina) {
    this.oficina = oficina;
  }

}
