package cs3500.pa03.Model.players;

import cs3500.pa03.Model.Board;
import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.GameResult;
import cs3500.pa03.Model.Ship;
import cs3500.pa03.Model.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents an AI player.
 */
public class AIPlayer implements Player {
  private Board board;
  private String name;
  private List<Ship> ships;
  private List<Coord> shots;

  public AIPlayer(String name) {
    this.name = name;
    this.ships = new ArrayList<>();
    this.shots = new ArrayList<>();
    this.board = new Board(0,0);
  }

  /**
   * Returns the name of the player
   * @return
   * the name of the player
   */
  @Override
  public String name() {
    return name;
  }

  /**
   * Sets up the ships for the player.
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return
   * the list of ships
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    System.out.println("Ship setup for " + name + ":\n");
    Random random = new Random();
    for(ShipType type : specifications.keySet()) {
      int shipCount = specifications.get(type);
      for(int i = 0; i < shipCount; i++) {
        Coord start = new Coord(random.nextInt(width), random.nextInt(height));
        Coord end = new Coord(random.nextInt(width), random.nextInt(height));
        ships.add(new Ship(type, start, end));
      }
    }
    return ships;
  }

  /**
   * Returns the list of shots to be taken by the player.
   * @return
   * the list of shots to be taken by the player
   */
  @Override
  public List<Coord> takeShots() {
    System.out.println(name + "'s turn to shoot:\n");
    Random random = new Random();
    shots.clear();
    int shotCount = (int) ships.stream().filter(ship -> !ship.isShipSunk()).count();
    for(int i = 0; i < shotCount; i++) {
      int x = random.nextInt(10);
      int y = random.nextInt(10);

      while(shots.contains(new Coord(x, y))) {
        x = random.nextInt(10);
        y = random.nextInt(10);
      }
        shots.add(new Coord(x, y));
    }
    return shots;
  }

  /**
   * Returns the list of shots that hit the opponent's ships.
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return new ArrayList<>();
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    System.out.println(name + "'s successful hits:\n");
    for(Coord shot : shotsThatHitOpponentShips) {
      System.out.println(shot);
    }
  }

  /**
   * Reports to this player the result of the game.
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    System.out.println(name + " " + result + " because " + reason);
  }

  /**
   * Returns the board of the player.
   * @return
   */
  public Board getBoard() {
    return board;
  }
}
