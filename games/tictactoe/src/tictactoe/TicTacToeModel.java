package tictactoe;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class represents a model for the game of tictactoe. It provides implementation for all
 * methods in the TicTacToe interface. It should work with the controller and view to perform a
 * good workflow for the game of tictactoe.
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private int step;
  private Player[][] board;

  /**
   * Constructs a TicTacToeModel object and initializes it to have a step of game and a game board
   */
  public TicTacToeModel() {
    this.step = 0;
    this.board = new Player[3][3];
  }

  @Override
  public void move(int r,int c) throws IllegalArgumentException,IllegalStateException {
    if (r < 0 || r >2 || c < 0 || c > 2 || this.getMarkAt(r,c) != null) {
      throw new IllegalArgumentException("Please enter a valid position for move!!");
    }

    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over!! No more moves!!");
    }

    switch (this.getTurn()) {
      case X:
        this.board[r][c] = Player.X;
        break;
      case O:
        this.board[r][c] = Player.O;
        break;
    }

    this.step += 1;
  }

  /**
   * Get the current turn, i.e., the player who will mark on the next call to move().
   *
   * @return the {@link Player} whose turn it is
   * @throws IllegalStateException if the game is over
   */
  @Override
  public Player getTurn() throws IllegalStateException {
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over!! No more moves!!");
    }

    if (this.step % 2 == 0) {
      return Player.X;
    }
    else {
      return Player.O;
    }
  }

  @Override
  public boolean isGameOver() {
    if (this.getWinner() == Player.X || this.getWinner() == Player.O) {
      return true;
    }

    for (int i = 0;i < 3;i++) {
      for (int j = 0;j < 3;j++) {
        if (this.board[i][j] == null) {
          return false;
        }
      }
    }
/*
    return this.step == 9;
*/
    return true;
  }

  @Override
  public Player getWinner() {
    Player[] XwinArray = {Player.X,Player.X,Player.X};
    Player[] OwinArray = {Player.O,Player.O,Player.O};

    for (int i = 0;i < 3;i++) {
      // check rows
      Player[] tempRow = {this.board[i][0],this.board[i][1],this.board[i][2]};
      if (Arrays.equals(tempRow,XwinArray)) {
        return Player.X;
      }
      else if (Arrays.equals(tempRow,OwinArray)) {
        return Player.O;
      }

      // check columns
      Player[] tempCol = {this.board[0][i],this.board[1][i],this.board[2][i]};
      if (Arrays.equals(tempCol,XwinArray)) {
        return Player.X;
      }
      else if (Arrays.equals(tempCol,OwinArray)) {
        return Player.O;
      }
    }

    // check diagonals
    Player[] forwardDiag = {this.board[0][0],this.board[1][1],this.board[2][2]};
    Player[] backwardDiag = {this.board[2][0],this.board[1][1],this.board[0][2]};

    if (Arrays.equals(forwardDiag,XwinArray) || Arrays.equals(backwardDiag,XwinArray)) {
      return Player.X;
    }
    else if (Arrays.equals(forwardDiag,OwinArray) || Arrays.equals(backwardDiag,OwinArray)) {
      return Player.O;
    }

    return null;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] deepCopy = new Player[3][3];

    for (int i = 0;i < 3;i++) {
      for (int j = 0;j < 3;j++) {
        deepCopy[i][j] = this.board[i][j];
      }
    }

    return deepCopy;
  }

  /**
   * Return the current {@link Player} mark at a given row and column, or {@code null} if the
   * position is empty.
   *
   * @param r the row
   * @param c the column
   * @return the player at the given position, or null if it's empty
   * @throws IllegalArgumentException if the given row and column are not valid
   */
  @Override
  public Player getMarkAt(int r, int c) throws IllegalArgumentException {
    if (r < 0 || r > 3 || c < 0 || c > 3) {
      throw new IllegalArgumentException("Please enter a valid position for move!!");
    }

    if (this.board[r][c] == Player.X) {
      return Player.X;
    }
    else if (this.board[r][c] == Player.O) {
      return Player.O;
    }
    else {
      return null;
    }
  }

  /**
   * Get a String description of the current board
   *
   * @return a String description of the current board
   */
  @Override
  public String toString() {
    // Using Java stream API to save code:
// return Arrays.stream(getBoard()).map(
//   row -> " " + Arrays.stream(row).map(
//     p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
//       .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
    List<String> rows = new ArrayList<>();
    for(Player[] row : getBoard()) {
      List<String> rowStrings = new ArrayList<>();
      for(Player p : row) {
        if(p == null) {
          rowStrings.add(" ");
        } else {
          rowStrings.add(p.toString());
        }
      }
      rows.add(" " + String.join(" | ", rowStrings));
    }
    return String.join("\n-----------\n", rows);
  }
}
