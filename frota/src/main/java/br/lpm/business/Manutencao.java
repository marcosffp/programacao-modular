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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Manutenção Detalhes:\n");
    sb.append("***********************************\n");

    sb.append("Data da Manutenção: ").append(previsao).append("\n");
    sb.append("Veículo: ")
        .append(veiculo != null ? veiculo: "Nenhum veículo associado")
        .append("\n");
    sb.append("Oficina: ")
        .append(oficina != null ? oficina: "Nenhuma oficina associada")
        .append("\n");

    sb.append("***********************************\n");
    return sb.toString();
  }
}
