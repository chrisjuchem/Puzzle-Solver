package evo.neuro;

import evo.puzzle.Half;
import evo.puzzle.Pattern;
import evo.puzzle.Pic;
import evo.puzzle.Puzzle;
import evo.puzzle.Tile;

public abstract class InNode extends Node{
  protected int tile;    //[0,8]
  protected int edge;    //[0,3]
  private Puzzle p;

  InNode(int tile, int edge) {
    this.tile = tile;
    this.edge = edge;
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

  @Override
  abstract void calcVal();
}
