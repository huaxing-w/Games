import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/** The type Controller test. */
public class controllerTest {

  /** The type Mock model. */
  class mockModel implements iModel {
    private StringBuilder log;

    /** Instantiates a new Mock model. */
    public mockModel() {
      this.log = new StringBuilder();
    }

    @Override
    public String makeGuess(String s) {
      log.append("passed " + s);

      return "this is null";
    }

    @Override
    public int getGuessLeft() {
      return -1;
    }

    @Override
    public boolean isGameLost() {
      return false;
    }

    @Override
    public boolean isGameWin() {
      return false;
    }

    @Override
    public String getUserCurGuess() {
      return "this is null";
    }

    @Override
    public String getWrongGuess() {
      return "this is null";
    }
    /**
     * Get log string.
     *
     * @return the string
     */
    public String getLog() {
      return this.log.toString();
    }
  }

  /** Test make guess. */
  @Test
  public void testMakeGuess() {
    mockModel m= new mockModel();
    controller c=new controller(m);
    iView testView=new JFrameView("test");
    c.setView(testView);
    String guess="a";
    c.makeGuess(guess);
    assertEquals("passed a",m.getLog());
  }

}
