package main;

import business.Pessoa;
import java.util.Locale;
import java.util.Scanner;

public class App {

  public static int TAMANHO_MAX_VETOR = 20;
  public static int totalCadastrado = 0;
  public static Pessoa[] pessoas = new Pessoa[TAMANHO_MAX_VETOR];

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);

    System.out.println("-----------Cadastro de Pessoas-----------");

    int opcaoMenu;

    do {
      System.out.println("Escolha: ");
      System.out.println("1. Cadastrar pessoa");
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

          if (totalCadastrado + numeroCadastro <= TAMANHO_MAX_VETOR) {
            for (int i = 0; i < numeroCadastro; i++) {
              System.out.println("Cadastro #" + (totalCadastrado + 1) + ":");
              System.out.print("Digite o nome: ");
              String nome = sc.nextLine();

              Pessoa pessoa = new Pessoa(nome);
              pessoas[totalCadastrado] = pessoa;
              totalCadastrado++;

              System.out.println("Cadastro da pessoa #" + totalCadastrado + " com sucesso!");
              System.out.println();
            }
          } else {
            System.out.println("Erro: Não há espaço suficiente para cadastrar todas as pessoas.");
          }
          break;
        case 2:
          if (totalCadastrado > 0) {
            for (int i = 0; i < totalCadastrado; i++) {
              System.out.println(pessoas[i].getNome());
              System.out.println("------------------------------------------");
            }
          } else {
            System.out.println("Nenhuma pessoa cadastrada");
          }
          break;
        default:
          break;
      }
    } while (opcaoMenu != 3);
    sc.close();
  }
}
