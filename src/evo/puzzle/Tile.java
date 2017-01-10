package evo.puzzle;

import java.util.Objects;

/**
 * Represents a tile with a specific rotation.
 */
public class Tile {
  private Pic top;
  private Pic bot;
  private Pic left;
  private Pic right;

  Tile(Pic top, Pic bot, Pic left, Pic right){
    this.top = top;
    this.bot = bot;
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean equals(Object other) {

  }

  @Override
  public int hashCode() {
    return Objects.hash(top, bot, left, right);
  }

  @Override
  public String toString() {

  }
}
