package evo.puzzle;

import java.util.ArrayList;
import java.util.List;

import evo.util.Rand;

/**
 * Created by Chris Juchem on 1/9/2017.
 */
public class Puzzle {
  private List<List<Tile>> pieces;

  public Puzzle (List<List<Tile>> pieces) {
    this.pieces = pieces;
  }

  private Puzzle() {
    /**  0  |  1  |  2
     *      0     1
     *  -2--+--3--+--4-
     * 3    5  4  6    5
     *  -7--+--8--+--9-
     *     10    11
     *   6  |  7  |  8
     */
    List<Pic> edges = new ArrayList<>();// the specific edge belongs to the lower tile it touches, its opposite to the higher
    for (int i = 0; i <= 11; i++) {
      edges.add(new Pic(Pattern.random(), Half.random()));
    }

    ...
  }

  public static Puzzle makeNew() {
    return new Puzzle();
  }
}
