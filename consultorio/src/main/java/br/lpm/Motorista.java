package br.lpm;

public class Motorista {
  private String nome;
  private int numeroIdentificacao;
  private Veiculo veiculo;
  public Motorista(String nome, int numeroIdentificacao, Veiculo veiculo) {
    this.nome = nome;
    this.numeroIdentificacao = numeroIdentificacao;
    this.veiculo = veiculo;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public int getNumeroIdentificacao() {
    return numeroIdentificacao;
  }
  public void setNumeroIdentificacao(int numeroIdentificacao) {
    this.numeroIdentificacao = numeroIdentificacao;
  }
  public Veiculo getVeiculo() {
    return veiculo;
  }
  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }
}
