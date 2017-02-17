package evo.neuro;

import evo.puzzle.Pattern;
import evo.puzzle.Pic;

public class PatternInNode extends InNode {
  private int pattern;    //[0,3]

  PatternInNode(int tile, int edge, int pattern) {
    super(tile, edge);
    this.pattern = pattern;
  }

  @Override
  void calcVal() {
    Pic pic = getPic();
    this.value = (pic.pattern == Pattern.fromOrdinal(pattern) ? 1 : 0);
  }
}

