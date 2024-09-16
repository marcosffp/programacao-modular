package br.lpm;

import java.time.LocalDate;

public class ViagemNacional extends Viagem {
  private Estado estado;
  private static final float TARIFA_BASE = 0.5f;

  public ViagemNacional(String destino, LocalDate dataPartida, int duracao,float distancia) {
    super(destino, dataPartida, duracao,distancia);
    estado = Estado.SEM_PASSAPORTE;
  }

  public Estado getEstado() {
    return estado;
  }

  @Override
  public float precoViagem() {
    return getDistancia() * TARIFA_BASE;
  }
}
