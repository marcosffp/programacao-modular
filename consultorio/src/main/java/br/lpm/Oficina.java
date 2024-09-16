package br.lpm;

public class Oficina {
  private String nome;
  private String endereco;
  private static final int MAX_VEICULOS = 55;
  private Veiculo[] veiculos = new Veiculo[MAX_VEICULOS];
  private int numVeiculos = 0;
  public Oficina(String nome, String endereco) {
    this.nome = nome;
    this.endereco = endereco;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getEndereco() {
    return endereco;
  }
  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }
  public static int getMaxVeiculos() {
    return MAX_VEICULOS;
  }

  public void addVeiculo(Veiculo veiculo) {
    if (!(numVeiculos < MAX_VEICULOS)) {
      return;
    }
    for (int i = 0; i < numVeiculos; i++) {
      if (veiculos[i].equals(veiculo)) {
        return;
      }
    }
    veiculos[numVeiculos++] = veiculo;
  }

  public void removeVeiculo(Veiculo veiculo) {
    for (int i = 0; i < numVeiculos; i++) {
      if (veiculos[i].equals(veiculo)) {
        for (int j = i; j < numVeiculos-1; j++) {
          veiculos[j] = veiculos[j + 1];
        }
      }
    }
    veiculos[--numVeiculos] = null;
  }

  public Veiculo getVeiculoByPlaca(String placa) {
    for (int i = 0; i < numVeiculos; i++) {
      if (veiculos[i].getPlaca().equalsIgnoreCase(placa)) {
        return veiculos[i];
      }
    }
    return null;
  }
  
  public Veiculo[] getAll() {
    Veiculo[] aux = new Veiculo[numVeiculos];
    for (int i = 0; i < numVeiculos; i++) {
      aux[i] = veiculos[i];
    }
    return aux;
  }

  public void replaceVeiculo(Veiculo velho, Veiculo novo) {
    for (int i = 0; i < numVeiculos; i++) {
      if (veiculos[i].equals(novo)) {
        return;
      }
      if (veiculos[i].equals(velho)) {
        veiculos[i] = novo;
      }
    }
  }
  
  public void removeAll() {
    for (int i = 0; i < numVeiculos; i++) {
      veiculos[i] = null;
    }
    numVeiculos = 0;
  }
  
}
