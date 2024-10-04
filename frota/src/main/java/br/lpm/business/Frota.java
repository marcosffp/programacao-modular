package br.lpm.business;

import java.time.LocalDate;

public class Frota {
  private static final int MAX_VALOR = 1000;
  private int numVeiculos = 0;
  private int numMecanicos = 0;
  private int numMotoristas = 0;
  private int numOficinas = 0;
  private int numRotas = 0;
  private Veiculo[] veiculos = new Veiculo[MAX_VALOR];
  private Rota[] rotas = new Rota[MAX_VALOR];
  private Mecanico[] mecanicos = new Mecanico[MAX_VALOR];
  private Motorista[] motoristas = new Motorista[MAX_VALOR];
  private Oficina[] oficinas = new Oficina[MAX_VALOR];

  public void addVeiculo(Veiculo veiculo) {
    numVeiculos=ManipuladorDeArrays.adicionar(veiculo, veiculos, numVeiculos, MAX_VALOR);
  }

  public void addRota(Rota rota) {
    numRotas=ManipuladorDeArrays.adicionar(rota, rotas, numRotas, MAX_VALOR);
  }

  public void cadastrarMotorista(Motorista motorista) {
    numMotoristas=ManipuladorDeArrays.adicionar(motorista, motoristas, numMotoristas, MAX_VALOR);
  }

  public void cadastrarOficina(Oficina oficina) {
    numOficinas=ManipuladorDeArrays.adicionar(oficina, oficinas, numOficinas, MAX_VALOR);
  }

  public void cadastrarMecanico(Mecanico mecanico) {
    numMecanicos=ManipuladorDeArrays.adicionar(mecanico, mecanicos, numMecanicos, MAX_VALOR);
  }

  public void removeRota(Rota rota) {
    numRotas=ManipuladorDeArrays.remover(rota, rotas, numRotas, MAX_VALOR);
  }

  public void removeVeiculo(Veiculo veiculo) {
    numVeiculos=ManipuladorDeArrays.remover(veiculo, veiculos, numVeiculos, MAX_VALOR);
  }

  public void removeMotorista(Motorista motorista) {
    numMotoristas=ManipuladorDeArrays.remover(motorista, motoristas, numMotoristas, MAX_VALOR);
  }

  public void removeMecanico(Mecanico mecanico) {
    numMecanicos=ManipuladorDeArrays.remover(mecanico, mecanicos, numMecanicos, MAX_VALOR);
  }

  public void removeOficina(Oficina oficina) {
    numOficinas=ManipuladorDeArrays.remover(oficina, oficinas, numOficinas, MAX_VALOR);
  }

  public Veiculo getVeiculoByPlaca(String placa) {
    for (Veiculo veiculo : veiculos) {
      if (veiculo!= null && veiculo.getPlaca().equalsIgnoreCase(placa)) {
        return veiculo;
      }
    }
    return null;
  }

  public Rota getRotaByVeiculo(Veiculo veiculo) {
    for (Rota rota : rotas) {
      if (rota != null && rota.getVeiculo().equals(veiculo)) {
        return rota;
      }
    }
    return null;
  }

  public Motorista getMotoristaById(int id) {
    for (Motorista motorista : motoristas) {
      if (motorista != null&&motorista.getId() == id) {
        return motorista;
      }
    }
    return null;
  }

  public Mecanico getMecanicoById(int id) {
    for (Mecanico mecanico : mecanicos) {
      if (mecanico != null && mecanico.getId() == id) {
        return mecanico;
      }
    }
    return null;
  }

  public Oficina getOficinaByName(String nome) {
    for (Oficina oficina : oficinas) {
      if (oficina != null && oficina.getNome().equalsIgnoreCase(nome)) {
        return oficina;
      }
    }
    return null;
  }

  public Veiculo[] getAllVeiculos() {
    return veiculos;
  }

  public Rota[] getAllRotas() {
    return rotas;
  }

  public Mecanico[] getAllMecanicos() {
    return mecanicos;
  }

  public Motorista[] getAllMotoristas() {
    return motoristas;
  }

  public Oficina[] getAllOficinas() {
    return oficinas;
  }

  public void replaceVeiculo(Veiculo velho, Veiculo novo) {
    ManipuladorDeArrays.replace(velho, novo, veiculos);
  }

  public void replaceRota(Rota velha, Rota nova) {
    ManipuladorDeArrays.replace(velha, nova, rotas);
  }

  public void replaceMotorista(Motorista velho, Motorista novo) {
    ManipuladorDeArrays.replace(velho, novo, motoristas);
  }

  public void replaceMecanico(Mecanico velho, Mecanico novo) {
    ManipuladorDeArrays.replace(velho, novo, mecanicos);
  }

  public void replaceOficina(Oficina velha, Oficina nova) {
    ManipuladorDeArrays.replace(velha, nova, oficinas);
  }

  public Rota newRota(Motorista motorista, Veiculo veiculo, String origem, String destino) {
    Rota rota = new Rota();
    rota.setMotorista(motorista);
    rota.setVeiculo(veiculo);
    rota.setOrigem(origem);
    rota.setDestino(destino);
    rota.setData(LocalDate.now());
    addRota(rota);
    return rota;
  }

  public int getNumVeiculos() {
    return numVeiculos;
  }

  public static int getMaxValor() {
    return MAX_VALOR;
  }

  public int getNumMecanicos() {
    return numMecanicos;
  }

  public int getNumMotoristas() {
    return numMotoristas;
  }

  public int getNumOficinas() {
    return numOficinas;
  }

  public int getNumRotas() {
    return numRotas;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Frota Detalhes:\n");
    sb.append("***********************************\n");

    sb.append("Veículos:\n");
    if (numVeiculos > 0) {
      for (int i = 0; i < numVeiculos; i++) {
        sb.append(veiculos[i]).append("\n");
      }
    } else {
      sb.append("Nenhum veículo cadastrado.\n");
    }
    sb.append("\n");

    sb.append("Rotas:\n");
    if (numRotas > 0) {
      for (int i = 0; i < numRotas; i++) {
        sb.append(rotas[i]).append("\n");
      }
    } else {
      sb.append("Nenhuma rota cadastrada.\n");
    }
    sb.append("\n");

    sb.append("Oficinas:\n");
    if (numOficinas > 0) {
      for (int i = 0; i < numOficinas; i++) {
        sb.append(oficinas[i]).append("\n");
      }
    } else {
      sb.append("Nenhuma oficina cadastrada.\n");
    }
    sb.append("\n");

    sb.append("Motoristas:\n");
    if (numMotoristas > 0) {
      for (int i = 0; i < numMotoristas; i++) {
        sb.append(motoristas[i]).append("\n");
      }
    } else {
      sb.append("Nenhum motorista cadastrado.\n");
    }
    sb.append("\n");

    sb.append("Mecânicos:\n");
    if (numMecanicos > 0) {
      for (int i = 0; i < numMecanicos; i++) {
        sb.append(mecanicos[i]).append("\n");
      }
    } else {
      sb.append("Nenhum mecânico cadastrado.\n");
    }

    sb.append("***********************************\n");
    return sb.toString();
  }
}
