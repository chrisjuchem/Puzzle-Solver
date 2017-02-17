package evo.neuro;

import evo.puzzle.Half;
import evo.puzzle.Pic;

public class HalfInNode extends InNode {
  private int half;    //[0,1]

  HalfInNode(int tile, int edge, int half) {
    super(tile, edge);
    this.half = half;
  }

  @Override
  void calcVal() {
    Pic pic = getPic();
    this.value = (pic.half == Half.fromOrdinal(half) ? 1 : 0);
  }
}

