import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import evo.puzzle.Half;
import evo.puzzle.Pattern;
import evo.puzzle.Pic;
import evo.puzzle.Puzzle;
import evo.puzzle.Tile;

public class PuzzleTest {

  /**
   * Creates this example puzzle: http://prntscr.com/egxdls
   * @return the puzzle
   */
  public Puzzle exPuzzle() {
    List<Tile> tiles = new ArrayList<>(9);
    tiles.add(new Tile(new Pic(Pattern.A, Half.x), new Pic(Pattern.A, Half.x), new Pic(Pattern.B, Half.y), new Pic(Pattern.C, Half.x)));
    tiles.add(new Tile(new Pic(Pattern.B, Half.y), new Pic(Pattern.D, Half.x), new Pic(Pattern.C, Half.y), new Pic(Pattern.C, Half.x)));
    tiles.add(new Tile(new Pic(Pattern.A, Half.x), new Pic(Pattern.A, Half.y), new Pic(Pattern.D, Half.x), new Pic(Pattern.A, Half.y)));
    tiles.add(new Tile(new Pic(Pattern.C, Half.x), new Pic(Pattern.C, Half.x), new Pic(Pattern.B, Half.x), new Pic(Pattern.C, Half.x)));
    tiles.add(new Tile(new Pic(Pattern.A, Half.x), new Pic(Pattern.A, Half.y), new Pic(Pattern.B, Half.y), new Pic(Pattern.B, Half.y)));
    tiles.add(new Tile(new Pic(Pattern.B, Half.x), new Pic(Pattern.B, Half.x), new Pic(Pattern.C, Half.y), new Pic(Pattern.A, Half.y)));
    tiles.add(new Tile(new Pic(Pattern.B, Half.x), new Pic(Pattern.B, Half.x), new Pic(Pattern.A, Half.x), new Pic(Pattern.D, Half.y)));
    tiles.add(new Tile(new Pic(Pattern.B, Half.x), new Pic(Pattern.B, Half.y), new Pic(Pattern.B, Half.x), new Pic(Pattern.A, Half.y)));
    tiles.add(new Tile(new Pic(Pattern.B, Half.y), new Pic(Pattern.A, Half.x), new Pic(Pattern.D, Half.y), new Pic(Pattern.C, Half.y)));
    return new Puzzle(tiles);
  }

  @Test
  public void testScore() {
    for (int i = 0; i < 100; i++) {
      Assert.assertEquals(new Puzzle(false).score(), 12);
    }

    Assert.assertEquals(exPuzzle().score(), 1);
    Assert.assertEquals(exPuzzle().swap(5, 6).rotate(5, 1).score(), 4);
  }

  @Test
  public void testEquals() {
    Puzzle p1 = exPuzzle();
    Puzzle p2 = exPuzzle();
    Assert.assertEquals(p1, p2);
    Assert.assertFalse(p1 == p2);
  }

  @Test
  public void testOrderOfOps() {
    Assert.assertEquals(exPuzzle().swap(5, 6).rotate(5, 1), exPuzzle().rotate(6, 1).swap(5, 6));
    Assert.assertEquals(exPuzzle().swap(3, 5), exPuzzle().swap(5, 3));
    Assert.assertEquals(exPuzzle().swap(3, 5).swap(5, 8), exPuzzle().swap(3, 8).swap(5, 3));
    Assert.assertEquals(exPuzzle().swap(3, 5).swap(5, 8).swap(3, 8).swap(5, 8), exPuzzle());
  }
}
