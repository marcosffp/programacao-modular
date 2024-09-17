package br.lpm.business;

public class Motorista extends Pessoa {
  private Veiculo veiculoAtual;
  private static final int MAX_VEICULOS = 1000;
  private int numVeiculos = 0;
  private Veiculo[] veiculos = new Veiculo[MAX_VEICULOS];

  public Motorista(String nome, int id, Veiculo veiculo) {
    super(nome, id);
    this.veiculoAtual = veiculo;
    addVeiculo(veiculo);
  }

  public int getNumVeiculos() {
    return numVeiculos;
  }

  public void setVeiculoAtual(Veiculo veiculoAtual) {
    this.veiculoAtual = veiculoAtual;
  }

  public Veiculo getVeiculoAtual() {
    return veiculoAtual;
  }

  public void addVeiculo(Veiculo veiculo) {
    ManipuladorDeArrays.adicionar(veiculo, veiculos, numVeiculos, MAX_VEICULOS);
  }

  public void removeVeiculo(Veiculo veiculo) {
    ManipuladorDeArrays.remover(veiculo, veiculos, numVeiculos, MAX_VEICULOS);
  }

  public Veiculo getVeiculoByPlaca(String placa) {
    for (Veiculo veiculo : veiculos) {
      if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
        return veiculo;
      }
    }
    return null;
  }

  public Veiculo[] getAllVeiculos() {
    return veiculos;
  }

  public void replaceVeiculo(Veiculo velho, Veiculo novo) {
    ManipuladorDeArrays.replace(velho, novo, veiculos);
  }

  public static int getMaxVeiculos() {
    return MAX_VEICULOS;
  }
}
