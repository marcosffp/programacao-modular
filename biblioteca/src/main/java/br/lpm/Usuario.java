package br.lpm;

public class Usuario {
  private String nome;
  private int identificacao;
  private static final int MAX_LIVROS = 3;
  private int numeroLivros = 0;
  private Livro[] livros = new Livro[MAX_LIVROS];

  public Usuario(String nome, int identificacao) {
    this.nome = nome;
    this.identificacao = identificacao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getIdentificacao() {
    return identificacao;
  }

  public void setIdentificacao(int identificacao) {
    this.identificacao = identificacao;
  }

  public static int getMaxLivros() {
    return MAX_LIVROS;
  }

  public int getNumeroLivros() {
    return numeroLivros;
  }

  public void setNumeroLivros(int numeroLivros) {
    this.numeroLivros = numeroLivros;
  }

  public void pegarLivroEmprestado(Livro livro) {
    if (!(numeroLivros < MAX_LIVROS)) {
      return;
    }
    for (int i = 0; i < numeroLivros; i++) {
      if (livros[i].equals(livro)) {
        return;
      }
    }
    livros[numeroLivros++] = livro;
    livro.setEstado(Estado.EMPRESTADO);
  }
  
  public void devolverLivro(Livro livro) {
    for (int i = 0; i < numeroLivros; i++) {
      if (livros[i].equals(livro)) {
        livros[i].setEstado(Estado.DISPONIVEL);
        for (int j = i; j < numeroLivros; j++) {
          livros[j] = livros[j + 1];
        }
      }
    }
    livros[--numeroLivros] = null;
  }
  
  
  
}
