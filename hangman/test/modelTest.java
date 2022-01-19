import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/** this is the test for the hangman model */
public class modelTest {


  /**
   * test constructor
   */
  @Test
  public void testConstructor(){
    iModel m=new testModel();
    assertTrue(m!=null);
  }
  /**
   * test if game lost
   */
  @Test
  public void testGameLost() {
    iModel m=new testModel();
    m.makeGuess("l");
    assertFalse(m.isGameLost());
  }

  /**
   * test if game win
   */
  @Test
  public void testGameWin(){
    iModel m=new testModel();
    m.makeGuess("s");
    assertFalse(m.isGameWin());
    m.makeGuess("n");
    m.makeGuess("o");
    m.makeGuess("w");
    assertTrue(m.isGameWin());

  }

  /**
   * test game left
   */
  @Test
  public void testGameLeft(){
    iModel m=new testModel();
    assertEquals(7,m.getGuessLeft());
    m.makeGuess("q");
    assertEquals(6,m.getGuessLeft());
    m.makeGuess("k");
    assertEquals(5,m.getGuessLeft());
    m.makeGuess("m");
    assertEquals(4,m.getGuessLeft());
    m.makeGuess("p");
    assertEquals(3,m.getGuessLeft());
    m.makeGuess("y");
    assertEquals(2,m.getGuessLeft());
    m.makeGuess("l");
    assertEquals(1,m.getGuessLeft());
    m.makeGuess("b");
    assertEquals(0,m.getGuessLeft());
  }

  /**
   * test user current guess
   */
  @Test
  public void testWrongGuess(){
    iModel m=new testModel();
    m.makeGuess("z");
    assertEquals("Your wrong guesses are: z",m.getWrongGuess());
    m.makeGuess("b");
    assertEquals("Your wrong guesses are: z b",m.getWrongGuess());

  }

  /**
   * test current correct guess
   */
  @Test
  public void testCorrectGuess(){
    iModel m=new testModel();
    m.makeGuess("n");
    assertEquals("n _ _",m.getUserCurGuess());
    m.makeGuess("w");
    assertEquals("n _ w",m.getUserCurGuess());
  }
}
