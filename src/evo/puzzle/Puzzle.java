package evo.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import evo.util.Rand;

/**
 * Created by Chris Juchem on 1/9/2017.
 */
public class Puzzle {
  private List<Tile> pieces;
  private static final boolean AUTO_GEN_SHUFFLE = false;

  public Puzzle (List<Tile> pieces) {
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
      edges.add(Pic.random());
    }

    pieces.add(new Tile(Pic.random(), edges.get(0), edges.get(2), Pic.random()));
    pieces.add(new Tile(Pic.random(), edges.get(1), edges.get(3), edges.get(0).opposite()));
    pieces.add(new Tile(Pic.random(), Pic.random(), edges.get(4), edges.get(1).opposite()));
    pieces.add(new Tile(edges.get(2).opposite(), edges.get(5), edges.get(7), Pic.random()));
    pieces.add(new Tile(edges.get(3).opposite(), edges.get(6), edges.get(8), edges.get(5).opposite()));
    pieces.add(new Tile(edges.get(4).opposite(), Pic.random(), edges.get(9), edges.get(6).opposite()));
    pieces.add(new Tile(edges.get(7).opposite(), edges.get(10), Pic.random(), Pic.random()));
    pieces.add(new Tile(edges.get(8).opposite(), edges.get(11), Pic.random(), edges.get(10).opposite()));
    pieces.add(new Tile(edges.get(9).opposite(), Pic.random(), Pic.random(), edges.get(11).opposite()));

    if (AUTO_GEN_SHUFFLE) {
      Collections.shuffle(pieces);
      pieces = pieces.stream().map((Tile t) -> {
        for (int i = Rand.rand.nextInt(4); i > 0; i++) {
          t = t.rotate();
        }
        return t;
      }).collect(Collectors.toList());
    }
  }

  public static Puzzle makeNew() {
    return new Puzzle();
  }
}
