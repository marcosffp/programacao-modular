package br.lpm.business;

public enum Genero {
  FEMININO(0),
  MASCULINO(1),
  NAO_BINARIO(2),
  NAO_RESPONDER(3);

  private int value;

  public int getValue() {
    return value;
  }

  private Genero(int value) {
    this.value = value;
  }

  public static Genero parseGenero(String genero) {
    genero = genero.toUpperCase();
    switch (genero) {
      case "FEMININO":
        return Genero.FEMININO;
      case "MASCULINO":
        return Genero.MASCULINO;
      case "NAO_BINARIO":
        return Genero.NAO_BINARIO;
      case "NAO_RESPONDER":
        return Genero.NAO_RESPONDER;
      default:
        return null;
    }
  }
}
