package evo.puzzle;

import java.util.Objects;

/**
 * Represents a specific half pattern (a picture).
 */
public class Pic {
  public final Pattern pattern;
  public final Half half;

  Pic(Pattern p, Half h) {
    half = h;
    pattern = p;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else if (other instanceof Pic) {
      return this.half == ((Pic) other).half && this.pattern == ((Pic) other).pattern;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode(){
    return Objects.hash(pattern, half);
  }

  @Override
  public String toString() {
    return pattern.toString() + half.toString();
  }
}
