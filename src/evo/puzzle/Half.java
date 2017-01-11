package evo.puzzle;

import evo.util.Rand;

/**
 * Represents the two halves of a pattern.
 */
public enum Half {
  x, y;

  public Half opposite() {
    if (this == x) {
      return y;
    } else {
      return x;
    }
  }

  public static Half fromOrdinal(int i) {
    switch (i) {
      case 0:
        return x;
      case 1:
        return y;
      default:
        throw new IllegalArgumentException("Not in range [0,1]");
    }
  }

  public static Half random() {
    return fromOrdinal(Rand.rand.nextInt(2));
  }
}
