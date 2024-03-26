package cs3500.pa03.Model;

/**
 * Represents the name and size of a ship.
 */
public enum BoatSize {
  CARRIER (6),
  BATTLESHIP (5),
  DESTROYER(4),
  SUBMARINE(3);

  private int size;
  private BoatSize(int size) {
    this.size = size;
  }

  /**
   * Returns the size of the ship.
   * @return
   */
  public int getSize() {
    return size;
  }

  /**
   * Returns the name of the ship.
   * @return
   */
  public String getName() {
    switch (this) {
      case CARRIER:
        return "Carrier";
      case BATTLESHIP:
        return "Battleship";
      case DESTROYER:
        return "Destroyer";
      case SUBMARINE:
        return "Submarine";
    }
    return "";
  }
}
