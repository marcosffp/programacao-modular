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
    ManipuladorDeArrays.adicionar(manutencao, manutencoes, numManutencoes, VALOR_MAXIMO);
  }

  public void addMotorista(Motorista motorista) {
    ManipuladorDeArrays.adicionar(motorista, motoristas, numMotoristas, VALOR_MAXIMO);
  }

  public void removeManutencao(Manutencao manutencao) {
    ManipuladorDeArrays.remover(manutencao, manutencoes, numManutencoes, VALOR_MAXIMO);
  }

  public void removeMotorista(Motorista motorista) {
    ManipuladorDeArrays.remover(motorista, motoristas, numMotoristas, VALOR_MAXIMO);
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
}
