package br.lpm;

import java.time.LocalDate;

public class RotaEntrega {
  private String origem;
  private String destino;
  private LocalDate data;
  private Veiculo veiculo;
  private Motorista motorista;
  public RotaEntrega(String origem, String destino, LocalDate data, Veiculo veiculo, Motorista motorista) {
    this.origem = origem;
    this.destino = destino;
    this.data = data;
    this.veiculo = veiculo;
    this.motorista = motorista;
    this.veiculo.setStatus(Status.EM_TRANSITO);
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
  public LocalDate getData() {
    return data;
  }
  public void setData(LocalDate data) {
    this.data = data;
  }
  public Veiculo getVeiculo() {
    return veiculo;
  }
  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }
  public Motorista getMotorista() {
    return motorista;
  }
  public void setMotorista(Motorista motorista) {
    this.motorista = motorista;
  }
}
