package cs3500.pa03.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Board class.
 */
class BoardTest {
  private Board board;

  @BeforeEach

  public void setUp() {
    board = new Board(10, 10);
  }

  @Test
  public void testGetWidth() {
    assertEquals(10, board.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(10, board.getHeight());
  }

  @Test
  public void testInitializeBoard() {
    board.initializeBoard();
    char[][] grid = board.getGrid();
    for (int i = 0; i < board.getWidth(); i++) {
      for (int j = 0; j < board.getHeight(); j++) {
        assertEquals('0', grid[i][j]);
      }
    }
  }

  @Test
  public void testHorizontalShipPlacement() {
    board.placeShip(2, 2, 4, true);
    char[][] grid = board.getGrid();

    for (int i = 2; i < 6; i++) {
      assertEquals('D', grid[i][2]);
    }
  }

  @Test
  public void testVerticalShipPlacement() {
    board.placeShip(2, 2, 4, false);
    char[][] grid = board.getGrid();

    for (int i = 2; i < 6; i++) {
      assertEquals('D', grid[2][i]);
    }
  }

  @Test
  public void testInvalidShot() {
    assertEquals('I', board.takeShot(-1, 2));
    assertEquals('I', board.takeShot(2, -1));
  }

  @Test
  public void testMissTakeShot() {
    board.placeShip(2, 2, 4, true);
    assertEquals('M', board.takeShot(1, 1));
  }

  @Test
  public void testHitTakeShot() {
    board.placeShip(2, 2, 4, true);
    assertEquals('H', board.takeShot(2, 2));
  }

  @Test
  public void testIsGameOverTrue() {
    board.placeShip(2, 2, 4, true);
    board.takeShot(2, 2);
    board.takeShot(3, 2);
    board.takeShot(4, 2);
    board.takeShot(5, 2);
    assertFalse(board.isGameOver());
  }

  @Test
  public void testIsGameOverFalse() {
    board.placeShip(2, 2, 4, true);
    board.takeShot(2, 2);
    assertTrue(board.isGameOver());
  }

  @Test
  public void testValidPlacementTrueHor() {
    assertTrue(board.isPlacementValid(2, 2, 4, true));
  }

  @Test
  public void testValidPlacementFalseHor() {
    board.placeShip(2, 2, 4, true);
    assertFalse(board.isPlacementValid(2, 2, 4, true));
    assertFalse(board.isPlacementValid(3, 2, 4, true));
  }

  @Test
  public void testValidPlacementTrueVert() {
    assertTrue(board.isPlacementValid(2, 2, 4, false));
  }

  @Test
  public void testValidPlacementFalseVert() {
    board.placeShip(2, 2, 4, false);
    assertFalse(board.isPlacementValid(2, 2, 4, false));
    assertFalse(board.isPlacementValid(2, 3, 4, false));
  }

}