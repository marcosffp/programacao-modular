package br.lpm.main;

import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;

public class Main {
  private static final int TAMANHO_MAX_VETOR = 50;
  private static int totalCadastrado = 0;
  private static Pessoa[] pessoas = new Pessoa[TAMANHO_MAX_VETOR];
  private static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public static void main(String[] args) throws Exception {
    Locale.setDefault(Locale.US);

    int opcaoMenu;

    do {
      opcaoMenu =
          JOptionPane.showOptionDialog(
              null,
              "Escolha uma opção do menu:",
              "Cadastro de Pessoas",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              menu(),
              menu()[0]);
      switch (opcaoMenu) {
        case 0:
          cadastrarPessoas();
          break;
        case 1:
          listarPessoas();
          break;
        case 2:
          sairDoSistema();
          break;

        default:
          break;
      }

    } while (opcaoMenu != 2);
  }

  private static void sairDoSistema() {
    JOptionPane.showMessageDialog(
        null, "Saindo do sistema", "Sair", JOptionPane.INFORMATION_MESSAGE);
  }
  
  private static String[] menu() {
    return new String[] {"Cadastrar pessoa", "Listar pessoas cadastradas", "Sair do sistema"};
  }

  private static void cadastrarPessoas() {
    int espacoRestante = TAMANHO_MAX_VETOR - totalCadastrado;
    if (espacoRestante == 0) {
      JOptionPane.showMessageDialog(
          null, "Capacidade máxima atingida. Não é possível cadastrar mais pessoas.");
      return;
    }

    JOptionPane.showMessageDialog(null, "Cadastro de pessoa");
    int numeroCadastro =
        Integer.parseInt(JOptionPane.showInputDialog(null, "Quantas pessoas serão cadastradas? "));

    for (int i = 0; i < numeroCadastro; i++) {

      JOptionPane.showMessageDialog(null, "Cadastro n°" + (totalCadastrado + 1));

      String nome = solicitarNome();
      LocalDate dataNascimento = solicitarDataNascimento();
      Genero genero = solicitarGenero();
      float altura = solicitarAltura();
      int peso = solicitarPeso();
      float renda = solicitarRenda();
      String naturalidade = solicitarNaturalidade();
      Hobby hobby = solicitarHobby();
      EstadoCivil estadoCivil = solicitarEstadoCivil();
      Escolaridade escolaridade = solicitarEscolaridade();
      boolean feliz = solicitarFeliz();
      Moradia moradia = solicitarMoradia();
     
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
    }
  }

