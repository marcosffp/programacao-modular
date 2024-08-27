package feer;

public class Produto {
  private int id;
  private String descricao;
  private float preco;
  private int quant;
  
  private static int cont = 0;

  public static int getCont() {
    return cont;
  }

  public int getId() {
    return id;
  }

  public Produto(String d, float p, int q) {
    // Inicialização do produto
    id = ++cont;
  }

  public Produto() {
    descricao = "Novo Produto";
    preco = 0.01F;
    quant = 0;
    id = ++cont;
  }
}
