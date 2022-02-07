package tictactoe;

/**
 * Represents a Controller for Tic Tac Toe: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface feature {

  /**
   * Make next move.
   *
   * @param row the row of the board
   * @param col the col of the board
   */
  void makeNextMove(int row, int col);

  /** Start new game. */
  void startNewGame();
}