  private static void listarPessoas() {
    if (totalCadastrado > 0) {
      String listarPessoas = "";
      for (int i = 0; i < totalCadastrado; i++) {
        listarPessoas += pessoas[i].toString() + "\n";
      }
      JOptionPane.showMessageDialog(
          null, listarPessoas, "Lista de Pessoas", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          null, "Nenhuma pessoa cadastrada.", "Nenhuma Pessoa", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private static String solicitarNome() {
    return JOptionPane.showInputDialog("Digite o nome:");
  }

  private static LocalDate solicitarDataNascimento() {
    String dataNascimentoString =
        JOptionPane.showInputDialog("Digite a data de nascimento (DD/MM/AAAA): ");
    return LocalDate.parse(dataNascimentoString, formatadorData);
  }

  private static Genero solicitarGenero() {
    String[] opcoesGenero = new String[Genero.values().length];
    for (int j = 0; j < Genero.values().length; j++) {
      opcoesGenero[j] = Genero.values()[j].name();
    }
    int escolhaGenero =
        JOptionPane.showOptionDialog(
            null,
            "Escolha o gênero: ",
            "Gênero",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoesGenero,
            opcoesGenero[0]);

    if (escolhaGenero >= 0 && escolhaGenero < Genero.values().length) {
      return Genero.values()[escolhaGenero];
    } else {
      JOptionPane.showMessageDialog(
          null, "Escolha inválida! Por favor, selecione uma opção válida.");
      return solicitarGenero();
    }
  }

  private static Float solicitarAltura() {
    return Float.parseFloat(JOptionPane.showInputDialog(null, "Digite a altura: "));
  }

  private static int solicitarPeso() {
    return Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o peso: "));
  }

  private static float solicitarRenda() {
    return Float.parseFloat(JOptionPane.showInputDialog(null, "Digite a renda: "));
  }

  private static String solicitarNaturalidade() {
    return JOptionPane.showInputDialog("Digite a naturalidade:");
  }

  private static Hobby solicitarHobby() {
    String[] opcoesHobby = new String[Hobby.values().length];
    for (int j = 0; j < Hobby.values().length; j++) {
      opcoesHobby[j] = Hobby.values()[j].name();
    }

    int escolhaHobby = JOptionPane.showOptionDialog(
        null,
        "Escolha o hobby: ",
        "Hobby",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        opcoesHobby,
        opcoesHobby[0]);
    if (escolhaHobby >= 0 && escolhaHobby < Hobby.values().length) {
      return Hobby.values()[escolhaHobby];
    } else {
      JOptionPane.showMessageDialog(
          null, "Escolha inválida! Por favor, selecione uma opção válida.");
      return solicitarHobby();
    }
  }
  
  private static EstadoCivil solicitarEstadoCivil() {
    String[] opcoesEstadoCivil = new String[EstadoCivil.values().length];
    for (int j = 0; j < EstadoCivil.values().length; j++) {
      opcoesEstadoCivil[j] = EstadoCivil.values()[j].name();
    }

    int escolhaEstadoCivil = JOptionPane.showOptionDialog(
        null,
        "Escolha o estado civil:",
        "Estado Civil",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        opcoesEstadoCivil,
        opcoesEstadoCivil[0]);

    if (escolhaEstadoCivil >= 0 && escolhaEstadoCivil < EstadoCivil.values().length) {
      return EstadoCivil.values()[escolhaEstadoCivil];
    } else {
      JOptionPane.showMessageDialog(
          null, "Escolha inválida! Por favor, selecione uma opção válida.");
      return solicitarEstadoCivil();
    }
  }
  
  private static Escolaridade solicitarEscolaridade() {
    String[] opcoesEscolaridade = new String[Escolaridade.values().length];
    for (int j = 0; j < Escolaridade.values().length; j++) {
      opcoesEscolaridade[j] = Escolaridade.values()[j].name();
    }

    int escolhaEscolaridade = JOptionPane.showOptionDialog(
        null,
        "Escolha a escolaridade:",
        "Escolaridade",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        opcoesEscolaridade,
        opcoesEscolaridade[0]);

    if (escolhaEscolaridade >= 0 && escolhaEscolaridade < Escolaridade.values().length) {
      return Escolaridade.values()[escolhaEscolaridade];
    } else {
      JOptionPane.showMessageDialog(
          null, "Escolha inválida! Por favor, selecione uma opção válida.");
      return solicitarEscolaridade(); 
    }
  }
  
  private static boolean solicitarFeliz() {
    int opcaoFeliz = JOptionPane.showConfirmDialog(
        null, "Você é feliz?", "Felicidade", JOptionPane.YES_NO_OPTION);
    return opcaoFeliz == JOptionPane.YES_OPTION;
  }
  
  private static Moradia solicitarMoradia() {
    String[] opcoesMoradia = new String[Moradia.values().length];
    for (int j = 0; j < Moradia.values().length; j++) {
      opcoesMoradia[j] = Moradia.values()[j].name();
    }

    int escolhaMoradia =
        JOptionPane.showOptionDialog(
            null,
            "Escolha a moradia:",
            "Moradia",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoesMoradia,
            opcoesMoradia[0]);

    if (escolhaMoradia >= 0 && escolhaMoradia < Moradia.values().length) {
      return Moradia.values()[escolhaMoradia];
    } else {
      JOptionPane.showMessageDialog(
          null, "Escolha inválida! Por favor, selecione uma opção válida.");
      return solicitarMoradia();
    }
  }
}
