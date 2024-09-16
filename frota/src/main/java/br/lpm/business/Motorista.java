package br.lpm.business;

public class Motorista {
  private String nome;
  private int identificacao;
  private Veiculo veiculo;

  public Motorista(String nome, int identificacao, Veiculo veiculo) {
    this.nome = nome;
    this.identificacao = identificacao;
    this.veiculo = veiculo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getIdentificacao() {
    return identificacao;
  }

  public void setIdentificacao(int identificacao) {
    this.identificacao = identificacao;
  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }
}
