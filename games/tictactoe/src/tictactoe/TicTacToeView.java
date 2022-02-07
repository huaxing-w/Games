package tictactoe;

/** The interface Tic tac toe view. */
public interface TicTacToeView {

  /**
   * Sets message.
   *
   * @param s the input String
   */
  void setMessage(String s);

  /**
   * Add controller.
   *
   * @param controller the controller
   */
  void addController(feature controller);

  /**
   * Sets move out.
   *
   * @param row the row
   * @param col the col
   * @param s the s
   */
  void setMoveOut(int row, int col, String s);

  /** Clear board. */
  void clearBoard();
}
