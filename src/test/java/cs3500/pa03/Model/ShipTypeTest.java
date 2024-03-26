package cs3500.pa03.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests for the ShipType enum.
 */
public class ShipTypeTest {

  @Test
  public void testGetBoatSize() {
    ShipType battleship = ShipType.BATTLESHIP;
    ShipType destroyer = ShipType.DESTROYER;
    ShipType submarine = ShipType.SUBMARINE;
    ShipType carrier = ShipType.CARRIER;

    assertEquals(BoatSize.BATTLESHIP, battleship.getBoatSize());
    assertEquals(BoatSize.DESTROYER, destroyer.getBoatSize());
    assertEquals(BoatSize.SUBMARINE, submarine.getBoatSize());
    assertEquals(BoatSize.CARRIER, carrier.getBoatSize());
  }
}
