package main;

import business.Pessoa;
import java.util.Locale;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);

    System.out.println("---Cadastro de Pessoas---");

    int opcaoMenu;

    do {
      System.out.println("Escolha: ");
      System.out.println("1. Cadatrar a pessoa");
      System.out.println("2. Sair do sistema");
      opcaoMenu = sc.nextInt();
      sc.nextLine();

      switch (opcaoMenu) {
        case 1:
          System.out.print("Digite o nome: ");
          String nome = sc.nextLine();

          sc.nextLine();

          Pessoa pessoa = new Pessoa(nome);

          System.out.println("Cadastro da pessoa " + pessoa.getNome() + " finalizado");
          System.out.println();
          break;

        default:
          break;
      }

    } while (opcaoMenu != 2);
    sc.close();
  }
}
