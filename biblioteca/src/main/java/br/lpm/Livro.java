package br.lpm;

public class Livro {
  private String titulo;
  private String autor;
  private int isbn;
  private int numPaginas;
  private Estado estado;

  public Livro(String titulo, String autor, int isbn, int numPaginas) {
    this.titulo = titulo;
    this.autor = autor;
    this.isbn = isbn;
    this.numPaginas = numPaginas;
    this.estado = Estado.DISPONIVEL;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public int getIsbn() {
    return isbn;
  }

  public void setIsbn(int isbn) {
    this.isbn = isbn;
  }

  public int getNumPaginas() {
    return numPaginas;
  }

  public void setNumPaginas(int numPaginas) {
    this.numPaginas = numPaginas;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }
}
