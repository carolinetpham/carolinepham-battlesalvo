package cs3500.pa03.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ship on the board.
 */
public class Ship {
  private ShipType type;
  private Coord start;
  private Coord end;
  private List<Ship> ships = new ArrayList<>();
  private int size;
  private boolean hits[][];

  public Ship(ShipType type, Coord start, Coord end) {
    this.type = type;
    this.start = start;
    this.end = end;
  }

  /**
   * Constructs a ship with the given type, start coordinate, and size.
   * @param type
   * @param start
   * @param size
   */
  public Ship(ShipType type, Coord start, int size) {
    this.type = type;
    this.start = start;
    this.size = size;
    this.hits = new boolean[size][1];
  }

  /**
   * Returns the type of the ship.
   * @return
   */
  public List<Ship> getShips() {

    return ships;
  }
  public boolean isShipSunk() {
    for(int i = 0; i < size; i++) {
      if(!hits[i][0]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the type of the ship.
   * @param shot
   * @return
   */
  public boolean hasCoord(Coord shot) {
    int height = shot.getY();
    int width = shot.getX();
    if(height >= start.getY() && height <= end.getY() && width >= start.getX() && width <= end.getX()) {
      return true;
    }
    return false;
  }

  /**
   * Sets the hit status of the ship at the specified index.
   *
   * @param index the index of the ship's section to set as hit
   * @throws IndexOutOfBoundsException if the index is out of bounds
   */
  public void setHit(int index) {
    if (index >= 0 && index < size) {
      hits[index][0] = true;
    } else {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
  }

}
