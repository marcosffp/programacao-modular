package tarefa01.RevisaoAed;

public class RevisaoAed {


  public RevisaoAed() {

  }


  public int fibLoop(int numerosTermos) {
    int a = 0;
    int b = 1;
    int c = 0;

      for (int i = 0; i < numerosTermos; i++) {
        c = a + b;
        a = b;
        b = c;
      }
      return c;
  }

  public int ficRec(int numerosTermos) {
    if (numerosTermos == 1 || numerosTermos == 0) {
      return 1;
    }
    return ficRec(numerosTermos - 2) + ficRec(numerosTermos - 1);
  }
}
