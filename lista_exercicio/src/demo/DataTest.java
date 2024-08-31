package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataTest {
  private Data data;

  @BeforeEach
  void setUp() {
    data = new Data(31, 8, 2024);
  }

  @Test
  void testAdicionarDias() {
    data.adicionarDias(10);
    assertEquals(10, data.getDia());
    assertEquals(9, data.getMes());
    assertEquals(2024, data.getAno());
  }

  @Test
  void testDiaDaSemana() {
    assertEquals("Sábado", data.diaDaSemana());
  }

  @Test
  void testDiasNoMes() {
    assertEquals(31, data.diasNoMes());
    Data dataFevereiro = new Data(10, 2, 2024);
    assertEquals(
        29, dataFevereiro.diasNoMes());
  }

  @Test
  void testEAnoBisexto() {
    assertEquals(true, data.eAnoBisexto());
    Data dataNaoBisexto = new Data(31, 8, 2023);
    assertEquals(false, dataNaoBisexto.eAnoBisexto());
  }

  @Test
  void testGetAno() {
    assertEquals(2024, data.getAno(), "O ano deve ser 2024");
  }

  @Test
  void testGetDia() {
    assertEquals(31, data.getDia(), "O dia deve ser 31");
  }

  @Test
  void testGetMes() {
    assertEquals(8, data.getMes(), "O mês deve ser agosto (8)");
  }

  @Test
  void testPorExtenso() {
    assertEquals(
        "31 de Agosto de 2024",
        data.porExtenso());
  }

  @Test
  void testProximoDia() {
    data.proximoDia();
    assertEquals(1, data.getDia());
    assertEquals(9, data.getMes());
    assertEquals(2024, data.getAno());
  }
}
