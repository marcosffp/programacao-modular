package br.lpm.business;

public class Servico {
  private String descricao;
  private int numHorasTrabalho;
  private static int contador = 0;
  private int cod;

  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public int getNumHorasTrabalho() {
    return numHorasTrabalho;
  }
  public void setNumHorasTrabalho(int numHorasTrabalho) {
    if (!(numHorasTrabalho >= 1)) {
      return;
    }
    this.numHorasTrabalho = numHorasTrabalho;
  }
  public int getCod() {
    return cod;
  }

  public Servico(String descricao, int numHorasTrabalho) {
    this.setDescricao(descricao);
    this.setNumHorasTrabalho(numHorasTrabalho);
    this.cod = ++contador;
  }
  @Override
  public String toString() {
    return "Servico [descricao=" + descricao + ", numHorasTrabalho=" + numHorasTrabalho + ", cod=" + cod + "]";
  }
  

}


