package br.lpm;

import java.time.LocalDate;

public class Sistema {
  private Bibliotecario bibliotecario;
  private static final int MAX_LIVROS = 55;
  private int numLivros = 0;
  private Livro[] livros = new Livro[MAX_LIVROS];
  private Emprestimo[] emprestimos = new Emprestimo[MAX_LIVROS];
  private int numEmprestimo=0;

  public Bibliotecario getBibliotecario() {
    return bibliotecario;
  }

  public void setBibliotecario(Bibliotecario bibliotecario) {
    this.bibliotecario = bibliotecario;
  }

  public static int getMaxLivros() {
    return MAX_LIVROS;
  }

  public void addLivros(Livro livro) {
    if (!(numLivros<MAX_LIVROS)) {
      return;
    }
    for (int i = 0; i < numLivros; i++) {
      if (livros[i].equals(livro)) {
        return;
      }
    }
    livros[numLivros++] = livro;
  }

  public void removeLivro(Livro livro) {
    for (int i = 0; i < numLivros; i++) {
      if (livros[i].equals(livro)) {
        for (int j = i; j < numLivros; j++) {
          livros[j] = livros[j + 1];
        }
      }
    }
    livros[--numLivros] = null;
  }

  public void replaceLivro(Livro velho, Livro novo) {
    for (int i = 0; i < livros.length; i++) {
      if (livros[i].equals(novo)) {
        return;
      }
      if (livros[i].equals(velho)) {
        livros[i] = novo;
      }
    }
  }

  public Livro getLivroByName(String nome) {
    for (int i = 0; i < numLivros; i++) {
      if (livros[i].getTitulo().equalsIgnoreCase(nome)) {
        return livros[i];
      }
    }
    return null;
  }
  
  public void removeAll() {
    for (int i = 0; i < numLivros; i++) {
      livros[i] = null;
    }
  }
  
  public Livro[] getAll() {
    Livro[] aux = new Livro[numLivros];
    for (int i = 0; i < numLivros; i++) {
      aux[i] = livros[i];
    }
    return aux;
  }

  public int size() {
    return numLivros;
  }

  public void registrarEmprestimo(Livro livro, Usuario usuario, LocalDate dataInicio, LocalDate dataTermino) {
    if (livro.getEstado() == Estado.DISPONIVEL) {
      usuario.pegarLivroEmprestado(livro);
      livro.setEstado(Estado.EMPRESTADO);
      Emprestimo emprestimo = new Emprestimo(livro, dataInicio, dataTermino, usuario);
      if (numEmprestimo < MAX_LIVROS) {
        emprestimos[numEmprestimo++] = emprestimo;
      }
    }
  }
  
  public void registarDevolucao(Livro livro, Usuario usuario) {
    for (int i = 0; i < numEmprestimo; i++) {
      if (emprestimos[i].getLivro().equals(livro) && emprestimos[i].getUsuario().equals(usuario)) {
        livro.setEstado(Estado.DISPONIVEL);
        usuario.devolverLivro(livro);
        for (int j = i; j < numEmprestimo - 1; j++) {
          emprestimos[j] = emprestimos[j + 1];
        }
      }
    }
    emprestimos[--numEmprestimo] = null;
  }
}
