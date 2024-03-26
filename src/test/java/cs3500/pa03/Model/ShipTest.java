package cs3500.pa03.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the Ship class.
 */
class ShipTest {
  @Test
  void testGetShips() {
    Ship ship1 = new Ship(ShipType.CARRIER, new Coord(1, 1), new Coord(1, 6));
    Ship ship2 = new Ship(ShipType.BATTLESHIP, new Coord(2, 2), new Coord(2, 6));
    Ship ship3 = new Ship(ShipType.DESTROYER, new Coord(3, 3), new Coord(3, 6));

    ship1.getShips().add(ship2);
    ship1.getShips().add(ship3);

    assertEquals(2, ship1.getShips().size());
    assertSame(ship2, ship1.getShips().get(0));
    assertSame(ship3, ship1.getShips().get(1));
  }

  @Test
  void testIsShipSunk() {
    Ship ship = new Ship(ShipType.SUBMARINE, new Coord(1, 1), 3);
    assertFalse(ship.isShipSunk());

    ship.setHit(0);
    assertFalse(ship.isShipSunk());

    ship.setHit(1);
    assertFalse(ship.isShipSunk());

    ship.setHit(2);
    assertTrue(ship.isShipSunk());
  }
}
