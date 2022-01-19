/**
 * this is the interface of controller
 */

public interface feature {

  /**
   * receive action from view and ask model to make a guess
   * @param s the string received from view
   */
  void makeGuess(String s);

  /**
   * receive action from view and ask model to start a new game
   */
  void startGame();

  /**
   * receive action from view and ask model to end the game
   */
  void exitGame();



}
