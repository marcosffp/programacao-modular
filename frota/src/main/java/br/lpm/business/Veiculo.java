package br.lpm.business;

public class Veiculo {
  private String modelo;
  private int ano;
  private String placa;
  private float quilometragem;
  private Estado estado;

  public Veiculo(String modelo, int ano, String placa, float quilometragem, Estado estado) {
    this.modelo = modelo;
    this.ano = ano;
    this.placa = placa;
    this.quilometragem = quilometragem;
    this.estado = estado;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public float getQuilometragem() {
    return quilometragem;
  }

  public void setQuilometragem(float quilometragem) {
    this.quilometragem = quilometragem;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }
}
