package br.lpm.main;

import br.lpm.business.Dataset;
import br.lpm.business.DistanceMeasure;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Main {
  private static int totalCadastrado = 0;
  private static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static DistanceMeasure distanceMeasure;
  public static Dataset dataset;

  public static void main(String[] args) throws Exception {
    int opcaoMenu;

    do {
      opcaoMenu = JOptionPane.showOptionDialog(
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
          pesquisarSimiliar();
          break;
        case 8:
          sairDoSistema();
          break;
        default:
          break;
      }

    } while (opcaoMenu != 8);
  }
  
   static {
    distanceMeasure = new DistanceMeasure(null); 
    dataset = new Dataset(distanceMeasure);
    distanceMeasure = new DistanceMeasure(dataset);
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
      "Pesquisar Similares",
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
    String nome;
    Pessoa pessoa = new Pessoa();
    do {
      nome =
          JOptionPane.showInputDialog(
              null,
              "Digite o nome:",
              "Cadastro n°" + (totalCadastrado + 1),
              JOptionPane.QUESTION_MESSAGE);
      pessoa.setNome(nome);
      if (pessoa.getNome() == null) {
        JOptionPane.showMessageDialog(
            null,
            "Nome inválido. Por favor, insira um nome contendo apenas letras e espaços.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
      }

    } while (pessoa.getNome() == null);
    return pessoa.getNome();
  }

  private static LocalDate solicitarDataNascimento() {
    Pessoa pessoa = new Pessoa();
    String dataNascimentoString;
    LocalDate dataNascimento = null;

    do {
      dataNascimentoString =
          JOptionPane.showInputDialog(
              null,
              "Digite a data de nascimento (DD/MM/AAAA): ",
              "Cadastro n°" + (totalCadastrado + 1),
              JOptionPane.QUESTION_MESSAGE);

      if (dataNascimentoString != null && !dataNascimentoString.isEmpty()) {
        dataNascimento = LocalDate.parse(dataNascimentoString, formatadorData);
        pessoa.setDataNascimento(dataNascimento);

        if (pessoa.getDataNascimento() == null) {
          JOptionPane.showMessageDialog(
              null,
              "Data de nascimento inválida. A data deve ser anterior à data atual.",
              "Erro",
              JOptionPane.ERROR_MESSAGE);
        }
      }

    } while (pessoa.getDataNascimento() == null); 

    return pessoa.getDataNascimento();
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
    float altura;
    Pessoa pessoa = new Pessoa();
    do {
      altura=    Float.parseFloat(
        JOptionPane.showInputDialog(
            null,
            "Digite a altura: ",
            "Cadastro n°" + (totalCadastrado + 1),
            JOptionPane.QUESTION_MESSAGE));
      pessoa.setAltura(altura);
      if (pessoa.getAltura()==0) {

        JOptionPane.showMessageDialog(
            null,
            "Altura inválida. Insira um valor entre 1 e 2.59 metros.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
      }
    } while (pessoa.getAltura() == 0);
    return pessoa.getAltura();
  }

  private static int solicitarPeso() {
    int peso;
    Pessoa pessoa = new Pessoa();
    do {
      peso =
          Integer.parseInt(
              JOptionPane.showInputDialog(
                  null,
                  "Digite o peso: ",
                  "Cadastro n°" + (totalCadastrado + 1),
                  JOptionPane.QUESTION_MESSAGE));
      pessoa.setPeso(peso);
      if (pessoa.getPeso() == 0) {

        JOptionPane.showMessageDialog(
            null,
            "Peso inválido. Insira um valor entre 1 e 599 quilogramas.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
      }
    } while (pessoa.getPeso() == 0);
    return pessoa.getPeso();
  }

  private static float solicitarRenda() {
    float renda;
    Pessoa pessoa = new Pessoa();
    do {
      renda =
          Float.parseFloat(
              JOptionPane.showInputDialog(
                  null,
                  "Digite a renda: ",
                  "Cadastro n°" + (totalCadastrado + 1),
                  JOptionPane.QUESTION_MESSAGE));
      pessoa.setRenda(renda);
      if (pessoa.getRenda() < 0) {

        JOptionPane.showMessageDialog(
            null,
            "Renda inválida. A renda deve ser um valor positivo.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
      }
    } while (pessoa.getRenda()< 0);
    return pessoa.getRenda();
  }

  private static String solicitarNaturalidade() {
    String naturalidade;
    Pessoa pessoa = new Pessoa();
    do {
      naturalidade =
          JOptionPane.showInputDialog(
        null,
        "Digite a naturalidade:",
        "Cadastro n°" + (totalCadastrado + 1),
        JOptionPane.QUESTION_MESSAGE);
      pessoa.setNaturalidade(naturalidade);
      if (pessoa.getNaturalidade() == null) {
        JOptionPane.showMessageDialog(
            null,
            "Naturalidade inválida. Por favor, insira um nome contendo apenas letras e espaços.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
      }

    } while (pessoa.getNaturalidade() == null);
    return pessoa.getNaturalidade();
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
    int opcaoFeliz;
    do {
      opcaoFeliz =
          JOptionPane.showConfirmDialog(
              null,
              "Você é feliz?",
              "Cadastro n°" + (totalCadastrado + 1),
              JOptionPane.YES_NO_OPTION);
      if (opcaoFeliz == JOptionPane.CLOSED_OPTION) {
        JOptionPane.showMessageDialog(
            null,
            "Por favor, responda à pergunta.",
            "Resposta Necessária",
            JOptionPane.WARNING_MESSAGE);
      }
    } while (opcaoFeliz == JOptionPane.CLOSED_OPTION);

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

    JTextArea textArea = new JTextArea();
    textArea.setEditable(false); 
    textArea.setLineWrap(true); 
    textArea.setWrapStyleWord(true); 

    StringBuilder sb = new StringBuilder();
    Pessoa[] pessoas = dataset.getAll();
    for (int i = 0; i < totalCadastrado; i++) {
      sb.append(pessoas[i].toString())
          .append("\n\n"); 
    }
    textArea.setText(sb.toString());

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(
        new Dimension(500, 400)); 

    JDialog dialog = new JDialog((Frame) null, "Lista de Pessoas Cadastradas", true);
    dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
    dialog.pack(); 
    dialog.setLocationRelativeTo(null); 
    dialog.setVisible(true);
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
            true,
            true,
            false);

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

    DefaultPieDataset<String> datasetGrafico = new DefaultPieDataset<>();
    datasetGrafico.setValue("Solteiro", dataset.percentEstadoCivil(EstadoCivil.SOLTEIRO));
    datasetGrafico.setValue("Casado", dataset.percentEstadoCivil(EstadoCivil.CASADO));
    datasetGrafico.setValue("Viúvo", dataset.percentEstadoCivil(EstadoCivil.VIUVO));
    datasetGrafico.setValue("Separado", dataset.percentEstadoCivil(EstadoCivil.SEPARADO));
    datasetGrafico.setValue("Divorciado", dataset.percentEstadoCivil(EstadoCivil.DIVORCIADO));

    JFreeChart grafico =
        ChartFactory.createPieChart(
            "Distribuição dos Estados Civis", datasetGrafico, true, true, false);

    ChartPanel painelGrafico = new ChartPanel(grafico);
    painelGrafico.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frameGrafico = new JFrame();
    frameGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameGrafico.add(painelGrafico);
    frameGrafico.pack();
    frameGrafico.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frameGrafico.getContentPane(), "Pie Chart", JOptionPane.PLAIN_MESSAGE);
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
          null,
          "Pessoa " + nome + " não encontrada.",
          "Resultado da Pesquisa",
          JOptionPane.INFORMATION_MESSAGE);
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
            + totalCadastrado;
    estatisticas += dataset.toString();

    JOptionPane.showMessageDialog(
        null, estatisticas, "Estatísticas", JOptionPane.INFORMATION_MESSAGE);
  }

  private static void removerPessoa() {
    if (totalCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " remover pessoa.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa a ser removida:");
    dataset.removePessoaByName(nome);
    totalCadastrado--;
    JOptionPane.showMessageDialog(
        null,
        "Pessoa " + nome + " removida com sucesso!",
        "Remover Pessoa",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private static void substituirPessoa() {
    if (totalCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " substituir pessoa.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String nomeAntigo =
        JOptionPane.showInputDialog(null, "Digite o nome da pessoa a ser substituída:");
    Pessoa pessoaAntiga = dataset.getPessoaByName(nomeAntigo);
    if (pessoaAntiga == null) {
      JOptionPane.showMessageDialog(
          null,
          "Pessoa " + nomeAntigo + " não encontrada.",
          "Substituir Pessoa",
          JOptionPane.INFORMATION_MESSAGE);
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

    Pessoa novaPessoa = new Pessoa(
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
  
private static void pesquisarSimiliar() {
    if (totalCadastrado == 0) {
        JOptionPane.showMessageDialog(
            null,
            "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
                + " pesquisar pessoa.",
            "Nenhum Cadastro Encontrado",
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    String nome = JOptionPane.showInputDialog(
        null,
        "Digite o nome da pessoa para pesquisa:",
        "Pesquisar Pessoa",
        JOptionPane.QUESTION_MESSAGE);

    if (nome == null || nome.trim().isEmpty()) {
        JOptionPane.showMessageDialog(
            null,
            "Nome inválido. Por favor, insira um nome válido.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    String quantidadeStr = JOptionPane.showInputDialog(
        null,
        "Digite a quantidade de pessoas mais similares:",
        "Pesquisar Pessoa",
        JOptionPane.QUESTION_MESSAGE);


    int n = Integer.parseInt(quantidadeStr);
    if (n <= 0) {
        JOptionPane.showMessageDialog(
            null,
            "Quantidade inválida. Por favor, insira um número inteiro positivo.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    Pessoa pessoa = dataset.getPessoaByName(nome);

    if (pessoa == null) {
        JOptionPane.showMessageDialog(
            null,
            "Pessoa não encontrada no sistema.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    Pessoa[] pessoasSimilares = dataset.getSimilar(pessoa, n);
    StringBuilder lista = new StringBuilder();
    for (Pessoa p : pessoasSimilares) {
        lista.append(p.toString()).append("\n");
    }

    JOptionPane.showMessageDialog(
        null,
        lista.toString(),
        "Pessoas Similares",
        JOptionPane.INFORMATION_MESSAGE);
}

}
