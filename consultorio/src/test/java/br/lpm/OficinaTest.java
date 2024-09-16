package br.lpm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OficinaTest {
  private static Veiculo veiculo;
  private static Veiculo veiculo2;
  private static Oficina oficina;

  @BeforeEach
  void setUp() {
    veiculo = new Veiculo("V8", 2017, "SFFFF123", 600, Status.EM_TRANSITO);
    veiculo2= new Veiculo("V8", 2017, "SFFFF123", 600, Status.EM_MANUTENCAO);
    oficina=new Oficina("Alto tanque", "Rua a");
  }
  @Test
  void testAddVeiculo() {
    oficina.removeAll();
    oficina.addVeiculo(veiculo);
    assertEquals(veiculo, oficina.getVeiculoByPlaca("SFFFF123"));
  }

  @Test
  void testGetAll() {
    oficina.removeAll();
    oficina.addVeiculo(veiculo);
    Veiculo[] aux = oficina.getAll();
    assertEquals(1, aux.length);

  }

  @Test
  void testGetVeiculoByPlaca() {
    oficina.removeAll();
    oficina.addVeiculo(veiculo);
    assertEquals(veiculo, oficina.getVeiculoByPlaca("SFFFF123"));
  }

  @Test
  void testRemoveAll() {
    oficina.removeAll();
    oficina.addVeiculo(veiculo);
    assertEquals(veiculo, oficina.getVeiculoByPlaca("SFFFF123"));
    oficina.removeAll();
    assertEquals(0, oficina.getAll().length);
  }

  @Test
  void testRemoveVeiculo() {
    oficina.removeAll();
    oficina.addVeiculo(veiculo);
    oficina.removeVeiculo(veiculo);
    assertEquals(0, oficina.getAll().length);
  }

  @Test
  void testReplaceVeiculo() {
    oficina.removeAll();
    oficina.addVeiculo(veiculo);
    oficina.replaceVeiculo(veiculo, veiculo2);
    Veiculo[] aux = oficina.getAll();
    assertEquals(veiculo2, aux[0]);
  }
}
