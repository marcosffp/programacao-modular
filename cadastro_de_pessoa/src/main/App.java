package main;

import business.Escolaridade;
import business.EstadoCivil;
import business.Genero;
import business.Hobby;
import business.Moradia;
import business.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class App {

  private static int TAMANHO_MAX_VETOR = 100;
  private static int totalCadastrado = 0;
  private static Pessoa[] pessoas = new Pessoa[TAMANHO_MAX_VETOR];
  

  public static void main(String[] args) throws Exception {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    System.out.println("-------------Cadastro de Pessoas-------------");
    int opcaoMenu;

    do {
      menu();
      opcaoMenu = sc.nextInt();
      System.out.println();

      switch (opcaoMenu) {
        case 1:
          cadastrarPessoas(sc, formatadorData);
          break;
        case 2:
          listarPessoas();
          break;
        case 3:
          System.out.println("Saindo do sistema");
          break;

        default:
          break;
      }

    } while (opcaoMenu != 3);
    sc.close();
  }

  public static void menu() {
    System.out.println("Escolha uma dessas opções: ");
    System.out.println("1. Cadastrar pessoa");
    System.out.println("2. Listar pessoas cadastradas");
    System.out.println("3. Sair do sistema");
  }

  public static void cadastrarPessoas(Scanner sc, DateTimeFormatter formatadorData) {
    System.out.println("-------------Cadastro de pessoa-------------");
    System.out.println();
    System.out.print("Quantas pessoas serão cadastradas? ");
    int numeroCadastro = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < numeroCadastro; i++) {
      System.out.println("Cadastro n°" + (totalCadastrado + 1) + ":");

      System.out.print("Digite o nome: ");
      String nome = sc.nextLine();

      System.out.print("Digite a data de nascimento (DD/MM/AAAA): ");
      String dataNascimentoString = sc.nextLine();
      LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatadorData);

      System.out.println("Selecione o gênero: ");
      for (int j = 0; j < Genero.values().length; j++) {
        System.out.println((j + 1) + ". " + Genero.values()[j].name());
      }
      int opcaoGenero = sc.nextInt();
      sc.nextLine();
      Genero genero = Genero.values()[opcaoGenero - 1];

      System.out.print("Digite a altura: ");
      float altura = sc.nextFloat();
      sc.nextLine();

      System.out.print("Digite o peso: ");
      int peso = sc.nextInt();
      sc.nextLine();

      System.out.print("Digite a renda: ");
      float renda = sc.nextFloat();
      sc.nextLine();

      System.out.print("Digite o naturalidade: ");
      String naturalidade = sc.nextLine();
      System.out.println();

      System.out.println("Selecione o hobby: ");
      for (int j = 0; j < Hobby.values().length; j++) {
        System.out.println((j + 1) + ". " + Hobby.values()[j].name());
      }
      int opcaoHobby = sc.nextInt();
      sc.nextLine();
      Hobby hobby = Hobby.values()[opcaoHobby - 1];

      System.out.println("Selecione o estado civil: ");
      for (int j = 0; j < EstadoCivil.values().length; j++) {
        System.out.println((j + 1) + ". " + EstadoCivil.values()[j].name());
      }
      int opcaoEstadoCivil = sc.nextInt();
      sc.nextLine();
      EstadoCivil estadoCivil = EstadoCivil.values()[opcaoEstadoCivil - 1];

      System.out.println("Selecione a escolaridade: ");
      for (int j = 0; j < Escolaridade.values().length; j++) {
        System.out.println((j + 1) + ". " + Escolaridade.values()[j].name());
      }
      int opcaoEscolaridade = sc.nextInt();
      sc.nextLine();
      Escolaridade escolaridade = Escolaridade.values()[opcaoEscolaridade - 1];

      System.out.print("Digite se é feliz (s/n): ");
      char respostaFeliz = sc.next().charAt(0);
      boolean feliz = respostaFeliz == 's' || respostaFeliz == 'S';
      sc.nextLine();

      System.out.println("Selecione a moradia: ");
      for (int j = 0; j < Moradia.values().length; j++) {
        System.out.println((j + 1) + ". " + Moradia.values()[j].name());
      }
      int opcaoMoradia = sc.nextInt();
      sc.nextLine();
      Moradia moradia = Moradia.values()[opcaoMoradia - 1];

      Pessoa pessoa = new Pessoa(
          nome,
          dataNascimento,
          genero,
          altura,
          peso,
          renda,
          naturalidade,
          hobby,
          estadoCivil,
          escolaridade,
          feliz,
          moradia);

      pessoas[totalCadastrado] = pessoa;
      totalCadastrado++;

      System.out.println("Cadastro da pessoa n°" + totalCadastrado + " com sucesso!");
      System.out.println();
    }
  }


  public static void listarPessoas() {
    if (totalCadastrado > 0) {
      for (int i = 0; i < totalCadastrado; i++) {
        System.out.println(pessoas[i]);
      }
    }
  }
}
