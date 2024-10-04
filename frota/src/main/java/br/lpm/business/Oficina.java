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
    numManutencoes=ManipuladorDeArrays.adicionar(manutencao, manutencoes, numManutencoes, MAX_VALOR);
  }

  public void addMecanico(Mecanico mecanico) {
    numMecanicos=ManipuladorDeArrays.adicionar(mecanico, mecanicos, numMecanicos, MAX_VALOR);
  }

  public void removeVeiculoFromManutencao(Veiculo veiculo) {
    Manutencao manutencaoARemover = getLastManutencaoFromVeiculo(veiculo);
    if (manutencaoARemover != null) {
      veiculo.setEstado(Estado.TRANSITO);
      numManutencoes=ManipuladorDeArrays.remover(manutencaoARemover, manutencoes, numManutencoes, MAX_VALOR);
    }
  }

  public void removeMecanico(Mecanico mecanico) {
    numMecanicos=ManipuladorDeArrays.remover(mecanico, mecanicos, numMecanicos, MAX_VALOR);
  }

  public void replaceManutencao(Manutencao velho, Manutencao novo) {
    ManipuladorDeArrays.replace(velho, novo, manutencoes);
  }

  public void replaceMecanico(Mecanico velho, Mecanico novo) {
    ManipuladorDeArrays.replace(velho, novo, mecanicos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Detalhes da Oficina:\n");
    sb.append("***********************************\n");

    sb.append("Nome: ").append(nome).append("\n");
    sb.append("Endereço: ").append(endereco).append("\n");

    sb.append("Número de Manutenções: ").append(numManutencoes).append("\n");
    if (numManutencoes > 0) {
      sb.append("Manutenções:\n");
      for (int i = 0; i < numManutencoes; i++) {
        sb.append(manutencoes[i]).append("\n");
      }
    } else {
      sb.append("Nenhuma manutenção registrada.\n");
    }

    sb.append("Número de Mecânicos: ").append(numMecanicos).append("\n");
    if (numMecanicos > 0) {
      sb.append("Mecânicos:\n");
      for (int i = 0; i < numMecanicos; i++) {
        sb.append(mecanicos[i]).append("\n");
      }
    } else {
      sb.append("Nenhum mecânico registrado.\n");
    }

    sb.append("***********************************\n");
    return sb.toString();
  }
}
