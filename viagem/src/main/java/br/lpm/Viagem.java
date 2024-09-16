package br.lpm;

import java.time.LocalDate;

public abstract class Viagem {
  private String destino;
  private LocalDate dataPartida;
  private int duracao;
  private float distancia;

  public Viagem(String destino, LocalDate dataPartida, int duracao,float distancia) {
    this.destino = destino;
    this.dataPartida = dataPartida;
    this.duracao = duracao;
    this.distancia = distancia;
  }
  public String getDestino() {
    return destino;
  }
  public float getDistancia() {
    return distancia;
  }
  public void setDistancia(float distancia) {
    this.distancia = distancia;
  }
  public void setDestino(String destino) {
    this.destino = destino;
  }
  public LocalDate getDataPartida() {
    return dataPartida;
  }
  public void setDataPartida(LocalDate dataPartida) {
    this.dataPartida = dataPartida;
  }
  public int getDuracao() {
    return duracao;
  }

  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }
  
  public abstract float precoViagem();
}
