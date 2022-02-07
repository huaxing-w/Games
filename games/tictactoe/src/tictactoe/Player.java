package tictactoe;

/**
 * This enum class represents an enum-type Players for the game of tictactoe X and O.
 */
public enum Player {
  X("X"),
  O("O");

  private String player;

  Player(String player) {
    this.player = player;
  }

  /**
   * Get a String description of the Player
   *
   * @return a String description of the Player
   */
  @Override
  public String toString() {
    return player;
  }
}
