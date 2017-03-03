package evo.neuro;

import evo.puzzle.Puzzle;

abstract class Node {
  double value;

  /**
   * The node calculates its value and caches it in value to later be retrieved by getVal()
   */
  abstract void calcVal();

  double getVal() {
    return value;
  }

  abstract Node copy();
}
