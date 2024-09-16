package br.lpm.business;

import java.time.LocalDate;

public class RotaEntrega {
  private String origem;
  private String destino;
  private LocalDate dataTransporte;
  private Motorista motorista;
  private Veiculo veiculo;

  public RotaEntrega(
      String origem,
      String destino,
      LocalDate dataTransporte,
      Motorista motorista,
      Veiculo veiculo) {
    this.origem = origem;
    this.destino = destino;
    this.dataTransporte = dataTransporte;
    this.motorista = motorista;
    this.veiculo = veiculo;
  }

  public String getOrigem() {
    return origem;
  }

  public void setOrigem(String origem) {
    this.origem = origem;
  }

  public String getDestino() {
    return destino;
  }

  public void setDestino(String destino) {
    this.destino = destino;
  }

  public LocalDate getDataTransporte() {
    return dataTransporte;
  }

  public void setDataTransporte(LocalDate dataTransporte) {
    this.dataTransporte = dataTransporte;
  }

  public Motorista getMotorista() {
    return motorista;
  }

  public void setMotorista(Motorista motorista) {
    this.motorista = motorista;
  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }
}
