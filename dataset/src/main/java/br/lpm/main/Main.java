package br.lpm.main;

import br.lpm.business.Dataset;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Main {
  public static Dataset dataset = new Dataset();
  private static int totalCadastrado = 0;
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
          histogramFormacaoAcadêmica();
          break;
        case 3:
          pieEstadoCivil();
        case 4:
          sairDoSistema();
        default:
          break;
      }

    } while (opcaoMenu != 4);
  }

  private static void sairDoSistema() {
    JOptionPane.showMessageDialog(
        null, "Saindo do sistema", "Sair", JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
  }

  private static String[] menu() {
    return new String[] {
      "Cadastrar pessoa",
      "Listar pessoas cadastradas",
      "Histograma Formação Acadêmica",
      "Pie Chart dos Estados Civis",
      "Sair do sistema"
    };
  }

  private static void cadastrarPessoas() {
    int espacoRestante = Dataset.getMaxPessoas() - totalCadastrado;
    if (espacoRestante == 0) {
      JOptionPane.showMessageDialog(
          null, "Capacidade máxima atingida. Não é possível cadastrar mais pessoas.");
      return;
    }
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

      dataset.addPessoa(pessoa);
      totalCadastrado++;

      JOptionPane.showMessageDialog(
          null,
          "Cadastro da pessoa " + totalCadastrado + " com sucesso!",
          "Informação",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private static void listarPessoas() {
    if (totalCadastrado > 0) {
      Pessoa[] pessoas = dataset.getAll();
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
    return JOptionPane.showInputDialog(
        null,
        "Digite o nome:",
        "Cadastro n°" + (totalCadastrado + 1),
        JOptionPane.QUESTION_MESSAGE);
  }

  private static LocalDate solicitarDataNascimento() {
    String dataNascimentoString =
        JOptionPane.showInputDialog(
            null,
            "Digite a data de nascimento (DD/MM/AAAA): ",
            "Cadastro n°" + (totalCadastrado + 1),
            JOptionPane.QUESTION_MESSAGE);
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
            "Cadastro n°" + (totalCadastrado + 1),
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
    return Float.parseFloat(
        JOptionPane.showInputDialog(
            null,
            "Digite a altura: ",
            "Cadastro n°" + (totalCadastrado + 1),
            JOptionPane.QUESTION_MESSAGE));
  }

  private static int solicitarPeso() {
    return Integer.parseInt(
        JOptionPane.showInputDialog(
            null,
            "Digite o peso: ",
            "Cadastro n°" + (totalCadastrado + 1),
            JOptionPane.QUESTION_MESSAGE));
  }

  private static float solicitarRenda() {
    return Float.parseFloat(
        JOptionPane.showInputDialog(
            null,
            "Digite a renda: ",
            "Cadastro n°" + (totalCadastrado + 1),
            JOptionPane.QUESTION_MESSAGE));
  }

  private static String solicitarNaturalidade() {
    return JOptionPane.showInputDialog(
        null,
        "Digite a naturalidade:",
        "Cadastro n°" + (totalCadastrado + 1),
        JOptionPane.QUESTION_MESSAGE);
  }

  private static Hobby solicitarHobby() {
    String[] opcoesHobby = new String[Hobby.values().length];
    for (int j = 0; j < Hobby.values().length; j++) {
      opcoesHobby[j] = Hobby.values()[j].name();
    }

    int escolhaHobby =
        JOptionPane.showOptionDialog(
            null,
            "Escolha o hobby: ",
            "Cadastro n°" + (totalCadastrado + 1),
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

    int escolhaEstadoCivil =
        JOptionPane.showOptionDialog(
            null,
            "Escolha o estado civil:",
            "Cadastro n°" + (totalCadastrado + 1),
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

    int escolhaEscolaridade =
        JOptionPane.showOptionDialog(
            null,
            "Escolha a escolaridade:",
            "Cadastro n°" + (totalCadastrado + 1),
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
    int opcaoFeliz =
        JOptionPane.showConfirmDialog(
            null,
            "Você é feliz?",
            "Cadastro n°" + (totalCadastrado + 1),
            JOptionPane.YES_NO_OPTION);
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
            "Cadastro n°" + (totalCadastrado + 1),
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

  public static void histogramFormacaoAcadêmica() {
    if (totalCadastrado <= 0) {
      JOptionPane.showMessageDialog(
          null, "Nenhuma pessoa cadastrada.", "Nenhuma Pessoa", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    Pessoa[] pessoas = dataset.getAll();
    Escolaridade[] tEscolaridades = Escolaridade.values();
    int[] contagemEscolaridade = new int[Escolaridade.values().length];

    for (int i = 0; i < totalCadastrado; i++) {
      Escolaridade escolaridade = pessoas[i].getEscolaridade();
      for (int j = 0; j < contagemEscolaridade.length; j++) {
        if (tEscolaridades[j] == escolaridade) {
          contagemEscolaridade[j]++;
        }
      }
    }

    DefaultCategoryDataset categoria = new DefaultCategoryDataset();
    for (int i = 0; i < contagemEscolaridade.length; i++) {
      categoria.addValue(
          contagemEscolaridade[i], "Formação Acadêmica", tEscolaridades[i].toString());
    }

    JFreeChart grafico =
        ChartFactory.createBarChart(
            "Distribuição de Formação Acadêmica",
            "Formação Acadêmica",
            "Número de Pessoas",
            categoria,
            PlotOrientation.VERTICAL,
            false,
            true,
            false);

    BarRenderer renderer = (BarRenderer) grafico.getCategoryPlot().getRenderer();
    for (int i = 0; i < tEscolaridades.length; i++) {
      renderer.setSeriesPaint(i, Color.BLUE);
    }

    ChartPanel chartPanel = new ChartPanel(grafico);
    chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(chartPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frame.getContentPane(), "Histograma", JOptionPane.PLAIN_MESSAGE);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static void pieEstadoCivil() {
    if (totalCadastrado <= 0) {
      JOptionPane.showMessageDialog(
          null, "Nenhuma pessoa cadastrada.", "Nenhuma Pessoa", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    DefaultPieDataset pizza = new DefaultPieDataset();
    pizza.setValue("Solteiro", dataset.percentEstadoCivil(EstadoCivil.SOLTEIRO));
    pizza.setValue("Casado", dataset.percentEstadoCivil(EstadoCivil.CASADO));
    pizza.setValue("Viúvo", dataset.percentEstadoCivil(EstadoCivil.VIUVO));
    pizza.setValue("Separado", dataset.percentEstadoCivil(EstadoCivil.SEPARADO));
    pizza.setValue("Divorciado", dataset.percentEstadoCivil(EstadoCivil.DIVORCIADO));

    JFreeChart pieChart =
        ChartFactory.createPieChart("Distribuição dos Estados Civis", pizza, true, true, false);

    PiePlot plot = (PiePlot) pieChart.getPlot();

    plot.setSectionPaint("Solteiro", new Color(135, 206, 250));
    plot.setSectionPaint("Casado", new Color(70, 130, 180));
    plot.setSectionPaint("Viúvo", new Color(0, 191, 255));
    plot.setSectionPaint("Separado", new Color(100, 149, 237));
    plot.setSectionPaint("Divorciado", new Color(72, 61, 139));

    pieChart.setBackgroundPaint(Color.WHITE);
    ChartPanel chartPanel = new ChartPanel(pieChart);
    chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(chartPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frame.getContentPane(), "Gráfico de Estados Civis", JOptionPane.PLAIN_MESSAGE);
  }
}
