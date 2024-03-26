package cs3500.pa03.View;

import cs3500.pa03.Model.BoatSize;
import cs3500.pa03.Model.Board;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the board display.
 */
public class BoardDisplay {
  private Board board;
  private Scanner scanner;

  public BoardDisplay(Board board) {
    this.board = board;
    this.scanner = new Scanner(System.in);
  }

  /**
   * Checks if the fleet sizes are valid.
   */
  public void generateBoard() {
    board.initializeBoard();
    displayShips();
  }

  /**
   * Displays the ships on the board.
   */
  public void displayShips() {
    int width = board.getWidth();
    int height = board.getHeight();

    int smallerDimension = Math.min(width, height);
    System.out.println("Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].");
    System.out.println("Remember, your fleet may not exceed the smaller dimension of the board.");

    int[] fleetSizes = new int[4];
    for (int i = 0; i < 4; i++) { // Get fleet sizes
      BoatSize boatSize = BoatSize.values()[i];
      System.out.printf("Enter the number of %s(s) (1-%d): ", boatSize.getName(), smallerDimension);
      fleetSizes[i] = scanner.nextInt();
    }

    if (!isFleetValid(fleetSizes, smallerDimension)) { // Check if fleet is valid
      System.out.println("Uh Oh! You've entered invalid fleet sizes.");
      System.out.println("Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].");
      System.out.println("Remember, your fleet may not exceed the smaller dimension of the board.");
      return;
    }

    Random random = new Random();
    for (int i = 0; i < 4; i++) { // Place ships on board
      BoatSize boatSize = BoatSize.values()[i];
      int count = fleetSizes[i];
      for (int j = 0; j < count; j++) { // Place each ship
        boolean isValid = false;
        while (!isValid) {
          int x, y;
          boolean horizontal = random.nextBoolean();
          if (horizontal) { // Generate random coordinates
            x = random.nextInt(width - boatSize.getSize() + 1);
            y = random.nextInt(height);
          } else { // Generate random coordinates
            x = random.nextInt(width);
            y = random.nextInt(height - boatSize.getSize() + 1);
          }
          isValid = board.isPlacementValid(x, y, boatSize.getSize(), horizontal);
          if (isValid) { // Place ship on board
            board.placeShip(x, y, boatSize.getSize(), horizontal);
          }
        }
      }
    }
    board.printBoard();
  }


  /**
   * Returns true if the fleet is valid, false otherwise
   * @param fleetSizes
   * @param smallerDimension
   * @return
   */
  public boolean isFleetValid(int[] fleetSizes, int smallerDimension) { // Check if fleet is valid
    int total = Arrays.stream(fleetSizes).sum();
    return fleetSizes.length == 4 && total <= smallerDimension;
  }

  /**
   * Returns a random boat from the list of boats that have not been placed yet
   * @param count
   * @return
   */

  public BoatSize getRandomBoat(Map<BoatSize, Integer> count) { // Get random boat
    List<BoatSize> availableBoats = new ArrayList<>();
    for (Map.Entry<BoatSize, Integer> entry : count.entrySet()) { // Get boats that have not been placed yet
      if (entry.getValue() < entry.getKey().getSize()) {
        availableBoats.add(entry.getKey());
      }
    }
    if (availableBoats.isEmpty()) { // No boats left
      return null;
    }
    return availableBoats.get(new Random().nextInt(availableBoats.size())); // Return random boat
  }

  /**
   * Returns a random number between 1 and smaller - total
   * @param smaller
   * @param total
   * @return
   */
  public int getRandomCount(int smaller, int total) { // Get random count
    Random random = new Random();
    int remaining = smaller - total;
    if(remaining == 0) {
      return 0;
    }
    if(remaining == 1) {
      return 1;
    }
    return random.nextInt(remaining - 1) + 1;
  }
}
