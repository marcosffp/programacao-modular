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

  public static int TAMANHO_MAX_VETOR = 50;
  public static int totalCadastrado = 0;
  public static Pessoa[] pessoas = new Pessoa[TAMANHO_MAX_VETOR];

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

      Pessoa pessoa = entradaDadosPessoas(sc, formatadorData);

      adicionarPessoa(pessoa);

      System.out.println("Cadastro da pessoa n°" + totalCadastrado + " com sucesso!");
      System.out.println();
    }
  }

  public static Pessoa entradaDadosPessoas(Scanner sc, DateTimeFormatter formatadorData) {
    String nome = entradaString(sc, "Digite o nome: ");
    LocalDate dataNascimento =
        entradaLocalDate(sc, "Digite a data de nascimento (DD/MM/AAAA): ", formatadorData);

    System.out.println("Selecione o gênero: ");
    for (int i = 0; i < Genero.values().length; i++) {
      System.out.println((i + 1) + ". " + Genero.values()[i].name());
    }
    int opcaoGenero = sc.nextInt();
    sc.nextLine();
    Genero genero = Genero.values()[opcaoGenero - 1];

    float altura = entradaFloat(sc, "Digite a altura: ");
    sc.nextLine();
    int peso = entradaInt(sc, "Digite o peso: ");
    sc.nextLine();
    float renda = entradaFloat(sc, "Digite a renda: ");
    sc.nextLine();
    String naturalidade = entradaString(sc, "Digite o naturalidade: ");
    System.out.println();
    System.out.println("Selecione o hobby: ");
    for (int i = 0; i < Hobby.values().length; i++) {
      System.out.println((i + 1) + ". " + Hobby.values()[i].name());
    }
    int opcaoHobby = sc.nextInt();
    sc.nextLine();
    Hobby hobby = Hobby.values()[opcaoHobby - 1];
    System.out.println("Selecione o estado civil: ");
    for (int i = 0; i < EstadoCivil.values().length; i++) {
      System.out.println((i + 1) + ". " + EstadoCivil.values()[i].name());
    }
    int opcaoEstadoCivil = sc.nextInt();
    sc.nextLine();
    EstadoCivil estadoCivil = EstadoCivil.values()[opcaoEstadoCivil - 1];

    System.out.println("Selecione a escolaridade: ");
    for (int i = 0; i < Escolaridade.values().length; i++) {
      System.out.println((i + 1) + ". " + Escolaridade.values()[i].name());
    }
    int opcaoEscolaridade = sc.nextInt();
    sc.nextLine();
    Escolaridade escolaridade = Escolaridade.values()[opcaoEscolaridade - 1];

    boolean feliz = entradaBoolean(sc, "Digite se é feliz (s/n): ");

    System.out.println("Selecione a moradia: ");
    for (int i = 0; i < Moradia.values().length; i++) {
      System.out.println((i + 1) + ". " + Moradia.values()[i].name());
    }
    int opcaoMoradia = sc.nextInt();
    sc.nextLine();
    Moradia moradia = Moradia.values()[opcaoMoradia - 1];

    return new Pessoa(
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
  }

  public static void adicionarPessoa(Pessoa pessoa) {
    pessoas[totalCadastrado] = pessoa;
    totalCadastrado++;
  }

  public static String entradaString(Scanner sc, String mensagem) {
    System.out.print(mensagem);
    return sc.nextLine();
  }

  public static LocalDate entradaLocalDate(
      Scanner sc, String mensagem, DateTimeFormatter formatadorData) {

    System.out.print(mensagem);
    String dataNascimentoString = sc.nextLine();
    return LocalDate.parse(dataNascimentoString, formatadorData);
  }

  public static Float entradaFloat(Scanner sc, String mensagem) {
    System.out.print(mensagem);
    return sc.nextFloat();
  }

  public static int entradaInt(Scanner sc, String mensagem) {
    System.out.print(mensagem);
    return sc.nextInt();
  }

  public static boolean entradaBoolean(Scanner sc, String mensagem) {
    System.out.print(mensagem);
    char resposta = sc.next().charAt(0);
    return resposta == 's' || resposta == 'S';
  }

  public static void listarPessoas() {
    if (totalCadastrado > 0) {
      for (int i = 0; i < totalCadastrado; i++) {
        System.out.println(pessoas[i]);
      }
    }
  }
}
