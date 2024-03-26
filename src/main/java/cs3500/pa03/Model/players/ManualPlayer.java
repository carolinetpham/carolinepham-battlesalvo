package cs3500.pa03.Model.players;

import cs3500.pa03.Model.Board;
import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.GameResult;
import cs3500.pa03.Model.Ship;
import cs3500.pa03.Model.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a manual player.
 */

public class ManualPlayer implements Player {
  private Board board;
  private String name;
  private List<Ship> ships;
  public ManualPlayer(String name, List<Ship> ships) {
    this.name = name;
    this.ships = ships;
    this.board = new Board(0,0);
  }

  /**
   * Returns the name of the player.
   *
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
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    System.out.println("Ship setup for " + name + ":\n");
    Scanner input = new Scanner(System.in);
    for(ShipType type : specifications.keySet()) {
      int shipCount = specifications.get(type);
      for(int i = 0; i < shipCount; i++) {
        System.out.println("Enter the coordinates for the " + type + " ship #" + (i + 1) + ":");
        String[] coords = input.nextLine().split(" ");
        Coord start = new Coord(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
        Coord end = new Coord(Integer.parseInt(coords[2]), Integer.parseInt(coords[3]));
        ships.add(new Ship(type, start, end));
      }
    }
    return ships;
  }

  /**
   * Returns the shots that the player wants to take.
   * @return
   */
  @Override
  public List<Coord> takeShots() {
    System.out.println(name + "'s turn to shoot:\n");
    List<Coord> shots = new ArrayList<>();
    int shotCount = (int) ships.stream().filter(ship -> !ship.isShipSunk()).count();
    for(int i = 0; i < shotCount; i++) {
      System.out.println("Enter the coordinates for shot #" + (i + 1) + ":");
      Scanner input = new Scanner(System.in);
      String[] coords = input.nextLine().split(" ");
      shots.add(new Coord(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
    }
    return shots;
  }

  /**
   * Returns the shots that hit the opponent's ships.
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hits = new ArrayList<>();
    for(Coord shot : opponentShotsOnBoard) {
      for(Ship ship : ships) {
        if(ship.hasCoord(shot)) {
          hits.add(shot);
          break;
        }
      }
    }
    return hits;
  }

  /**
   * Updates the player's ships based on the shots that hit the opponent's ships.
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
   * Prints the result of the game.
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
