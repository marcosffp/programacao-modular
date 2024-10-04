package br.lpm.business;

public enum Hobby {
  ARTE(0),
  ESPORTE(1),
  CINEMA(2),
  LIVRO(3),
  MÚSICA(4),
  CULINÁRIA(5),
  GAME(6),
  NENHUM(7);

  private int value;

  public int getValue() {
    return value;
  }

  private Hobby(int value) {
    this.value = value;
  }

  public static Hobby parseHobby(String hobby) {
    hobby = hobby.toUpperCase();
    switch (hobby) {
      case "ARTE":
        return Hobby.ARTE;
      case "ESPORTE":
        return Hobby.ESPORTE;
      case "CINEMA":
        return Hobby.CINEMA;
      case "LIVRO":
        return Hobby.LIVRO;
      case "MÚSICA":
        return Hobby.MÚSICA;
      case "CULINÁRIA":
        return Hobby.CULINÁRIA;
      case "GAME":
        return Hobby.GAME;
      case "NENHUM":
        return Hobby.NENHUM;
      default:
        return null;
    }
  }
}
