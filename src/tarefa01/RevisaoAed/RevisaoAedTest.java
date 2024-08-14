package tarefa01.RevisaoAed;

public class RevisaoAedTest {
  public static void main(String[] args) {

    int numero = Integer.parseInt(args[0]);

    RevisaoAed revisaoAed = new RevisaoAed();

    System.out.println(revisaoAed.fibLoop(numero));
    System.out.println(revisaoAed.ficRec(numero));

  }
  
}
