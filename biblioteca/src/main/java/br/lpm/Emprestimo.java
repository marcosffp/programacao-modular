package br.lpm;

import java.time.LocalDate;

public class Emprestimo {
  private Livro livro;
  private LocalDate dataInicio;
  private LocalDate dataDevolucao;
  private Usuario usuario;
  public Emprestimo(Livro livro, LocalDate dataInicio, LocalDate dataDevolucao, Usuario usuario) {
    this.livro = livro;
    this.dataInicio = dataInicio;
    this.dataDevolucao = dataDevolucao;
    this.usuario = usuario;
  }
  public Livro getLivro() {
    return livro;
  }
  public void setLivro(Livro livro) {
    this.livro = livro;
  }
  public LocalDate getDataInicio() {
    return dataInicio;
  }
  public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
  }
  public LocalDate getDataDevolucao() {
    return dataDevolucao;
  }
  public void setDataDevolucao(LocalDate dataDevolucao) {
    this.dataDevolucao = dataDevolucao;
  }
  public Usuario getUsuario() {
    return usuario;
  }
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
