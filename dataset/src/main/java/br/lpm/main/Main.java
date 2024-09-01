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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Main {
  public static Dataset dataset = new Dataset();
  private static int totalCadastrado = 0;
  private static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public static void main(String[] args) throws Exception {
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
          histogramaFormacaoAcadêmica();
          pieEstadoCivil();
          break;
        case 3:
          pesquisarPessoa();
          break;
        case 4:
          mostrarEstatisticas();
          break;
        case 5:
          removerPessoa();
          break;
        case 6:
          substituirPessoa();
          break;
        case 7:
          sairDoSistema();
          break;
        default:
          break;
      }

    } while (opcaoMenu != 7);
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
      "Gráficos",
      "Pesquisar Pessoa",
      "Mostrar EstatÍsticas",
      "Remover Pessoa",
      "Substituir Pessoa",
      "Sair do sistema"
    };
  }

  private static void cadastrarPessoas() {
    if (totalCadastrado == Dataset.getMaxPessoas()) {
      JOptionPane.showMessageDialog(
          null,
          "Capacidade máxima atingida. Não é possível cadastrar mais pessoas.",
          "Aviso",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    int numeroCadastro =
        Integer.parseInt(JOptionPane.showInputDialog(null, "Quantas pessoas serão cadastradas? "));

    if (numeroCadastro <= 0) {
      JOptionPane.showMessageDialog(
          null,
          "Número de pessoas deve ser maior que zero. Operação cancelada.",
          "Erro",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    for (int i = 0; i < numeroCadastro; i++) {

      JOptionPane.showMessageDialog(
          null,
          "Cadastro n°" + (totalCadastrado + 1),
          "Cadastro em Andamento",
          JOptionPane.INFORMATION_MESSAGE);

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
          "O cadastro da pessoa nº "
              + totalCadastrado
              + " foi concluído com sucesso!\nNome: "
              + nome,
          "Cadastro Concluído",
          JOptionPane.INFORMATION_MESSAGE);
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
          null,
          "A opção selecionada é inválida. Por favor, escolha uma opção válida",
          "Seleção Inválida",
          JOptionPane.ERROR_MESSAGE);
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
          null,
          "A opção selecionada é inválida. Por favor, escolha uma opção válida",
          "Seleção Inválida",
          JOptionPane.ERROR_MESSAGE);
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
          null,
          "A opção selecionada é inválida. Por favor, escolha uma opção válida",
          "Seleção Inválida",
          JOptionPane.ERROR_MESSAGE);
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
          null,
          "A opção selecionada é inválida. Por favor, escolha uma opção válida",
          "Seleção Inválida",
          JOptionPane.ERROR_MESSAGE);
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
          null,
          "A opção selecionada é inválida. Por favor, escolha uma opção válida",
          "Seleção Inválida",
          JOptionPane.ERROR_MESSAGE);
      return solicitarMoradia();
    }
  }

  private static void listarPessoas() {
    if (totalCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " visualizar a lista.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
 
    Pessoa[] pessoas = dataset.getAll();
    String listarPessoas = "";
    for (int i = 0; i < totalCadastrado; i++) {
      listarPessoas += pessoas[i].toString() + "\n";
    }
    JOptionPane.showMessageDialog(
        null, listarPessoas, "Lista de Pessoas Cadastradas", JOptionPane.INFORMATION_MESSAGE);
  }

  

  public static void histogramaFormacaoAcadêmica() {
    if (totalCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " visualizar o histograma.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    Pessoa[] listaPessoas = dataset.getAll();
    Escolaridade[] todasEscolaridades = Escolaridade.values();
    int[] contagemEscolaridades = new int[Escolaridade.values().length];

    for (int i = 0; i < totalCadastrado; i++) {
      Escolaridade escolaridade = listaPessoas[i].getEscolaridade();
      for (int j = 0; j < contagemEscolaridades.length; j++) {
        if (todasEscolaridades[j] == escolaridade) {
          contagemEscolaridades[j]++;
        }
      }
    }

    DefaultCategoryDataset datasetGrafico = new DefaultCategoryDataset();
    for (int i = 0; i < contagemEscolaridades.length; i++) {
      datasetGrafico.addValue(
          contagemEscolaridades[i], "Formação Acadêmica", todasEscolaridades[i].name());
    }

    JFreeChart grafico =
        ChartFactory.createBarChart(
            "Distribuição de Formação Acadêmica",
            "Formação Acadêmica",
            "Número de Pessoas",
            datasetGrafico,
            PlotOrientation.VERTICAL,
            false,
            true,
            false);

    BarRenderer renderizador = (BarRenderer) grafico.getCategoryPlot().getRenderer();
    for (int i = 0; i < todasEscolaridades.length; i++) {
      renderizador.setSeriesPaint(i, Color.BLUE);
    }

    ChartPanel painelGrafico = new ChartPanel(grafico);
    painelGrafico.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frameGrafico = new JFrame();
    frameGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameGrafico.add(painelGrafico);
    frameGrafico.pack();
    frameGrafico.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frameGrafico.getContentPane(), "Histograma", JOptionPane.PLAIN_MESSAGE);
  }

  public static void pieEstadoCivil() {
    if (totalCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " visualizar a pizza.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    DefaultPieDataset<String> pizza = new DefaultPieDataset<>();
    pizza.setValue("Solteiro", dataset.percentEstadoCivil(EstadoCivil.SOLTEIRO));
    pizza.setValue("Casado", dataset.percentEstadoCivil(EstadoCivil.CASADO));
    pizza.setValue("Viúvo", dataset.percentEstadoCivil(EstadoCivil.VIUVO));
    pizza.setValue("Separado", dataset.percentEstadoCivil(EstadoCivil.SEPARADO));
    pizza.setValue("Divorciado", dataset.percentEstadoCivil(EstadoCivil.DIVORCIADO));

    JFreeChart pieChart =
        ChartFactory.createPieChart("Distribuição dos Estados Civis", pizza, true, true, false);

    pieChart.setBackgroundPaint(Color.WHITE);
    ChartPanel chartPanel = new ChartPanel(pieChart);
    chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(chartPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frame.getContentPane(), "Pie Chart", JOptionPane.PLAIN_MESSAGE);
  }

  private static void pesquisarPessoa() {
    if (totalCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " pesquisar pessoa.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String nome =
        JOptionPane.showInputDialog(
            null,
            "Digite o nome da pessoa para pesquisa:",
            "Pesquisar Pessoa",
            JOptionPane.QUESTION_MESSAGE);
    Pessoa pessoa = dataset.getPessoaByName(nome);

    if (pessoa != null) {
      JOptionPane.showMessageDialog(
          null, pessoa.toString(), "Pessoa Encontrada", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          null, "Pessoa "+nome+" não encontrada.", "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private static void mostrarEstatisticas() {
    if (totalCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " visualizar as estatísticas.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String estatisticas =
        "**************************************************************\n"
            + "Número de pessoas cadastradas: "
            + totalCadastrado
            + "\n";
    estatisticas += dataset.toString();

    JOptionPane.showMessageDialog(
        null, estatisticas, "Estatísticas", JOptionPane.INFORMATION_MESSAGE);
  }

  private static void removerPessoa() {
    if (totalCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " visualizar o histograma.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa a ser removida:");
    dataset.removePessoaByName(nome);
    totalCadastrado--;
    JOptionPane.showMessageDialog(
        null, "Pessoa "+nome+" removida com sucesso!", "Remover Pessoa", JOptionPane.INFORMATION_MESSAGE);
  }

  private static void substituirPessoa() {
    if (totalCadastrado == 0) {
           JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " visualizar o histograma.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String nomeAntigo =
        JOptionPane.showInputDialog(null, "Digite o nome da pessoa a ser substituída:");
    Pessoa pessoaAntiga = dataset.getPessoaByName(nomeAntigo);
    if (pessoaAntiga == null) {
      JOptionPane.showMessageDialog(
          null, "Pessoa "+nomeAntigo +" não encontrada.", "Substituir Pessoa", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    JOptionPane.showMessageDialog(null, "Substituindo pessoa: " + pessoaAntiga.toString());

    String nomeNovo = solicitarNome();
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

    Pessoa novaPessoa =
        new Pessoa(
            nomeNovo,
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

    dataset.replacePessoa(pessoaAntiga, novaPessoa);
    JOptionPane.showMessageDialog(
        null,
        "Pessoa substituída com sucesso!",
        "Substituir Pessoa",
        JOptionPane.INFORMATION_MESSAGE);
  }
}
