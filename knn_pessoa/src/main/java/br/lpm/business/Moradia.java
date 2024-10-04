package br.lpm.business;

public enum Moradia {
  COM_FAMILIA(0),
  ALUGUEL(1),
  CASA_PROPRIA(2);

  private int value;

  public int getValue() {
    return value;
  }

  private Moradia(int value) {
    this.value = value;
  }

  public static Moradia parseMoradia(String moradia) {
    moradia = moradia.toUpperCase();
    switch (moradia) {
      case "COM_FAMILIA":
        return Moradia.COM_FAMILIA;
      case "ALUGUEL":
        return Moradia.ALUGUEL;
      case "CASA_PROPRIA":
        return Moradia.CASA_PROPRIA;
      default:
        return null;
    }
  }
}
