package cs3500.pa03.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the BoatSize enum.
 */
class BoatSizeTest {
  @Test
  void testGetSize() {
    assertEquals(6, BoatSize.CARRIER.getSize());
    assertEquals(5, BoatSize.BATTLESHIP.getSize());
    assertEquals(4, BoatSize.DESTROYER.getSize());
    assertEquals(3, BoatSize.SUBMARINE.getSize());
  }

  @Test
  void testGetName() {
    assertEquals("Carrier", BoatSize.CARRIER.getName());
    assertEquals("Battleship", BoatSize.BATTLESHIP.getName());
    assertEquals("Destroyer", BoatSize.DESTROYER.getName());
    assertEquals("Submarine", BoatSize.SUBMARINE.getName());
  }
}
