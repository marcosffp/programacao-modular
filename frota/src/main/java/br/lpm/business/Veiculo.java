package br.lpm.business;

import java.time.LocalDate;

public class Veiculo {
  private String modelo;
  private int ano;
  private String placa;
  private int km;
  private Estado estado;
  private static final int VALOR_MAXIMO = 1000;
  private int numManutencoes = 0;
  private int numMotoristas = 0;
  private Manutencao[] manutencoes = new Manutencao[VALOR_MAXIMO];
  private Motorista[] motoristas = new Motorista[VALOR_MAXIMO];

  public Veiculo(String modelo, int ano, String placa, int km, Estado estado) {
    this.modelo = modelo;
    this.ano = ano;
    this.placa = placa;
    this.km = km;
    this.estado = estado;
  }

  public static int getValorMaximo() {
    return VALOR_MAXIMO;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public int getKm() {
    return km;
  }

  public void setKm(int km) {
    this.km = km;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  public void addManutencao(Manutencao manutencao) {
    numManutencoes=ManipuladorDeArrays.adicionar(manutencao, manutencoes, numManutencoes, VALOR_MAXIMO);
  }

  public void addMotorista(Motorista motorista) {
    numMotoristas=ManipuladorDeArrays.adicionar(motorista, motoristas, numMotoristas, VALOR_MAXIMO);
  }

  public void removeManutencao(Manutencao manutencao) {
    numManutencoes=ManipuladorDeArrays.remover(manutencao, manutencoes, numManutencoes, VALOR_MAXIMO);
  }

  public void removeMotorista(Motorista motorista) {
    numMotoristas=ManipuladorDeArrays.remover(motorista, motoristas, numMotoristas, VALOR_MAXIMO);
  }

  public Manutencao getManutencaoByDate(LocalDate previsao) {
    for (Manutencao manutencao : manutencoes) {
      if (manutencao.getPrevisao().equals(previsao)) {
        return manutencao;
      }
    }
    return null;
  }

  public Motorista getMotoristaById(int id) {
    for (Motorista motorista : motoristas) {
      if (motorista.getId() == id) {
        return motorista;
      }
    }
    return null;
  }

  public Manutencao[] getAllManutencoes() {
    return manutencoes;
  }

  public Motorista[] getAllMotoristas() {
    return motoristas;
  }

  public void replaceManutencao(Manutencao velho, Manutencao novo) {
    ManipuladorDeArrays.replace(velho, novo, manutencoes);
  }

  public void replaceMotorista(Motorista velho, Motorista novo) {
    ManipuladorDeArrays.replace(velho, novo, motoristas);
  }

  public int getNumManutencoes() {
    return numManutencoes;
  }

  public int getNumMotoristas() {
    return numMotoristas;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Detalhes do Veículo:\n");
    sb.append("***********************************\n");

    sb.append("Modelo: ").append(modelo).append("\n");
    sb.append("Ano: ").append(ano).append("\n");
    sb.append("Placa: ").append(placa).append("\n");
    sb.append("Quilometragem: ").append(km).append(" km\n");
    sb.append("Estado: ").append(estado != null ? estado : "Não definido").append("\n");

    sb.append("Número de Manutenções: ").append(numManutencoes).append("\n");
    sb.append("Manutenções:\n");
    if (numManutencoes > 0) {
      for (int i = 0; i < numManutencoes; i++) {
        sb.append(manutencoes[i]).append("\n");
      }
    } else {
      sb.append("Nenhuma manutenção registrada.\n");
    }

    sb.append("Número de Motoristas: ").append(numMotoristas).append("\n");
    sb.append("Motoristas:\n");
    if (numMotoristas > 0) {
      for (int i = 0; i < numMotoristas; i++) {
        sb.append(motoristas[i]).append("\n");
      }
    } else {
      sb.append("Nenhum motorista registrado.\n");
    }

    sb.append("***********************************\n");
    return sb.toString();
  }
}
