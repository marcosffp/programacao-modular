import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImcTest {

  @Test
  public void testGetImc() {
    Imc imc = new Imc();
    imc.peso = 60;
    imc.altura = 1.60;
    assertEquals(23.43, imc.getImc(), 0.01D);
  }
  
}
