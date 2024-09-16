package br.lpm.business;


public class Veiculo {
  private String modelo;
  private int ano;
  private String placa;
  private int km;
  private Estado estado;
  private static final int MAX_MANUTENCOES = 1000;
  private static final int MAX_MOTORISTAS = 1000;
  private int numManutencoes = 0;
  private int numMotoristas = 0;
  private Manutencao[] manutencoes = new Manutencao[MAX_MANUTENCOES];
  private Motorista[] motoristas = new Motorista[MAX_MOTORISTAS];

  public Veiculo(String modelo, int ano, String placa, int km, Estado estado) {
    this.modelo = modelo;
    this.ano = ano;
    this.placa = placa;
    this.km = km;
    this.estado = estado;
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
        manutencoes[numManutencoes++] = manutencao;
    }

  private void executarRemocaoManutencao(int i) {
    for (int j = i; j < numManutencoes - 1; j++) {
      manutencoes[j] = manutencoes[j + 1];
    }
    manutencoes[--numManutencoes] = null;
  }

  public void removeManutencao(Manutencao manutencao) {
    for (int i = 0; i < numManutencoes; i++) {
      if (manutencoes[i].equals(manutencao)) {
        executarRemocaoManutencao(i);
        return;
      }
    }
  }

  public Manutencao[] getAllManutencoes() {
    return manutencoes;
  }

  public void addMotorista(Motorista motorista) {
    motoristas[numMotoristas++] = motorista;
  }

  private void executarRemocaoMotorista(int i) {
    for (int j = i; j < numMotoristas - 1; j++) {
      motoristas[j] = motoristas[j + 1];
    }
    motoristas[--numMotoristas] = null;
  }

  public void removeMotorista(Motorista motorista) {
    for (int i = 0; i < numMotoristas; i++) {
      if (motoristas[i].equals(motorista)) {
        executarRemocaoMotorista(i);
        return;
      }
    }
  }

  public Motorista[] getAllMotoristas() {
    return motoristas;
  }
}
