import java.util.ArrayList;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Test cases for the tictactoe model. Verifying that game state is properly managed, and
 * all game actions are properly validated.
 */
public class TicTacToeModelTest {

  private TicTacToe ttt1;


  /**
   * Set up the TicTacToeModel object for testing
   */
  @Before
  public void setUp() {
    this.ttt1 = new TicTacToeModel();
  }


  /**
   * Test for a legal move
   */
  @Test
  public void testMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
  }


  /**
   * Test for a horizontal win by legal moves
   */
  @Test
  public void testHorizontalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" X | X | X\n"
        + "-----------\n"
        + " O |   |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }


  /**
   * Test for a vertical win by legal moves
   */
  @Test
  public void testVerticalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(0, 1); // O takes upper middle
    ttt1.move(1, 0); // X takes middle left
    assertNull(ttt1.getWinner());
    ttt1.move(0, 2); // O takes upper right
    ttt1.move(2, 0); // X takes lower left
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" X | O | O\n"
        + "-----------\n"
        + " X |   |  \n"
        + "-----------\n"
        + " X |   |  ", ttt1.toString());
  }


  /**
   * Test for a diagonal win by legal moves
   */
  @Test
  public void testDiagonalWin() {
    diagonalWinHelper();
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.O, ttt1.getWinner());
    assertEquals(" X | X | O\n"
        + "-----------\n"
        + " X | O |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }


  // set up situation where game is over, O wins on the diagonal, board is not full
  private void diagonalWinHelper() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(1, 0); // X takes middle left
    assertNull(ttt1.getWinner());
    ttt1.move(1, 1); // O takes center
    ttt1.move(0, 1); // X takes upper middle
    ttt1.move(0, 2); // O takes upper right
  }


  /**
   * Test for illegal moves where the cell to take a move is occupied and where the cell is out of bound
   */
  @Test
  public void testInvalidMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));
    try {
      ttt1.move(0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      ttt1.move(-1, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }


  /**
   * Test for valid moves after the game is over
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveAttemptAfterGameOver() {
    diagonalWinHelper();
    ttt1.move(2, 2); // 2,2 is an empty position
  }


  /**
   * Test for a tie game
   */
  @Test
  public void testCatsGame() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(1, 1);
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(0, 2);
    ttt1.move(0, 1);
    ttt1.move(2, 1);
    ttt1.move(1, 0);
    ttt1.move(1, 2);
    ttt1.move(2, 2);
    ttt1.move(2, 0);
    assertTrue(ttt1.isGameOver());
    assertNull(ttt1.getWinner());
    assertEquals( " X | O | X\n"
        + "-----------\n"
        + " O | O | X\n"
        + "-----------\n"
        + " X | X | O", ttt1.toString());
  }


  /**
   * Test for getMarkAt() method where the row of the cell to get is out of bound
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtRow() {
    ttt1.getMarkAt(-12, 0);
  }


  /**
   * Test for getMarkAt() method where the column of the cell to get is out of bound
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtCol() {
    ttt1.getMarkAt(0, -30);
  }


  /**
   * Test for getBoard() method as a deep copy
   */
  @Test
  public void testGetBoard() {
    diagonalWinHelper();
    Player[][] bd = ttt1.getBoard();
    assertEquals(Player.X, bd[0][0]);
    assertEquals(Player.O, bd[1][1]);
    assertEquals(Player.X, bd[0][1]);

    // attempt to cheat by mutating board returned by getBoard()
    // check correct preconditions
    assertEquals(Player.O, bd[2][0]);
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    bd[2][0] = Player.X;  // mutate
    // check correct post conditions
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    Player[][] bd2 = ttt1.getBoard();
    assertEquals(Player.O, bd2[2][0]);
  }


  /**
   * Test case where board is full AND there is a winner
   */
  @Test
  public void testFullAndXWins() {
    ttt1.move(0, 0);  // X takes upper left
    ttt1.move(0, 1);  // O takes upper center
    ttt1.move(0, 2);  // X takes upper right
    ttt1.move(1, 0);  // O takes central left
    ttt1.move(1, 1);  // X takes central center
    ttt1.move(1, 2);  // O takes central right
    ttt1.move(2, 1);  // X takes lower center
    ttt1.move(2, 0);  // O takes lower left
    ttt1.move(2, 2);  // X takes lower right
    assertEquals(" X | O | X\n"
        + "-----------\n"
        + " O | X | O\n"
        + "-----------\n"
        + " O | X | X", ttt1.toString());
    assertTrue(this.ttt1.isGameOver());
    assertEquals(Player.X,this.ttt1.getWinner());
  }


  /**
   * Test invalid getTurn() after the game is over
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidGetTurn() {
    ttt1.move(0, 0); // X takes upper left
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(1, 0); // X takes middle left
    ttt1.move(1, 1); // O takes center
    ttt1.move(0, 1); // X takes upper middle
    ttt1.move(0, 2); // O takes upper right
    assertTrue(this.ttt1.isGameOver());
    assertEquals(Player.O,this.ttt1.getWinner());
    ttt1.getTurn();
  }


  /**
   * Randomized test for the game
   */
  @Test
  public void testIsGameOverRandom() {
    int N = 5000;
    for (int i = 0;i < N;i++) {
      TicTacToeModel testGame = new TicTacToeModel();
      int moveCount = 0;

      ArrayList<Pos> positions = new ArrayList<>();
      for (int k = 0; k < 3; k++) {
        for (int j = 0; j < 3; j++) {
          positions.add(new Pos(k, j));
        }
      }
      Collections.shuffle(positions);

      for (Pos pos : positions) {
        Player nextPlayer = testGame.getTurn();
        testGame.move(pos.row, pos.col);
        moveCount++;
        boolean expected = willThisMoveEndTheGame(pos.row, pos.col, moveCount, nextPlayer, testGame);
        boolean actual = testGame.isGameOver();
        assertEquals(expected, actual);
        if (testGame.isGameOver()) {
          break;
        }
      }
    }
  }


  /**
   * This class represents a position with a row and a column coordinates
   */
  private class Pos {
    int row;
    int col;

    private Pos(int row,int col) {
      this.row = row;
      this.col = col;
    }
  }


  /**
   * Check if the game will be ended after a specific move by the corresponding player
   *
   * @param r the row coordinate of a specific move
   * @param c the column coordinate of a specific move
   * @param moveCount the step count after the corresponding player finishing a specific move, being
   *                  9 at maximum
   * @param p the corresponding player that takes the move
   * @param game the current state of game to be tested
   * @return true if this move will end the game, false otherwise
   */
  private boolean willThisMoveEndTheGame(int r,int c,int moveCount,Player p,TicTacToeModel game) {
    // Winning or losing always relates to the most recent move.
    // check row
    for (int i = 0; i < 3; i++) {
      if (game.getMarkAt(r, i) != p)
        break;
      if (i == 3 - 1) {
        // win at a row
        return true;
      }
    }

    // check column
    for (int i = 0; i < 3; i++) {
      if (game.getMarkAt(i, c) != p)
        break;
      if (i == 3 - 1) {
        // win at a column
        return true;
      }
    }

    // check diag
    if (r == c) {
      //we're on a diagonal
      for (int i = 0; i < 3; i++) {
        if (game.getMarkAt(i, i) != p)
          break;
        if (i == 3 - 1) {
          // win at a diag
          return true;
        }
      }
    }

    //check another diag
    if (r + c == 3 - 1) {
      for (int i = 0; i < 3; i++) {
        if (game.getMarkAt(i, 3 - 1 - i) != p)
          break;
        if (i == 3 - 1) {
          // win at another diag
          return true;
        }
      }
    }

    //check draw
    return moveCount == 9;
  }
}
