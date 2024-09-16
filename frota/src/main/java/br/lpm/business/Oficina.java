package br.lpm.business;

public class Oficina {
  private String nome;
  private String endereco;
  private Veiculo[] veiculosManutencao;
  private int quantidadeVeiculos;

  public Oficina(String nome, String endereco, int capacidade) {
    this.nome = nome;
    this.endereco = endereco;
    this.veiculosManutencao = new Veiculo[capacidade];
    this.quantidadeVeiculos = 0;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public Veiculo[] getVeiculosManutencao() {
    Veiculo[] resultado = new Veiculo[quantidadeVeiculos];
    for (int i = 0; i < quantidadeVeiculos; i++) {
      resultado[i] = veiculosManutencao[i];
    }
    return resultado;
  }

  public void adicionarVeiculoManutencao(Veiculo veiculo) {
    if (quantidadeVeiculos < veiculosManutencao.length) {
      veiculosManutencao[quantidadeVeiculos++] = veiculo;
    } else {
      System.out.println("Capacidade máxima de veículos");
    }
  }

  public void removerVeiculoManutencao(Veiculo veiculo) {
    for (int i = 0; i < quantidadeVeiculos; i++) {
      if (veiculosManutencao[i].equals(veiculo)) {
        for (int j = i; j < quantidadeVeiculos - 1; j++) {
          veiculosManutencao[j] = veiculosManutencao[j + 1];
        }
        veiculosManutencao[--quantidadeVeiculos] = null;
        break;
      }
    }
  }
}
