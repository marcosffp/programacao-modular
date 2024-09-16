package br.lpm.business;

import java.time.LocalDate;

public class Oficina {
  private String nome;
  private String endereco;
  private int numManutencoes = 0;
  private static final int MAX_MANUTENCOES = 1000;
  private Manutencao[] manutencoes = new Manutencao[MAX_MANUTENCOES];


  public Oficina(String nome, String endereco) {
    this.nome = nome;
    this.endereco = endereco;
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

  public static int getMaxManutencoes() {
    return MAX_MANUTENCOES;
  }
  
  public int getNumManutencoes() {
    return numManutencoes;
  }

  public Manutencao[] getAllManutencoes() {
    return manutencoes;
  }

  public Manutencao getLastManutencaoFromVeiculo(Veiculo veiculo) {
    for (int i = numManutencoes - 1; i >= 0; i--) {
      if (manutencoes[i].getVeiculo().equals(veiculo)) {
        return manutencoes[i];
      }
    }
    return null;
  }

  public void addVeiculoToManutencao(Veiculo veiculo) {
    Manutencao manutencao = new Manutencao(LocalDate.now());
    if (numManutencoes < MAX_MANUTENCOES) {
      veiculo.setEstado(Estado.MANUTENCAO);
      manutencao.setVeiculo(veiculo);
      manutencao.setOficina(this);
      manutencoes[numManutencoes++] = manutencao;
    }
  }

  public void removeVeiculoFromManutencao(Veiculo veiculo) {
    veiculo.setEstado(Estado.TRANSITO);
  }
}
