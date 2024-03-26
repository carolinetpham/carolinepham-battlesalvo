package cs3500.pa03.View;

import cs3500.pa03.Model.Board;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the BoardDisplay class.
 */
public class BoardDisplayTest {
  private BoardDisplay boardDisplay;

  @BeforeEach
  public void setUp() {
    Board board = new Board(10, 10);
    boardDisplay = new BoardDisplay(board);
  }

  @Test
  public void testIsValidFleetRetTrue() {
    int[] fleetSizes = {1, 2, 3, 4};
    int smallerDimension = 10;
    assertTrue(boardDisplay.isFleetValid(fleetSizes, smallerDimension));
  }

  @Test
  public void testIsValidFleetRetFalse() {
    int[] fleetSizes = {1, 2, 3, 5};
    int smallerDimension = 10;
    assertFalse(boardDisplay.isFleetValid(fleetSizes, smallerDimension));
  }


  @Test
  public void testGetRandomCountRet0() {
    int smaller = 10;
    int total = 10;
    assertEquals(0, boardDisplay.getRandomCount(smaller, total));
  }

  @Test
  public void testGetRandomCountRem1() {
    int smaller = 10;
    int total = 9;
    assertEquals(1, boardDisplay.getRandomCount(smaller, total));
  }

  @Test
  public void testGetRandomCount() {
    int smaller = 10;
    int total = 7;
    int randomCount = boardDisplay.getRandomCount(smaller, total);
    assertTrue(randomCount >= 1 && randomCount <= 3);
  }

}
