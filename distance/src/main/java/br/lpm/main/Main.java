package br.lpm.main;

import br.lpm.business.Dataset;
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
  private static int totalUsuariosCadastrado = 0;
  private static DateTimeFormatter dataFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  public static Dataset dataset=new Dataset();

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
              menuOpcoes(),
              menuOpcoes()[0]);
      switch (opcaoMenu) {
        case 0:
          cadastrarPessoa();
          break;
        case 1:
          listarPessoas();
          break;
        case 2:
          histogramaFormacaoAcadêmica();
          gerarGraficoPizzaEstadoCivil();
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
          pesquisarPessoaSimilar();
          break;
        case 8:
          sairDoSistema();
          break;
        default:
          break;
      }

    } while (opcaoMenu != 8);
  }

  private static void sairDoSistema() {
    JOptionPane.showMessageDialog(
        null, "Saindo do sistema", "Sair", JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
  }

  private static String[] menuOpcoes() {
    return new String[] {
      "Cadastrar Pessoa",
      "Listar Pessoas Cadastradas",
      "Gerar Gráficos",
      "Pesquisar Pessoa",
      "Mostrar Estatísticas",
      "Remover Pessoa",
      "Substituir Pessoa",
      "Pesquisar Similares",
      "Sair do Sistema"
    };
  }

  private static void cadastrarPessoa() {
    if (totalUsuariosCadastrado == Dataset.getMaxPessoas()) {
      JOptionPane.showMessageDialog(
          null,
          "Capacidade máxima atingida. Não é possível cadastrar mais pessoas.",
          "Aviso",
          JOptionPane.WARNING_MESSAGE);
      return;
    }
    int quantidadePessoasParaRegistrar =
        Integer.parseInt(JOptionPane.showInputDialog(null, "Quantas pessoas serão cadastradas? "));
    if (quantidadePessoasParaRegistrar <= 0) {
      JOptionPane.showMessageDialog(
          null,
          "Número de pessoas deve ser maior que zero. Operação cancelada.",
          "Erro",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    for (int i = 0; i < quantidadePessoasParaRegistrar; i++) {
      JOptionPane.showMessageDialog(
          null,
          "Cadastro n°" + (totalUsuariosCadastrado + 1),
          "Cadastro em Andamento",
          JOptionPane.INFORMATION_MESSAGE);
      String nome = obterNome();
      LocalDate dataNascimento = obterDataNascimento();
      Genero genero = obterGenero();
      float altura = obterAltura();
      int peso = obterPeso();
      float renda = obterRenda();
      String naturalidade = obterNaturalidade();
      Hobby hobby = obterHobby();
      EstadoCivil estadoCivil = obterEstadoCivil();
      Escolaridade escolaridade = obterEscolaridade();
      boolean feliz = obterFeliz();
      Moradia moradia = obterMoradia();
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
      totalUsuariosCadastrado++;
      JOptionPane.showMessageDialog(
          null,
          "O cadastro da pessoa nº "
              + totalUsuariosCadastrado
              + " foi concluído com sucesso!\nNome: "
              + nome,
          "Cadastro Concluído",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private static LocalDate obterDataNascimento() {
    Pessoa pessoa = new Pessoa();
    String dataNascimentoString;
    LocalDate dataNascimento = null;
    do {
      dataNascimentoString =
          JOptionPane.showInputDialog(
              null,
              "Digite a data de nascimento (DD/MM/AAAA): ",
              "Cadastro n°" + (totalUsuariosCadastrado + 1),
              JOptionPane.QUESTION_MESSAGE);
      if (dataNascimentoString != null && !dataNascimentoString.isEmpty()) {
        dataNascimento = LocalDate.parse(dataNascimentoString, dataFormatador);
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

  private static int obterPeso() {
    int peso;
    Pessoa pessoa = new Pessoa();
    do {
      peso =
          Integer.parseInt(
              JOptionPane.showInputDialog(
                  null,
                  "Digite o peso: ",
                  "Cadastro n°" + (totalUsuariosCadastrado + 1),
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

  private static Float obterAltura() {
    return validarEntradaFloat(
        "Digite a altura: ",
        "Altura inválida. Insira um valor entre 1 e 2.59 metros.",
        1f,
        2.59f,
        false);
  }

  private static Float obterRenda() {
    return validarEntradaFloat(
        "Digite a renda: ", "Renda inválida. A renda deve ser um valor positivo.", 0f, 0f, true);
  }

  private static Float validarEntradaFloat(
      String mensagemSolicitacao,
      String mensagemErro,
      float valorMinimo,
      float valorMaximo,
      boolean semValorMaximo) {
    float valorEntrada;
    Pessoa pessoa = new Pessoa();
    do {
      valorEntrada =
          Float.parseFloat(
              JOptionPane.showInputDialog(
                  null,
                  mensagemSolicitacao,
                  "Cadastro n°" + (totalUsuariosCadastrado + 1),
                  JOptionPane.QUESTION_MESSAGE));

      if (semValorMaximo) {
        pessoa.setRenda(valorEntrada);
        if (pessoa.getRenda() < valorMinimo) {
          JOptionPane.showMessageDialog(null, mensagemErro, "Erro", JOptionPane.ERROR_MESSAGE);
        }
      } else {
        pessoa.setAltura(valorEntrada);
        if (pessoa.getAltura() < valorMinimo || pessoa.getAltura() > valorMaximo) {
          JOptionPane.showMessageDialog(null, mensagemErro, "Erro", JOptionPane.ERROR_MESSAGE);
        }
      }
    } while ((!semValorMaximo
            && (pessoa.getAltura() < valorMinimo || pessoa.getAltura() > valorMaximo))
        || (semValorMaximo && pessoa.getRenda() < valorMinimo));

    return semValorMaximo ? pessoa.getRenda() : pessoa.getAltura();
  }

  private static String obterNome() {
    return validarEntradaString("nome");
  }

  private static String obterNaturalidade() {
    return validarEntradaString("naturalidade");
  }

  private static String validarEntradaString(String tipoInformacao) {
    String entradaInformacao;
    Pessoa pessoa = new Pessoa();
    do {
      entradaInformacao =
          JOptionPane.showInputDialog(
              null,
              "Digite o " + tipoInformacao + ":",
              "Cadastro n°" + (totalUsuariosCadastrado + 1),
              JOptionPane.QUESTION_MESSAGE);
      if (pessoa.isStringValido(entradaInformacao)) {
        if ("nome".equals(tipoInformacao)) {
          pessoa.setNome(entradaInformacao);
        } else if ("naturalidade".equals(tipoInformacao)) {
          pessoa.setNaturalidade(entradaInformacao);
        }
      } else {
        JOptionPane.showMessageDialog(
            null,
            "inválido. Por favor, insira um texto contendo apenas letras e espaços.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
      }
    } while (!pessoa.isStringValido(entradaInformacao));
    return entradaInformacao;
  }

  private static Genero obterGenero() {
    return (Genero) exibirOpcoesEnum(Genero.values(), "Escolha o gênero:");
  }

  private static Hobby obterHobby() {
    return (Hobby) exibirOpcoesEnum(Hobby.values(), "Escolha o hobby:");
  }

  private static EstadoCivil obterEstadoCivil() {
    return (EstadoCivil) exibirOpcoesEnum(EstadoCivil.values(), "Escolha o estado civil:");
  }

  private static Escolaridade obterEscolaridade() {
    return (Escolaridade) exibirOpcoesEnum(Escolaridade.values(), "Escolha a escolaridade:");
  }

  private static Moradia obterMoradia() {
    return (Moradia) exibirOpcoesEnum(Moradia.values(), "Escolha a moradia:");
  }

  private static boolean obterFeliz() {
    int opcaoFeliz;
    do {
      opcaoFeliz =
          JOptionPane.showConfirmDialog(
              null,
              "Você é feliz?",
              "Cadastro n°" + (totalUsuariosCadastrado + 1),
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

  public static void histogramaFormacaoAcadêmica() {
    if (totalUsuariosCadastrado == 0) {
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

    for (int i = 0; i < totalUsuariosCadastrado; i++) {
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

  public static void gerarGraficoPizzaEstadoCivil() {
    if (totalUsuariosCadastrado == 0) {
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

    JFreeChart grafico = ChartFactory.createPieChart(
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

  private static void listarPessoas() {
    if (totalUsuariosCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " visualizar a lista.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    JTextArea areaTexto = new JTextArea();
    areaTexto.setEditable(false);
    areaTexto.setLineWrap(true);

    StringBuilder sb = new StringBuilder();
    Pessoa[] pessoas = dataset.getAll();
    for (int i = 0; i < totalUsuariosCadastrado; i++) {
      sb.append(pessoas[i].toString()).append("\n\n");
    }
    areaTexto.setText(sb.toString());

    JScrollPane painelRolagem = new JScrollPane(areaTexto);
    painelRolagem.setPreferredSize(new Dimension(400, 400));

    JDialog janelaDialogo = new JDialog((Frame) null, "Lista de Pessoas Cadastradas", true);
    janelaDialogo.getContentPane().add(painelRolagem, BorderLayout.CENTER);
    janelaDialogo.pack();
    janelaDialogo.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, janelaDialogo.getContentPane(), "Lista de Pessoas Cadastradas", JOptionPane.PLAIN_MESSAGE);
  }

  private static void pesquisarPessoa() {
    if (totalUsuariosCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " pesquisar pessoa.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    String nomePessoa =
        JOptionPane.showInputDialog(
            null,
            "Digite o nome da pessoa para pesquisa:",
            "Pesquisar Pessoa",
            JOptionPane.QUESTION_MESSAGE);
    Pessoa pessoaEncontrada = dataset.getPessoaByName(nomePessoa);
    if (pessoaEncontrada != null) {
      JOptionPane.showMessageDialog(
          null, pessoaEncontrada.toString(), "Pessoa Encontrada", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          null,
          "Pessoa " + nomePessoa + " não encontrada.",
          "Resultado da Pesquisa",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private static void mostrarEstatisticas() {
    if (totalUsuariosCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " visualizar as estatísticas.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String relatorioEstatisticas =
        "**************************************************************\n"
            + "Número de pessoas cadastradas: "
            + totalUsuariosCadastrado;
    relatorioEstatisticas += dataset.toString();

    JOptionPane.showMessageDialog(
        null, relatorioEstatisticas, "Estatísticas", JOptionPane.INFORMATION_MESSAGE);
  }

  private static void removerPessoa() {
    if (totalUsuariosCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " remover pessoa.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    String nomePessoa =
        JOptionPane.showInputDialog(null, "Digite o nome da pessoa a ser removida:");
    dataset.removePessoaByName(nomePessoa);
    totalUsuariosCadastrado--;
    JOptionPane.showMessageDialog(
        null,
        "Pessoa " + nomePessoa + " removida com sucesso!",
        "Remover Pessoa",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private static void substituirPessoa() {
    if (totalUsuariosCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " substituir pessoa.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    String nomePessoaAntiga =
        JOptionPane.showInputDialog(null, "Digite o nome da pessoa a ser substituída:");
    Pessoa pessoaAtual = dataset.getPessoaByName(nomePessoaAntiga);
    if (pessoaAtual == null) {
      JOptionPane.showMessageDialog(
          null,
          "Pessoa " + nomePessoaAntiga + " não encontrada.",
          "Substituir Pessoa",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    JOptionPane.showMessageDialog(null, "Substituindo pessoa: " + pessoaAtual.toString());
    String nomeNovo = obterNome();
    LocalDate dataNascimento = obterDataNascimento();
    Genero genero = obterGenero();
    float altura = obterAltura();
    int peso = obterPeso();
    float renda = obterRenda();
    String naturalidade = obterNaturalidade();
    Hobby hobby = obterHobby();
    EstadoCivil estadoCivil = obterEstadoCivil();
    Escolaridade escolaridade = obterEscolaridade();
    boolean feliz = obterFeliz();
    Moradia moradia = obterMoradia();
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
    dataset.replacePessoa(pessoaAtual, novaPessoa);
    JOptionPane.showMessageDialog(
        null,
        "Pessoa substituída com sucesso!",
        "Substituir Pessoa",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private static void pesquisarPessoaSimilar() {
    if (totalUsuariosCadastrado == 0) {
      JOptionPane.showMessageDialog(
          null,
          "Não há pessoas cadastradas no sistema. Por favor, cadastre algumas pessoas para"
              + " pesquisar pessoa.",
          "Nenhum Cadastro Encontrado",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    String nomePessoa =
        JOptionPane.showInputDialog(
            null,
            "Digite o nome da pessoa para pesquisa:",
            "Pesquisar Pessoa",
            JOptionPane.QUESTION_MESSAGE);

    if (nomePessoa == null || nomePessoa.isEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "Nome inválido. Por favor, insira um nome válido.",
          "Erro",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    String quantidadePessoa =
        JOptionPane.showInputDialog(
            null,
            "Digite a quantidade de pessoas mais similares:",
            "Pesquisar Pessoa",
            JOptionPane.QUESTION_MESSAGE);

    int n = Integer.parseInt(quantidadePessoa);
    if (n <= 0||n>totalUsuariosCadastrado) {
      JOptionPane.showMessageDialog(
          null,
          "Quantidade inválida.",
          "Erro",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    Pessoa pessoa = dataset.getPessoaByName(nomePessoa);
    if (pessoa == null) {
      JOptionPane.showMessageDialog(
          null, "Pessoa não encontrada no sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Pessoa[] pessoasSimilares = dataset.getSimilar(pessoa, n);
    StringBuilder listaPessoa = new StringBuilder();
    for (int i = 0; i < pessoasSimilares.length; i++) {
      listaPessoa.append(pessoasSimilares[i].toString()).append("\n");
    }
    JOptionPane.showMessageDialog(
        null, listaPessoa.toString(), "Pessoas Similares", JOptionPane.INFORMATION_MESSAGE);
  }

  private static Enum<?> exibirOpcoesEnum(Enum<?>[] opcoesEnum, String mensagem) {
    String[] nomesOpcoes = new String[opcoesEnum.length];
    for (int i = 0; i < opcoesEnum.length; i++) {
      nomesOpcoes[i] = opcoesEnum[i].name();
    }
    int indiceEscolha =
        JOptionPane.showOptionDialog(
            null,
            mensagem,
            "Cadastro",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            nomesOpcoes,
            nomesOpcoes[0]);
    if (indiceEscolha >= 0 && indiceEscolha < opcoesEnum.length) {
      return opcoesEnum[indiceEscolha];
    } else {
      JOptionPane.showMessageDialog(
          null,
          "A opção selecionada é inválida. Por favor, escolha uma opção válida.",
          "Seleção Inválida",
          JOptionPane.ERROR_MESSAGE);
      return exibirOpcoesEnum(opcoesEnum, mensagem);
    }
  }

  
}
