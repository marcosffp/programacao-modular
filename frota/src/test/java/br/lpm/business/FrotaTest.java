package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FrotaTest {
  private Frota frota;
  private Motorista motorista;
  private Veiculo veiculo;
  private Mecanico mecanico;
  private Oficina oficina;
  private Rota rota;
  private Veiculo veiculo2;

  @BeforeEach
  void setUp() throws Exception {
    frota = new Frota();
    veiculo = new Veiculo("V8", 2024, "ASD123", 250, Estado.TRANSITO);
    veiculo2 = new Veiculo("V4", 2024, "111SSA", 200, Estado.MANUTENCAO);
    motorista = new Motorista("Marcos", 1, veiculo);
    oficina = new Oficina("São Jorge", "Rua A");
    mecanico = new Mecanico("Bernardo", 2, oficina);
    frota.newRota(motorista, veiculo, "bh", "santa luzia");

    frota.cadastrarOficina(oficina);
    frota.addVeiculo(veiculo);
    frota.cadastrarMecanico(mecanico);
    oficina.addMecanico(mecanico);
    frota.cadastrarMotorista(motorista);
    rota = frota.newRota(motorista, veiculo2, "santa luzia", "bg");
  }

  @Test
  void testAddVeiculo() {
    assertEquals(veiculo, frota.getVeiculoByPlaca("ASD123"));
  }

  @Test
  void testCadastrarMecanico() {
    assertNotNull(frota.getMecanicoById(2), "O mecânico não deve ser nulo.");
    assertEquals(mecanico, frota.getMecanicoById(2));
  }

  @Test
  void testCadastrarMotorista() {
    frota.cadastrarMotorista(motorista);
    assertEquals(motorista, frota.getMotoristaById(1));
  }

  @Test
  void testCadastrarOficina() {
    assertEquals(oficina, frota.getOficinaByName("São Jorge"));
  }

  @Test
  void testGetAllMecanicos() {
    Mecanico[] mecanicos = frota.getAllMecanicos();
    assertEquals(mecanico, mecanicos[0]);
  }

  @Test
  void testGetAllMotoristas() {
    Motorista[] motoristas = frota.getAllMotoristas();
    assertEquals(motorista, motoristas[0]);
  }

  @Test
  void testGetAllOficinas() {
    Oficina[] oficinas = frota.getAllOficinas();
    assertEquals(oficina, oficinas[0]);
  }

  @Test
  void testGetAllRotas() {
    Rota[] rotas = frota.getAllRotas();
    assertEquals(rota, rotas[1]);
  }

  @Test
  void testGetAllVeiculos() {
    Veiculo[] veiculos = frota.getAllVeiculos();
    assertEquals(veiculo, veiculos[0]);
  }

  @Test
  void testGetMecanicoById() {
    assertEquals(mecanico, frota.getMecanicoById(2));
  }

  @Test
  void testGetMotoristaById() {
    assertEquals(motorista, frota.getMotoristaById(1));
  }

  @Test
  void testGetNumMecanicos() {
    assertEquals(1, frota.getNumMecanicos());
  }

  @Test
  void testGetNumMotoristas() {
    assertEquals(1, frota.getNumMotoristas());
  }

  @Test
  void testGetNumOficinas() {
    assertEquals(1, frota.getNumOficinas());
  }

  @Test
  void testGetNumRotas() {
    assertEquals(2, frota.getNumRotas());
  }

  @Test
  void testGetNumVeiculos() {
    assertEquals(1, frota.getNumVeiculos());
  }

  @Test
  void testGetOficinaByName() {
    assertEquals(oficina, frota.getOficinaByName("São Jorge"));
  }

  @Test
  void testGetRotaByVeiculo() {
    assertEquals(rota, frota.getRotaByVeiculo(veiculo2));
  }

  @Test
  void testGetVeiculoByPlaca() {
    assertEquals(veiculo, frota.getVeiculoByPlaca("ASD123"));
  }

  @Test
  void testNewRota() {
    Rota rota = frota.newRota(motorista, veiculo, "sl", "bh");
    assertEquals("sl", rota.getOrigem());
    assertEquals("bh", rota.getDestino());
  }

  @Test
  void testRemoveMecanico() {
    frota.removeMecanico(mecanico);
    assertEquals(0, frota.getNumMecanicos());
  }

  @Test
  void testRemoveMotorista() {
    frota.removeMotorista(motorista);
    assertEquals(0, frota.getNumMotoristas());
  }

  @Test
  void testRemoveOficina() {
    frota.removeOficina(oficina);
    assertEquals(0, frota.getNumOficinas());
  }

  @Test
  void testRemoveRota() {
    Rota rota = frota.getRotaByVeiculo(veiculo);
    frota.removeRota(rota);
    assertEquals(1, frota.getNumRotas());
  }

  @Test
  void testRemoveVeiculo() {
    frota.removeVeiculo(veiculo);
    assertEquals(0, frota.getNumVeiculos());
  }

  @Test
  void testReplaceMecanico() {
    Mecanico novoMecanico = new Mecanico("Carlos", 3, oficina);
    frota.replaceMecanico(mecanico, novoMecanico);
    assertEquals(novoMecanico, frota.getMecanicoById(3));
  }

  @Test
  void testReplaceMotorista() {
    Motorista novoMotorista = new Motorista("João", 4, veiculo);
    frota.replaceMotorista(motorista, novoMotorista);
    assertEquals(novoMotorista, frota.getMotoristaById(4));
  }

  @Test
  void testReplaceOficina() {
    Oficina novaOficina = new Oficina("Novo São Jorge", "Rua B");
    frota.replaceOficina(oficina, novaOficina);
    assertEquals(novaOficina, frota.getOficinaByName("Novo São Jorge"));
  }

  @Test
  void testReplaceRota() {
    Rota velhaRota = frota.getRotaByVeiculo(veiculo);
    Rota novaRota = new Rota();
    novaRota.setMotorista(motorista);
    novaRota.setVeiculo(veiculo);
    novaRota.setOrigem("nova origem");
    novaRota.setDestino("novo destino");
    novaRota.setData(LocalDate.now());
    frota.replaceRota(velhaRota, novaRota);
    assertEquals(novaRota, frota.getRotaByVeiculo(veiculo));
  }

  @Test
  void testReplaceVeiculo() {
    Veiculo novoVeiculo = new Veiculo("V7", 2024, "ASA1232", 100, Estado.TRANSITO);
    frota.replaceVeiculo(veiculo, novoVeiculo);
    assertEquals(novoVeiculo, frota.getVeiculoByPlaca("ASA1232"));
  }
}
