package br.lpm.business;

public class Knn {
  private int k;
  private Dataset dataset;

  public Knn(int k, Dataset dataset) {
    this.k = k;
    this.dataset = dataset;
  }

  public boolean classifyFeliz(Pessoa pessoa) {
    Pessoa[] vizinhos = dataset.getSimilar(pessoa, k);

    int felizes = 0;
    int tristes = 0;

    for (Pessoa vizinho : vizinhos) {
      if (vizinho.isFeliz() && vizinho != null) {
        felizes++;
      } else {
        tristes++;
      }
    }
    return felizes > tristes;
  }
}
