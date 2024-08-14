import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RevisaoAedTest {
  @Test
  public void testFibLoop() {
    RevisaoAed revisaoAed = new RevisaoAed();
    assertEquals(8, revisaoAed.fibLoop(6));
  }

  @Test
  public void testFibRec() {
    RevisaoAed revisaoAed = new RevisaoAed();
    assertEquals(8, revisaoAed.ficRec(6));
  }
  
}
