package cs3500.pa03.Model;

/**
 * Represents a coordinate on the board.
 */

public class Coord {
  private int x;
  private int y;

  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x coordinate.
   * @return
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the y coordinate.
   * @return
   */
  public int getY() {
    return y;
  }

}
