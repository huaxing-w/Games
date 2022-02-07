package tictactoe;

/** The type Controller. */
public class controller implements feature {
  private TicTacToe model;
  private TicTacToeView view;

  /**
   * Instantiates a new Controller.
   *
   * @param m the m
   */
  public controller(TicTacToe m) {
    this.model = m;
  }

  /**
   * Sets view.
   *
   * @param v the TicTacToe View
   */
  public void setView(TicTacToeView v) {
    view = v;
    view.addController(this);
  }

  @Override
  public void startNewGame() {
    this.model = new TicTacToeModel();
    this.view.setMessage("Make move for X");
    this.view.clearBoard();
  }

  @Override
  public void makeNextMove(int row, int col) {

    if (this.model.isGameOver()) {
      this.view.setMessage("Game is Over!");
    } else {
      if (this.model.getMarkAt(row, col) != null) {
        this.view.setMessage("Not valid move!");
      } else {
        this.model.move(row, col);

        this.view.setMoveOut(row, col, this.model.getMarkAt(row, col).toString());
        if (this.model.isGameOver()) {
          if (this.model.getWinner()==null) {
            this.view.setMessage("Tie Game!");
          } else if (this.model.getWinner().equals(Player.O)) {
            this.view.setMessage("O win!");
          } else if (this.model.getWinner().equals(Player.X)) {
            this.view.setMessage("X win!");
          }
        }
        else {
          this.view.setMessage("Make move for " + this.model.getTurn().toString());
        }
      }
    }
  }
}
