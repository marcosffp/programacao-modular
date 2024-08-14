package tarefa01.RevisaoAed;

public class RevisaoAed {

  public RevisaoAed() {
  }
  



  public int fibLoop(int numerosTermos) {

    int a = 0;
    int b = 1;
    int c = 0;

    if (numerosTermos < 0) {
      return 0;
    }
    else if (numerosTermos == 1 || numerosTermos == 2) {
      return 1;
    } 
    else {
      for (int i = 1; i < numerosTermos; i++) {
        c = a + b;
        a = b;
        b = c;
      }
      return c;
   }
  }

  

  public int ficRec(int numerosTermos) {
    if (numerosTermos<0) {
      return 0;
    }
    if (numerosTermos == 1) {
      return 1;
    }
    if (numerosTermos == 2) {
      return 1;
    }
    return ficRec(numerosTermos - 2) + ficRec(numerosTermos - 1);
  }
}
