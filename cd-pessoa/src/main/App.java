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
      System.out.println("Escolha uma dessas opções: ");
      System.out.println("1. Cadastrar pessoa");
      System.out.println("2. Listar pessoas cadastradas");
      System.out.println("3. Sair do sistema");
      opcaoMenu = sc.nextInt();
      System.out.println();

      switch (opcaoMenu) {
        case 1:
          System.out.println("-------------Cadastro de pessoa-------------");
          System.out.println();
          System.out.print("Quantas pessoas serão cadastradas? ");
          int numeroCadastro = sc.nextInt();
          sc.nextLine();

          for (int i = 0; i < numeroCadastro; i++) {

            System.out.println("Cadastro n°" + (totalCadastrado + 1) + ":");
            System.out.print("Digite o nome:");
            String nome = sc.nextLine();
            System.out.print("Digite a data de nascimento (DD/MM/AAAA): ");
            String dataNascimentoString = sc.nextLine();
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatadorData);
            System.out.println();

            System.out.println("Selecione o gênero: ");
            for (int j = 0; j < Genero.values().length; j++) {
              System.out.println((j + 1) + ". " + Genero.values()[j]);
            }
            int generoSelecionado = sc.nextInt();
            Genero genero = Genero.values()[generoSelecionado - 1];
            System.out.println();

            System.out.print("Digite a altura: ");
            float altura = sc.nextFloat();
            sc.nextLine();
            System.out.print("Digite o peso: ");
            int peso = sc.nextInt();
            sc.nextLine();
            System.out.print("Digite a renda: ");
            float renda = sc.nextFloat();
            sc.nextLine();
            System.out.print("Digite o naturalidade:");
            String naturalidade = sc.nextLine();
            System.out.println();

            System.out.println("Selecione o hobby: ");
            for (int j = 0; j < Hobby.values().length; j++) {
              System.out.println((j + 1) + ". " + Hobby.values()[j]);
            }
            int hobbySelecionado = sc.nextInt();
            Hobby hobby = Hobby.values()[hobbySelecionado - 1];
            System.out.println();

            System.out.println("Selecione o estado civil: ");
            for (int j = 0; j < EstadoCivil.values().length; j++) {
                System.out.println((j + 1) + ". " + EstadoCivil.values()[j]);
        
            }
            int estadoCivilSelecionado = sc.nextInt();
            EstadoCivil estadoCivil = EstadoCivil.values()[estadoCivilSelecionado - 1];
            System.out.println();

            System.out.println("Selecione a escolaridade: ");
            for (int j = 0; j < Escolaridade.values().length; j++) {
              System.out.println((j + 1) + ". " + Escolaridade.values()[j]);
            }
            int escolaridadeSelecionado = sc.nextInt();
            Escolaridade escolaridade = Escolaridade.values()[escolaridadeSelecionado - 1];
            System.out.println();
            System.out.print("Digite se é feliz (s/n): ");
            char isFeliz = sc.next().charAt(0);
            boolean feliz;
            if (isFeliz == 's') {
              feliz = true;
          } else {
              feliz = false;
          }

            System.out.println();
            System.out.println("Selecione a moradia: ");
            for (int j = 0; j < Moradia.values().length; j++) {
              System.out.println((j + 1) + ". " + Moradia.values()[j]);
            }
            int moradiaSelecionado = sc.nextInt();
            Moradia moradia = Moradia.values()[moradiaSelecionado - 1];
            System.out.println();

            Pessoa pessoa =
                new Pessoa(
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

          break;
        case 2:
          if (totalCadastrado > 0) {
            for (int i = 0; i < totalCadastrado; i++) {
              System.out.println(pessoas[i]);
            }
          }
          break;

        default:
          break;
      }

    } while (opcaoMenu != 3);
    sc.close();
  }
}
