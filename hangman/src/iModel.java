/** The interface of the Model. */
public interface iModel {

  /**
   * Make guess string.
   *
   * @param s the input string
   * @return the string message
   */
  String makeGuess(String s);

  /**
   * Get the number of guess left.
   *
   * @return the number of guess left
   */
  int getGuessLeft();

  /**
   * Is the game win already
   *
   * @return ture if user win the game
   */
  boolean isGameWin();

  /**
   * Get user current guess
   *
   * @return user current guess String
   */
  String getUserCurGuess();

  /**
   * Get the user wrong guess.
   *
   * @return wrong guess string
   */
  String getWrongGuess();

  /**
   * if user lost the game.
   *
   * @return ture if user lost the game.
   */
  boolean isGameLost();
}
