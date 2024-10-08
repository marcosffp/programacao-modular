package br.lpm.business;

public class Peca {
  private String descricao;
  private static int contador = 0;
  private int cod;
  private Tipo tipo;
  private Marca marca;

  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public int getCod() {
    return cod;
  }
  public Tipo getTipo() {
    return tipo;
  }
  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }
  public Marca getMarca() {
    return marca;
  }

  public void setMarca(Marca marca) {
    this.marca = marca;
  }
  
  public String garantia() {
    if (tipo.equals(Tipo.USADA)) {
      return "Garantia de 30 dias";
    }
    else {
      return "Garantia determinada pelo fabricante";
    }
  }

  public Peca(String descricao,Tipo tipo, Marca marca) {
    this.setDescricao(descricao);
    this.setTipo(tipo);
    this.setMarca(marca);
    this.cod = ++contador;
  }
  @Override
  public String toString() {
    return "Peca [descricao=" + descricao + ", cod=" + cod + ", tipo=" + tipo + ", marca=" + marca + ", garantia()="
        + garantia() + "]";
  }
  
  

  
}
