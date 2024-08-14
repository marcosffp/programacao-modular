package TestDeSoftware;

public class Conjunto {
  private int tamanho = 0;
  public static final int MAX = 10;
  private Object[] itens = new Object[MAX];

  public boolean contem(Object o) {
    for (int i = 0; i < MAX; i++) {
      if (itens[i] == o) {
        return true;
      }
    }
    return false;
  }

  public void adicionar(Object o) {
    for (int i = 0; i < MAX; i++) {
      if (!contem(o)) {
        itens[tamanho++] = o;
      }
    }
  }
  
  public int tamanho() {
    return tamanho;
  }

  
}

