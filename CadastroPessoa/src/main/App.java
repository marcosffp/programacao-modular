package main;

import business.EstadoCivil;
import business.FormacaoAcademica;
import business.Pessoa;
import business.Profissao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {

  public static List<Pessoa> pessoas = new ArrayList<>();

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);

    System.out.println("---Cadastro de Pessoas---");

    int opcaoMenu;

    do {
      System.out.println("Escolha: ");
      System.out.println("1. Cadatrar a pessoa");
      System.out.println("2. Listar pessoas cadastradas");
      System.out.println("3. Sair do sistema");
      opcaoMenu = sc.nextInt();
      sc.nextLine();

      switch (opcaoMenu) {
        case 1:
          System.out.println("Cadastro de pessoa");
          System.out.println("Quantas pessoas serão cadastradas? ");
          int numeroCadastro = sc.nextInt();
          sc.nextLine();

          for (int i = 1; i <= numeroCadastro; i++) {
            System.out.println("Cadastro #" + i + ":");
            System.out.print("Digite o nome: ");
            String nome = sc.nextLine();
            System.out.print("Digite a altura: ");
            float altura = sc.nextFloat();
            System.out.print("Digite o peso: ");
            float peso = sc.nextFloat();

            sc.nextLine();

            System.out.print("Digite a data de nascimento(AAAA-MM-DD): ");
            String dataString = sc.nextLine();
            LocalDate dataNascimento = LocalDate.parse(dataString);

            System.out.print(
                "Digite o estado civil (SOLTEIRO, "
                    + "CASADO, "
                    + "UNIAO_ESTAVEL, "
                    + "DIVORCIADO, "
                    + "SEPARADO, "
                    + "VIUVO): ");
            String estadoCivil = sc.nextLine();

            System.out.print(
                "Digite a formação acadêmica (NENHUMA, " + "BASICA, " + "MEDIA, " + "SUPERIOR): ");
            String formacaoAcademica = sc.nextLine();

            System.out.print(
                "Digite a profissão (DESEMPREGADO, "
                    + "ESTUDANTE, "
                    + "AUTONOMO, "
                    + "CLT, "
                    + "EMPRESARIO): ");
            String profissao = sc.nextLine();

            System.out.print("Digite 'true' se você tem vida social ou 'false' se não tem: ");
            boolean vidaSocial = sc.nextBoolean();
            System.out.print("Digite 'true' se tem hobby ou 'false' se não tem: ");
            boolean hobby = sc.nextBoolean();

            System.out.print(
                "Digite a quantidade de dias que a pessoa faz atividade física por semana: ");
            int atividadeFisica = sc.nextInt();
            System.out.print("Digite o valor de escala entre 1 e 10 de saúde: ");
            int saude = sc.nextInt();

            sc.nextLine();

            Pessoa pessoa =
                new Pessoa(
                    nome,
                    altura,
                    peso,
                    dataNascimento,
                    EstadoCivil.valueOf(estadoCivil),
                    FormacaoAcademica.valueOf(formacaoAcademica),
                    Profissao.valueOf(profissao),
                    vidaSocial,
                    hobby,
                    atividadeFisica,
                    saude);

            pessoas.add(pessoa);

            System.out.println("Cadastro da pessoa #" + i + " finalizado");
            System.out.println();
          }
          break;
        case 2:
          if (!(pessoas.isEmpty())) {
            for (Pessoa p : pessoas) {
              System.out.println(p);
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
