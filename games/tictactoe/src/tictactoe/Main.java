package tictactoe;

import java.awt.FontFormatException;
import java.io.IOException;

/**
 * Run a TicTacToe game interactively on the GUI.
 */
public class Main {
  /**
   * Run a TicTacToe game interactively on the GUI.
   * 
   * @param args not used
   */
  public static void main(String[] args) throws IOException, FontFormatException {
    TicTacToe m = new TicTacToeModel();
    TicTacToeView v = new SwingTicTacToeView("Tic-Tac-Toe");
    controller c = new controller(m);
    c.setView(v);
  }
}
