package evo.neuro;

import evo.puzzle.Half;
import evo.puzzle.Pattern;
import evo.puzzle.Pic;
import evo.puzzle.Puzzle;
import evo.puzzle.Tile;

public class InNode extends Node{
  private int tile; //[0,8]
  private int edge; //[0,3]
  private int pattern;
  private int half;

  InNode(int tile, int edge, int pattern, int half) {
    this.tile = tile;
    this.edge = edge;
    this.pattern = pattern;
    this.half = half;
  }

  Pic getPic(Puzzle p) {
    Tile t = p.tileAt(tile % 3, tile / 3);
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

  @Override
  void calcVal() {
    Pic p = getPic();
    this.value = ((p.half == Half.fromOrdinal(half)
            && p.pattern == Pattern.fromOrdinal(pattern)) ? 1 : 0);
  }
}
