package evo.util;

import evo.evo.Generation;
import evo.gui.Window;
import evo.neuro.Network;
import evo.puzzle.Puzzle;

public class Main {
  public static void main(String[] args) {
    Generation g = new Generation();
    Puzzle p = Puzzle.makeNew();

    g.run(p);
  }
}
