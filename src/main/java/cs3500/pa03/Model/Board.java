package cs3500.pa03.Model;

/**
 * Represents a board used to display the player's ships.
 */
public class Board {
  private int width;
  private int height;
  private char[][] grid;
  private int shipCount;

  /**
   * Gets the width and height of the board.
   * @return the width of the board
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets the height of the board.
   * @return the height of the board
   */
  public int getHeight() {
    return height;
  }

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    grid = new char[width][height];
    initializeBoard();
  }

  /**
   * Initializes the board with empty cells.
   */
  public void initializeBoard() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        grid[i][j] = '0';
      }
    }
  }

  /**
   * Prints the board.
   */
  public void printBoard() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * Places a ship on the board.
   *
   * @param x         the x-coordinate of the ship's starting position
   * @param y         the y-coordinate of the ship's starting position
   * @param size      the size of the ship
   * @param horizontal true if the ship should be placed horizontally, false if vertically
   */
  public void placeShip(int x, int y, int size, boolean horizontal) {
    char shipInitial;
    switch (size) {
      case 3:
        shipInitial = 'S'; // Submarine
        break;
      case 4:
        shipInitial = 'D'; // Destroyer
        break;
      case 5:
        shipInitial = 'B'; // Battleship
        break;
      case 6:
        shipInitial = 'C'; // Carrier
        break;
      default:
        return;
    }

    for (int i = 0; i < size; i++) { // Place ship
      if (isWithinBounds(x, y)) {
        grid[x][y] = shipInitial;
      }
      if (horizontal) { // Move to next cell
        x++;
      } else { // Vertical
        y++;
      }
    }
    shipCount++;
  }

  /**
   * Checks if the specified coordinates are within the bounds of the board.
   * @param x
   * @param y
   * @return
   */

  private boolean isWithinBounds(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
  }

  /**
   * Takes a shot at the specified coordinates and updates the board accordingly.
   *
   * @param x the x-coordinate of the shot
   * @param y the y-coordinate of the shot
   * @return 'M' if the shot is a miss, 'H' if the shot is a hit, or 'I' if the shot is invalid
   */
  public char takeShot(int x, int y) {
    if (!isWithinBounds(x, y)) {
      return 'I'; // Invalid shot
    }
    char cell = grid[x][y];
    if (cell == '0') { // Empty cell
      grid[x][y] = 'M'; // Miss
      return 'M';
    } else if (cell != 'M' && cell != 'H') { // Not a miss or hit
      grid[x][y] = 'H'; // Hit
      shipCount--;
      return 'H';
    }
    return 'I'; // Invalid shot (already hit or missed)
  }


  /**
   * Checks if the game is over.
   *
   * @return true if all ships are sunk, false otherwise
   */
  public boolean isGameOver() { // Checks if all ships are sunk
    return shipCount == 0;
  }

  /**
   * Checks if a ship placement is valid.
   * @param width
   * @param height
   * @param size
   * @param horizontal
   * @return
   */
  public boolean isPlacementValid(int width, int height, int size, boolean horizontal) { // Checks if ship placement is valid
    if(horizontal) {
      if(width < 0 || width >= this.width || height < 0 || height >= this.height - size + 1) {
        return false;
      }
      for(int i = 0; i < size; i++) { // Checks if ship placement is valid
        if(grid[width][height + i] != '0') {
          return false;
        }
      }
      return true;
    } else {
      if(width < 0 || width >= this.width - size + 1 || height < 0 || height >= this.height) { // Checks if ship placement is valid
        return false;
      }
      for(int i = 0; i < size; i++) { // Checks if ship placement is valid
        if(grid[width + i][height] != '0') {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * Gets the grid.
   * @return
   */
  public char[][] getGrid() {
    return grid;
  }

  /**
   * Checks if the board is empty.
   *
   * @return
   */
  public boolean isEmpty() {
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        if(grid[i][j] != '0') {
          throw new IllegalArgumentException("Board is not empty");
        }
      }
    }
    return false;
  }
}
