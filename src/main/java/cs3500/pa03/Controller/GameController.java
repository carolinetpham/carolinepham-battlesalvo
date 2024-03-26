package cs3500.pa03.Controller;

import cs3500.pa03.Model.Board;
import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.GameResult;
import cs3500.pa03.Model.ShipType;
import cs3500.pa03.Model.players.AIPlayer;
import cs3500.pa03.Model.players.ManualPlayer;
import cs3500.pa03.View.BoardDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents the controller for the game.
 */
public class GameController {
  private final Scanner scanner;
  private GameResult result;

  public GameController() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Starts the game.
   *
   * @param width  the width of the game board
   * @param height the height of the game board
   */
  public void startGame(int width, int height) {
    this.result = new GameResult(false, null, null, null);
    ManualPlayer user = new ManualPlayer("user", new ArrayList<>());
    AIPlayer computer = new AIPlayer("computer");

    BoardDisplay boardDisplay = new BoardDisplay(user.getBoard());
    boardDisplay.generateBoard();

    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.BATTLESHIP, 0);
    specs.put(ShipType.DESTROYER, 0);
    specs.put(ShipType.SUBMARINE, 0);
    specs.put(ShipType.CARRIER, 0);

    // Create BoardDisplay instances
    BoardDisplay userBoardDisplay = new BoardDisplay(user.getBoard());
    BoardDisplay computerBoardDisplay = new BoardDisplay(computer.getBoard());

    // Set up user board
    System.out.println("Setting up user board:");
    userBoardDisplay.generateBoard();
    user.setup(width, height, specs);

    // Set up computer board
    System.out.println("Setting up computer board:");
    computerBoardDisplay.generateBoard();
    computer.setup(width, height, specs);

    while (true) {
      System.out.println("Opponent Board Data:");
      computerBoardDisplay.displayShips();
      System.out.println("Your Board Data:");
      userBoardDisplay.displayShips();

      List<Coord> userShots = user.takeShots(); // Allow user to enter coordinates
      List<Coord> computerShots = computer.takeShots();

      for (Coord shot : userShots) { // Take shots
        char output = computer.getBoard().takeShot(shot.getX(), shot.getY());
        if (output == 'H') {
          System.out.println("You hit a ship!");
        } else if (output == 'M') {
          System.out.println("You missed!");
        } else {
          System.out.println("You already shot there!");
        }
      }

      for (Coord shot : computerShots) { // Allow computer to enter coordinates
        char output = user.getBoard().takeShot(shot.getX(), shot.getY());
        if (output == 'H') {
          System.out.println("The computer hit a ship!");
        } else if (output == 'M') {
          System.out.println("The computer missed!");
        } else {
          System.out.println("The computer already shot there!");
        }
      }

      List<Coord> userHits = user.reportDamage(computerShots);
      List<Coord> computerHits = computer.reportDamage(userShots);

      user.successfulHits(userHits);
      computer.successfulHits(computerHits);

      if (user.getBoard().isGameOver()) { // Check if game is over
        user.endGame(result.getWin(), "Congrats! You won!");
        computer.endGame(result.getLoss(), "Sorry! You lost!");
        break;
      } else if (computer.getBoard().isGameOver()) {
        user.endGame(result.getLoss(), "Sorry! You lost!");
        computer.endGame(result.getWin(), "Congrats! You won!");
        break;
      }
    }
  }
}
