package br.lpm;

public class Funcionario extends PessoaFisica {
  private String cargo;
  private float salario;
  public Funcionario(String nome, String endereco, String telefone, String cep, String cidade, String uf, String cpf,
      String cargo, float salario) {
    super(nome, endereco, telefone, cep, cidade, uf, cpf);
    this.cargo = cargo;
    this.salario = salario;
  }
  public String getCargo() {
    return cargo;
  }
  public void setCargo(String cargo) {
    this.cargo = cargo;
  }
  public float getSalario() {
    return salario;
  }
  public void setSalario(float salario) {
    this.salario = salario;
  }
}
