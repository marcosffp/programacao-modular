package br.lpm.business;


public class NotaFiscal {
  private String nomeCliente;
  private static int contador = 0;
  private int id;

  private static final int MAX_AUTOMOVEIS = 100;
  private static final int MAX_SERVICOS = 1000;
  private static final int MAX_PECAS = 1000;

  private int numAutomovel = 0;
  private int numServico = 0;
  private int numPeca = 0;

  private Automovel[] automoveis = new Automovel[NotaFiscal.MAX_AUTOMOVEIS];
  private Servico[] servicos = new Servico[NotaFiscal.MAX_SERVICOS];
  private Peca[] pecas = new Peca[NotaFiscal.MAX_PECAS];

  public NotaFiscal(String nomeCliente) {
    this.nomeCliente = nomeCliente;
    this.id = ++contador;
  }

  public void addAutomovel(Automovel automovel) {
    automoveis[numAutomovel++] = automovel;
  }

  public void addPeca(Peca peca) {
    pecas[numPeca++] = peca;
  }

  public void addServico(Servico servico) {
    servicos[numServico++] = servico;
  }

  public double calcularTotal() {
    double total = 0.0;
    total += numAutomovel + numPeca + numServico;
    return total;
  }

  public void exibirNota() {
    System.out.println("Nota fiscal");
    System.out.println("Nome do cliente: "+nomeCliente);
    System.out.println("id: "+id);

    for (int i = 0; i < numAutomovel; i++) {
      System.out.println(automoveis[i]);
    }

    System.out.println(
    );

    for (int i = 0; i < numPeca; i++) {
      System.out.println(pecas[i]);
    }
    System.out.println();
    for (int i = 0; i <numServico; i++) {
      System.out.println(servicos[i]);
    }
  
  }

  
}
