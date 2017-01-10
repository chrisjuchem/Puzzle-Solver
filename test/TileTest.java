import org.junit.Assert;
import org.junit.Test;

import evo.puzzle.Half;
import evo.puzzle.Pattern;
import evo.puzzle.Pic;
import evo.puzzle.Tile;

/**
 * Created by Chris Juchem on 1/10/2017.
 */
public class TileTest {
  Tile testTile = new Tile(new Pic(Pattern.A, Half.y), new Pic(Pattern.B, Half.x),
          new Pic(Pattern.C, Half.y), new Pic(Pattern.D, Half.y));

  @Test
  public void testPrint() {
    Assert.assertEquals(testTile.toString(), "[Ay Bx Cy Dy]");
  }

  @Test
  public void testRotate() {
    Assert.assertEquals(testTile.rotate().toString(), "[Dy Ay Bx Cy]");
    Assert.assertEquals(testTile.rotate().rotate().rotate().rotate(), testTile);
  }
}
