package br.lpm.business;

import java.time.LocalDate;

public class Rota {
  private String origem;
  private String destino;
  private Veiculo veiculo;
  private Motorista motorista;
  private LocalDate data;
  private Frota frota;

  public Frota getFrota() {
    return frota;
  }

  public void setFrota(Frota frota) {
    this.frota = frota;
  }

  public String getOrigem() {
    return origem;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }

  public void setMotorista(Motorista motorista) {
    this.motorista = motorista;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
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

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public Motorista getMotorista() {
    return motorista;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Detalhes da Rota:\n");
    sb.append("***********************************\n");

    sb.append("Origem: ").append(origem != null ? origem : "Não definida").append("\n");
    sb.append("Destino: ").append(destino != null ? destino : "Não definido").append("\n");
    sb.append("Data: ").append(data != null ? data : "Não definida").append("\n");
    sb.append("Veículo: ")
        .append(veiculo != null ? veiculo : "Nenhum veículo associado")
        .append("\n");
    sb.append("Motorista: ")
        .append(motorista != null ? motorista : "Nenhum motorista associado")
        .append("\n");
    sb.append("Frota: ").append(frota != null ? frota : "Nenhuma frota associada").append("\n");

    sb.append("***********************************\n");
    return sb.toString();
  }
}
