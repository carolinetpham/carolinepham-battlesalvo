package cs3500.pa03.Model;

/**
 * Represents the result of a game.
 */
public class GameResult {
  private boolean isGameOver;
  private GameResult win;
  private GameResult loss;
  private GameResult draw;

    public GameResult(boolean isGameOver, GameResult win, GameResult loss, GameResult draw) {
        this.isGameOver = isGameOver;
        this.win = win;
        this.loss = loss;
        this.draw = draw;
    }

  /**
   * Returns whether or not the game is over.
   * @return
   */
  public boolean isGameOver() {
        return isGameOver;
    }

  /**
   * Returns the winner of the game.
   *
   * @return
   */
  public GameResult getWin() {
        return win;
    }

  /**
   * Returns the loser of the game.
   *
   * @return
   */
  public GameResult getLoss() {
        return loss;
    }

  /**
   * Returns the draw of the game.
   * @return
   */
  public GameResult getDraw() {
        return draw;
    }
}
