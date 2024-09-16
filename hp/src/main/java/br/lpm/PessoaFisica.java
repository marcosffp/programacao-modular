package br.lpm;

public class PessoaFisica extends Pessoa {
  private String cpf;

  public PessoaFisica(String nome, String endereco, String telefone, String cep, String cidade, String uf, String cpf) {
    super(nome, endereco, telefone, cep, cidade, uf);
    this.cpf = cpf;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}
