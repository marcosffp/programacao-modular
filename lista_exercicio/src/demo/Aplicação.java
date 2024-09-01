package demo;

public class Aplicação {
  public static void main(String[] args) {
    Pessoa pessoa = new Pessoa();
    Pessoa pessoa2 = new Pessoa("Marcos", 18, "102932333", "Masculino");

    pessoa2.setNome("Ana");
    pessoa2.setIdade(17);
    pessoa2.setCpf("1111111111");
    pessoa2.setSexo("Feminino");

    System.out.println( pessoa2.getNome());
    System.out.println(pessoa2.getIdade());
    System.out.println(pessoa2.getCpf());
    System.out.println(pessoa2.getSexo());

    System.out.println("Ana é maior de idade?");
    System.out.println(pessoa2.isMaiorIdade()?"Sim":"Não");
  }
}
