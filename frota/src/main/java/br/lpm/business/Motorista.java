package br.lpm.business;

public class Motorista {
  private String nome;
  private int id;
  private static int cont = 0;
  Veiculo veiculoAtual;
  private static final int MAX_VEICULOS = 1000;
  private int numVeiculos = 0;
  private Veiculo[] veiculos = new Veiculo[MAX_VEICULOS];

  public Motorista(String nome, int id, Veiculo veiculo) {
    this.nome = nome;
    this.id = id;
    this.veiculoAtual = veiculo;
    addVeiculo(veiculo);
    cont++;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public static int getCont() {
    return cont;
  }

  public static void setCont(int cont) {
    Motorista.cont = cont;
  }

  public void addVeiculo(Veiculo veiculo) {
    veiculos[numVeiculos++] = veiculo;
  }

  private void executarRemocao(int i) {
    for (int j = i; j < numVeiculos - 1; j++) {
      veiculos[j] = veiculos[j + 1];
    }
    veiculos[--numVeiculos] = null;
  }

  public void removeVeiculo(Veiculo veiculo) {
    for (int i = 0; i < numVeiculos; i++) {
      if (veiculos[i].equals(veiculo)) {
        executarRemocao(i);
        return;
      }
    }
  }

  public Veiculo[] getAllVeiculos() {
    return veiculos;
  }
}
