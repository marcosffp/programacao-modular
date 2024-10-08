package br.lpm.business;

import java.time.LocalDate;

public class Automovel {
  private String modelo;
  private LocalDate ano;
  private Cor cor;
  private static int contador = 0;
  private int cod;
  
  public String getModelo() {
    return modelo;
  }
  public void setModelo(String modelo) {
    this.modelo = modelo;
  }
  public LocalDate getAno() {
    return ano;
  }

  public void setAno(LocalDate ano) {
    if (!(ano.isAfter(LocalDate.of(1886, 1, 1)))) {
      return;
    }
    this.ano = ano;
  }
  public Cor getCor() {
    return cor;
  }

  public void setCor(Cor cor) {
    this.cor = cor;
  }
  
  public int getCod() {
    return cod;
  }

  
  public Automovel(String modelo, LocalDate ano, Cor cor) {
    this.setModelo(modelo);
    this.setAno(ano);
    this.setCor(cor);
    this.cod = ++contador;
  }
  @Override
  public String toString() {
    return "Automovel [modelo=" + modelo + ", ano=" + ano + ", cor=" + cor + ", cod=" + cod + "]";
  }
}
