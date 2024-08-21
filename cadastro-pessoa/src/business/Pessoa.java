package business;

public class Pessoa {
  private String nome;

  public Pessoa() {}

  public Pessoa(String nome) {

    this.setNome(nome);
  }

  public void setNome(String nome) {
    if (nome == null || nome.isEmpty()) {
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
    }

    char[] nomeChars = nome.toLowerCase().toCharArray();
    for (char nomeChar : nomeChars) {
      if (!(nomeChar >= 'a' && nomeChar <= 'z') && nomeChar != ' ') {
        throw new IllegalArgumentException("Nome deve ser alfabético com apenas letras e espaço");
      }
    }

    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
}
