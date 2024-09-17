package br.lpm.business;

import java.time.LocalDate;

public class Oficina {
  private String nome;
  private String endereco;
  private static final int MAX_VALOR = 1000;
  private Manutencao[] manutencoes = new Manutencao[MAX_VALOR];
  private Mecanico[] mecanicos = new Mecanico[MAX_VALOR];
  private int numMecanicos = 0;
  private int numManutencoes = 0;

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

  public static int getMaxValor() {
    return MAX_VALOR;
  }

  public int getNumManutencoes() {
    return numManutencoes;
  }

  public int getNumMecanicos() {
    return numMecanicos;
  }

  public Manutencao[] getAllManutencoes() {
    return manutencoes;
  }

  public Mecanico[] getAllMecanicos() {
    return mecanicos;
  }

  public Manutencao getLastManutencaoFromVeiculo(Veiculo veiculo) {
    for (int i = numManutencoes - 1; i >= 0; i--) {
      if (manutencoes[i].getVeiculo().equals(veiculo)) {
        return manutencoes[i];
      }
    }
    return null;
  }

  public Mecanico getMecanicoById(int id) {
    for (Mecanico mecanico : mecanicos) {
      if (mecanico.getId() == id) {
        return mecanico;
      }
    }
    return null;
  }

  public void addVeiculoToManutencao(Veiculo veiculo) {
    if (numManutencoes >= MAX_VALOR) {
      return;
    }
    Manutencao manutencao = new Manutencao(LocalDate.now());
    veiculo.setEstado(Estado.MANUTENCAO);
    manutencao.setVeiculo(veiculo);
    manutencao.setOficina(this);
    ManipuladorDeArrays.adicionar(manutencao, manutencoes, numManutencoes, MAX_VALOR);
  }

  public void addMecanico(Mecanico mecanico) {
    ManipuladorDeArrays.adicionar(mecanico, mecanicos, numMecanicos, MAX_VALOR);
  }

  public void removeVeiculoFromManutencao(Veiculo veiculo) {
    Manutencao manutencaoARemover = getLastManutencaoFromVeiculo(veiculo);
    if (manutencaoARemover != null) {
      veiculo.setEstado(Estado.TRANSITO);
      ManipuladorDeArrays.remover(manutencaoARemover, manutencoes, numManutencoes, MAX_VALOR);
    }
  }

  public void removeMecanico(Mecanico mecanico) {
    ManipuladorDeArrays.remover(mecanico, mecanicos, numMecanicos, MAX_VALOR);
  }

  public void replaceManutencao(Manutencao velho, Manutencao novo) {
    ManipuladorDeArrays.replace(velho, novo, manutencoes);
  }

  public void replaceMecanico(Mecanico velho, Mecanico novo) {
    ManipuladorDeArrays.replace(velho, novo, mecanicos);
  }
}
