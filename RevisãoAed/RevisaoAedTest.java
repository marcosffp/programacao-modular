package RevisãoAed;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RevisaoAedTest {
  @Test
  public void testFibLoop() {
    RevisaoAed revisaoAed = new RevisaoAed();
    assertEquals(55, revisaoAed.fibLoop(10));
  }

  @Test
  public void testFibRec() {
    RevisaoAed revisaoAed = new RevisaoAed();
    assertEquals(8, revisaoAed.ficRec(6));
  }
  
}
