package cs3500.pa03.Model.players;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the AIPlayer class.
 */
class AiPlayerTest {
  private AIPlayer aiPlayer;

  @BeforeEach
  public void setUp() {
    aiPlayer = new AIPlayer("AI");
  }

  @Test
  public void testSetup() {
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 2);
    aiPlayer.setup(10, 10, specifications);
    assertEquals(6, aiPlayer.setup(10, 10, specifications).size());
  }

  @Test
  public void testTakeShots() {
    aiPlayer.takeShots();
    assertEquals(0, aiPlayer.takeShots().size());
  }

  @Test
  public void testReportDamage() {
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(1, 1));
    shots.add(new Coord(2, 2));
    List<Coord> hits = aiPlayer.reportDamage(shots);
    assertEquals(0, hits.size());
  }

  @Test
  public void testGetSuccessfulHits() {
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(1, 1));
    shots.add(new Coord(2, 2));
    aiPlayer.successfulHits(shots);
  }
}