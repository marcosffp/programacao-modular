package br.lpm.business;

public enum EstadoCivil {
  SOLTEIRO(0),
  CASADO(1),
  VIUVO(2),
  SEPARADO(3),
  DIVORCIADO(4);

  private int value;

  public int getValue() {
    return value;
  }

  private EstadoCivil(int value) {
    this.value = value;
  }

  public static EstadoCivil parseEstadoCivil(String estadoCivil) {
    estadoCivil = estadoCivil.toUpperCase();
    switch (estadoCivil) {
      case "SOLTEIRO":
        return EstadoCivil.SOLTEIRO;
      case "CASADO":
        return EstadoCivil.CASADO;
      case "VIUVO":
        return EstadoCivil.VIUVO;
      case "SEPARADO":
        return EstadoCivil.SEPARADO;
      case "DIVORCIADO":
        return EstadoCivil.DIVORCIADO;
      default:
        return null;
    }
  }
}
