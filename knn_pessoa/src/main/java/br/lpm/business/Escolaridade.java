package br.lpm.business;

public enum Escolaridade {
  NENHUMA(0),
  FUNDAMENTAL(1),
  MEDIO(2),
  SUPERIOR(3),
  POS_GRADUACAO(4);

  private int value;

  public int getValue() {
    return value;
  }

  private Escolaridade(int value) {
    this.value = value;
  }

  public static Escolaridade parseEscolaridade(String escolaridade) {
    escolaridade = escolaridade.toUpperCase();
    switch (escolaridade) {
      case "NENHUMA":
        return Escolaridade.NENHUMA;
      case "FUNDAMENTAL":
        return Escolaridade.FUNDAMENTAL;
      case "MEDIO":
        return Escolaridade.MEDIO;
      case "SUPERIOR":
        return Escolaridade.SUPERIOR;
      case "POS_GRADUACAO":
        return Escolaridade.POS_GRADUACAO;
      default:
        return null;
    }
  }
}
