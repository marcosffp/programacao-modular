package business;

public class Pessoa {
  String nome;

  public Pessoa() {}

  public Pessoa(String nome) {

    this.setNome(nome);
  }

  public void setNome(String nome) {
    if (nome != null
        && nome.chars()
            .allMatch(n -> (n >= 'a' && n <= 'z') || (n >= 'A' && n <= 'Z') || (n == ' '))
        && !nome.isEmpty()) {
      this.nome = nome;
    } else {
      throw new IllegalArgumentException(
          "Nome inválido. O nome deve conter apenas letras e espaço");
    }
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    return "Nome: " + nome + "\n";
  }
}
