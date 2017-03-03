package evo.puzzle;

import java.util.Objects;

/**
 * Represents a tile with a specific rotation. Tiles are never mutated.
 */
public class Tile {
  public final Pic top;
  public final Pic bot;
  public final Pic left;
  public final Pic right;

  public Tile(Pic top, Pic right, Pic bot, Pic left){
    this.top = top;
    this.bot = bot;
    this.left = left;
    this.right = right;
  }

  /**
   * Returns a new tile rotates 90 degrees CW.
   * @return the new tile
   */
  public Tile rotate() {
    return new Tile (left, top, right, bot);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else if (other instanceof Tile) {
      return this.top.equals(((Tile) other).top) && this.bot.equals(((Tile) other).bot) &&
              this.left.equals(((Tile) other).left) && this.right.equals(((Tile) other).right);
    } else {
      return false;
    }

  }

  @Override
  public int hashCode() {
    return Objects.hash(top, bot, left, right);
  }

  @Override
  public String toString() {
    return "[" + top  + " " + right + " " + bot + " " + left + "]";
  }
}
