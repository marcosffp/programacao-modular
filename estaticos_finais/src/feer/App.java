package feer;

public class App {
  public static void main(String args[]) {
    System.out.println("Cont. produtos: "+Produto.getCont());
    Produto p1 = new Produto();
    System.out.println("Cont. produtos: "+Produto.getCont());
    Produto p2 = new Produto("Shulambs", 1.99F, 200);
    System.out.println("Cont. produtos: "+Produto.getCont());

  
    System.out.println("Produto ID: " + p1.getId());
    System.out.println("Produto ID: " + p2.getId());
  }
}
