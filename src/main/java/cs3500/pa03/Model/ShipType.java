package cs3500.pa03.Model;

/**
 * Represents the name and size of a ship.
 */
public class ShipType {
  public static final ShipType BATTLESHIP = new ShipType("b", BoatSize.BATTLESHIP);
  public static final ShipType DESTROYER = new ShipType("d", BoatSize.DESTROYER);
  public static final ShipType SUBMARINE = new ShipType("s", BoatSize.SUBMARINE);
  public static final ShipType CARRIER = new ShipType("c", BoatSize.CARRIER);

  private String letter;
  private BoatSize size;

  private ShipType(String letter, BoatSize size) {
    this.letter = letter;
    this.size = size;
  }

  /**
   * Returns the size of the ship.
   * @return
   */
  public BoatSize getBoatSize() {
    return size;
  }
}
