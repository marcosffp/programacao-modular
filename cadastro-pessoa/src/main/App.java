package main;

import business.Pessoa;
import java.util.Locale;
import java.util.Scanner;

public class App {

  public static Pessoa[] pessoas;

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);

    System.out.println("-----------Cadastro de Pessoas-----------");

    int opcaoMenu;

    do {
      System.out.println("Escolha: ");
      System.out.println("1. Cadatrar a pessoa");
      System.out.println("2. Listar pessoas cadastradas");
      System.out.println("3. Sair do sistema");
      opcaoMenu = sc.nextInt();
      sc.nextLine();
      System.out.println();

      switch (opcaoMenu) {
        case 1:
          System.out.println("Cadastro de pessoa");
          System.out.println();
          System.out.print("Quantas pessoas serão cadastradas? ");
          int numeroCadastro = sc.nextInt();
          sc.nextLine();

          pessoas = new Pessoa[numeroCadastro];
          for (int i = 0; i < numeroCadastro; i++) {
            System.out.println("Cadastro #" + (i + 1) + ":");
            System.out.print("Digite o nome: ");
            String nome = sc.nextLine();

            Pessoa pessoa = new Pessoa(nome);

            pessoas[i] = pessoa;

            System.out.println("Cadastro da pessoa #" + (i + 1) + " com sucesso!");
            System.out.println();
          }
          break;
        case 2:
          if ((pessoas != null) && (pessoas.length > 0)) {
            for (Pessoa pessoa : pessoas) {
              System.out.println(pessoa.getNome());
              System.out.println();
              System.out.println("------------------------------------------");
            }
          } else {
            System.out.println("Nenhuma pessoa cadastrada");
          }

        default:
          break;
      }
    } while (opcaoMenu != 3);
    sc.close();
  }
}
