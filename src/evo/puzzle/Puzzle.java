package evo.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import evo.util.Rand;


public class Puzzle {
  private List<Tile> pieces;
  private static final boolean AUTO_GEN_SHUFFLE = true; //shuffle on creation?

  public Puzzle (List<Tile> pieces) {
    this.pieces = pieces;
  }

  public Puzzle () {
    this(AUTO_GEN_SHUFFLE);
  }

  public Puzzle (boolean shuffle) {
    /*   0  |  1  |  2
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

    pieces = new ArrayList<>();
    pieces.add(new Tile(Pic.random(), edges.get(0), edges.get(2), Pic.random()));
    pieces.add(new Tile(Pic.random(), edges.get(1), edges.get(3), edges.get(0).opposite()));
    pieces.add(new Tile(Pic.random(), Pic.random(), edges.get(4), edges.get(1).opposite()));
    pieces.add(new Tile(edges.get(2).opposite(), edges.get(5), edges.get(7), Pic.random()));
    pieces.add(new Tile(edges.get(3).opposite(), edges.get(6), edges.get(8), edges.get(5).opposite()));
    pieces.add(new Tile(edges.get(4).opposite(), Pic.random(), edges.get(9), edges.get(6).opposite()));
    pieces.add(new Tile(edges.get(7).opposite(), edges.get(10), Pic.random(), Pic.random()));
    pieces.add(new Tile(edges.get(8).opposite(), edges.get(11), Pic.random(), edges.get(10).opposite()));
    pieces.add(new Tile(edges.get(9).opposite(), Pic.random(), Pic.random(), edges.get(11).opposite()));

    if (shuffle) {
      Collections.shuffle(pieces);
      pieces = pieces.stream().map((Tile t) -> {
        for (int i = Rand.rand.nextInt(4); i > 0; i--) {
          t = t.rotate();
        }
        return t;
      }).collect(Collectors.toList());
    }
  }

  public Tile tileAt(int x, int y) {
    if (x >= 3 || y >= 3) {
      throw new IllegalArgumentException("x or y out of range [0,2]");
    }
    return pieces.get(x + y * 3);
  }

  public Puzzle rotate(int tile, int rotations) {
    Tile t = pieces.get(tile);
    for (int i = 0; i < rotations; i++) {
      t = t.rotate();
    }
    pieces.set(tile, t);

    return this;
  }

  public Puzzle swap(int tile1, int tile2) {
    pieces.set(tile1, pieces.set(tile2, pieces.get(tile1)));

    return this;
  }

  public int score(){
    return Pic.score(pieces.get(0).right, pieces.get(1).left)
            + Pic.score(pieces.get(1).right, pieces.get(2).left)
            + Pic.score(pieces.get(0).bot, pieces.get(3).top)
            + Pic.score(pieces.get(1).bot, pieces.get(4).top)
            + Pic.score(pieces.get(2).bot, pieces.get(5).top)
            + Pic.score(pieces.get(3).right, pieces.get(4).left)
            + Pic.score(pieces.get(4).right, pieces.get(5).left)
            + Pic.score(pieces.get(3).bot, pieces.get(6).top)
            + Pic.score(pieces.get(4).bot, pieces.get(7).top)
            + Pic.score(pieces.get(5).bot, pieces.get(8).top)
            + Pic.score(pieces.get(6).right, pieces.get(7).left)
            + Pic.score(pieces.get(7).right, pieces.get(8).left);
  }

  public Puzzle copyOf() {
    return new Puzzle(this.pieces);
  }

  @Override
  public boolean equals (Object other) {
    if (this == other) {
      return true;
    } else if (other instanceof Puzzle) {
      return ((Puzzle) other).pieces.equals(this.pieces);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(pieces);
  }
}
