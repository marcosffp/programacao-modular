package br.lpm;

import java.time.LocalDate;

public final class Bibliotecario extends Usuario {
  private Sistema sistema;
  public Bibliotecario(String nome, int identificacao) {
    super(nome, identificacao);
  }

  public void addLivros(Livro livro) {
    sistema.addLivros(livro);
  }
  
  public void verificarDispobilidade(Livro livro) {
    if (livro.getEstado() == Estado.DISPONIVEL) {
      System.out.println("Está disponivel");
    } else {
      System.out.println("Não esta disponivel");
    }
  }
  
  public void registrarEmprestimo(Livro livro, Usuario usuario, LocalDate dataInicio, LocalDate dataTermino) {
    sistema.registrarEmprestimo(livro, usuario, dataInicio, dataTermino);
  }

  public void registarDevolucao(Livro livro, Usuario usuario) {
    sistema.registarDevolucao(livro, usuario);
  }
}
