package cs3500.pa03;

import cs3500.pa03.Controller.GameController;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter board width (6-15): ");
    int width = scanner.nextInt();
    System.out.print("Enter board height (6-15): ");
    int height = scanner.nextInt();

    GameController gameController = new GameController();
    gameController.startGame(width, height);

    scanner.close();
  }
}