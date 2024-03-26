package cs3500.pa03.Model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for the GameResult class.
 */
class GameResultTest {
  @Test
  void testIsGameOver() {
    GameResult gameResult = new GameResult(true, null, null, null);
    assertTrue(gameResult.isGameOver());

    gameResult = new GameResult(false, null, null, null);
    assertFalse(gameResult.isGameOver());
  }

  @Test
  void testGetWin() {
    GameResult winResult = new GameResult(true, null, null, null);
    GameResult gameResult = new GameResult(false, winResult, null, null);
    assertSame(winResult, gameResult.getWin());
  }

  @Test
  void testGetLoss() {
    GameResult lossResult = new GameResult(true, null, null, null);
    GameResult gameResult = new GameResult(false, null, lossResult, null);
    assertSame(lossResult, gameResult.getLoss());
  }

  @Test
  void testGetDraw() {
    GameResult drawResult = new GameResult(true, null, null, null);
    GameResult gameResult = new GameResult(false, null, null, drawResult);
    assertSame(drawResult, gameResult.getDraw());
  }

}