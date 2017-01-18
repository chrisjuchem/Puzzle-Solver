package evo.neuro;

import evo.puzzle.Half;
import evo.puzzle.Pattern;
import evo.puzzle.Pic;
import evo.puzzle.Puzzle;
import evo.puzzle.Tile;

public class InNode extends Node{
  private int tile;    //[0,8]
  private int edge;    //[0,3]
  private int pattern; //[0,3]
  private int half;    //[0,1]
  private Puzzle p;

  InNode(int tile, int edge, int pattern, int half) {
    this.tile = tile;
    this.edge = edge;
    this.pattern = pattern;
    this.half = half;
  }

  void setPuzzle(Puzzle p) {
    this.p = p;
  }

  Pic getPic() {
    Tile t = this.p.tileAt(tile % 3, tile / 3);
    switch (edge) {
      case 0:
        return t.top;
      case 1:
        return t.right;
      case 2:
        return t.bot;
      case 3:
        return t.left;
      default:
        throw new IllegalStateException("edge was not in [0,3]");
    }
  }

  /**
   * Algorithm is 1 if the specified pic is exactly the right pattern and half, 0 otherwise.
   * TODO play around with this
   */
  @Override
  void calcVal() {
    Pic pic = getPic();
    this.value = ((pic.half == Half.fromOrdinal(half)
            && pic.pattern == Pattern.fromOrdinal(pattern)) ? 1 : 0);
  }
}
