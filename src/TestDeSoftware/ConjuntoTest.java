package TestDeSoftware;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConjuntoTest {
  @Test
  public void testConjuntoVazio() {
    Conjunto c = new Conjunto();
    assertEquals(0, c.tamanho());
  }

  @Test
  public void testAdicionarUm() {
    Conjunto c = new Conjunto();
    c.adicionar(new Object());
    assertEquals(1, c.tamanho());
  }

  @Test
  public void testAdicionarJaExistente() {
      Conjunto c = new Conjunto();
      Object o = new Object();
      c.adicionar(o);
      c.adicionar(o);
      assertEquals(1, c.tamanho()); 
  }

}
