package cs3500.pa03.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Coord class.
 */
class CoordTest {
  private Coord coord;

  @BeforeEach
  public void setUp() {
    coord = new Coord(5, 5);
  }

  @Test
  public void testGetX() {
    assertEquals(5, coord.getX());
  }

  @Test
  public void testGetY() {
    assertEquals(5, coord.getY());
  }
}