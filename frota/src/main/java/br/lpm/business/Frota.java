package br.lpm.business;

import java.time.LocalDate;

public class Frota {
  private static final int MAX_VEICULOS = 1000;
  private int numVeiculos = 0;
  private Veiculo[] veiculos = new Veiculo[MAX_VEICULOS];
  private static final int MAX_ROTAS = 1000;
  private int numRotas = 0;
  private Rota[] rotas = new Rota[MAX_ROTAS];

  public Veiculo[] getAllVeiculos() {
    return veiculos;
  }

  public void addVeiculo(Veiculo veiculo) {
    veiculos[numVeiculos++] = veiculo;
  }

  public Veiculo getVeiculoByPlaca(String placa) {
    for (int i = 0; i < numVeiculos; i++) {
      if (veiculos[i].getPlaca().equals(placa)) {
        return veiculos[i];
      }
    }
    return null;
  }

  private void executarRemocao(int i) {
    for (int j = i; j < numVeiculos - 1; j++) {
      veiculos[j] = veiculos[j + 1];
    }
    veiculos[--numVeiculos] = null;
  }

  public void removeVeiculoByPlaca(String placa) {

    for (int i = 0; i < numVeiculos; i++) {
      if (veiculos[i].getPlaca().equals(placa)) {
        executarRemocao(i);
        return;
      }
    }
  }


  public void replaceVeiculo(Veiculo velho, Veiculo novo) {

    for (int i = 0; i < numVeiculos; i++) {
      if (veiculos[i].equals(velho)) {
        veiculos[i] = novo;
        return;
      }
    }
  }

  public Rota newRota(Motorista motorista, Veiculo veiculo, String origem, String destino) {
    Rota rota = new Rota(origem, destino, veiculo, motorista, LocalDate.now());
    return rota;
  }

  public void addRota(Rota rota) {
    rotas[numRotas++] = rota;
  }

  private void executarRemocaoRota(int i) {
    for (int j = i; j < numRotas - 1; j++) {
      rotas[j] = rotas[j + 1];
    }
    rotas[--numRotas] = null;
  }

  public void removeRota(Rota rota) {
    for (int i = 0; i < numRotas; i++) {
      if (rotas[i].equals(rota)) {
        executarRemocaoRota(i);
        return;
      }
    }
  }

  public Rota[] getAllRotas() {
    return rotas;
  }
}
