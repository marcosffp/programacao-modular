package br.lpm;

import java.time.LocalDate;

public class ViagemInternacional extends Viagem {
  private Estado estado;
  private int numeroParadas;
  private static final float TARIFA_BASE = 1.00f;
  private static final float CUSTO_POR_PARADA = 1000f;

  public ViagemInternacional(String destino, LocalDate dataPartida, int duracao,float distancia, int numeroParadas) {
    super(destino, dataPartida, duracao,distancia);
    estado = Estado.COM_PASSAPORTE;
    this.numeroParadas = numeroParadas;
  }

  public Estado getEstado() {
    return estado;
  }

  @Override
  public float precoViagem() {
    return getDistancia() * numeroParadas * CUSTO_POR_PARADA * TARIFA_BASE;
  }


}
