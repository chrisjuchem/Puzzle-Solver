package evo.puzzle;

import evo.util.Rand;

/**
 * the 4 patterns on the puzzle.
 */
public enum Pattern {
  A, B, C, D;

  public static Pattern fromOrdinal(int i) {
    switch (i) {
      case 0:
        return A;
      case 1:
        return B;
      case 2:
        return C;
      case 3:
        return D;
      default:
        throw new IllegalArgumentException("Not in range [0,3]");
    }
  }

  public static Pattern random() {
    return fromOrdinal(Rand.rand.nextInt(4));
  }
}
