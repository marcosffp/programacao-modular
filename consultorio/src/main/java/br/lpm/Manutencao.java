package br.lpm;

import java.time.LocalDate;

public class Manutencao {
  private LocalDate dataPrevista;
  private Veiculo veiculo;
  private Oficina oficina;
  public Manutencao(LocalDate dataPrevista, Veiculo veiculo, Oficina oficina) {
    this.dataPrevista = dataPrevista;
    this.veiculo = veiculo;
    this.oficina = oficina;
    this.veiculo.setStatus(Status.EM_MANUTENCAO);
  }
  public LocalDate getDataPrevista() {
    return dataPrevista;
  }
  public void setDataPrevista(LocalDate dataPrevista) {
    this.dataPrevista = dataPrevista;
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
