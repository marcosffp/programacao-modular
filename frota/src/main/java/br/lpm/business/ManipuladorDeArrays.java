package br.lpm.business;

public class ManipuladorDeArrays {
  public static int adicionar(Object object, Object[] vetor, int numObjetos, int MAX_OBJETOS) {
    if (numObjetos >= MAX_OBJETOS || object == null) {
      return numObjetos;
    }
    for (int i = 0; i < numObjetos; i++) {
      if (vetor[i].equals(object)) {
        return numObjetos;
      }
    }
    vetor[numObjetos] = object;
    return numObjetos + 1;
  }

  public static int remover(Object object, Object[] vetor, int numObjetos, int MAX_OBJETOS) {
    for (int i = 0; i < numObjetos; i++) {
      if (vetor[i].equals(object)) {
        executarRemocao(i, numObjetos, vetor);
        return numObjetos - 1;
      }
    }
    return numObjetos;
  }

  private static void executarRemocao(int i, int numObjetos, Object[] vetor) {
    for (int j = i; j < numObjetos - 1; j++) {
      vetor[j] = vetor[j + 1];
    }
    vetor[numObjetos - 1] = null;
  }

  public static void replace(Object velho, Object novo, Object[] vetor) {
    for (int i = 0; i < vetor.length; i++) {
      if (vetor[i] != null && vetor[i].equals(novo)) {
        return;
      }
      if (vetor[i] != null && vetor[i].equals(velho)) {
        vetor[i] = novo;
        return;
      }
    }
  }
}
