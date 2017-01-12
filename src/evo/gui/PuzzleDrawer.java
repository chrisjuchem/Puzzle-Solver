package evo.gui;

import java.awt.*;

import javax.swing.*;

import evo.puzzle.Pic;
import evo.puzzle.Puzzle;
import evo.puzzle.Tile;

/**
 * Created by Chris Juchem on 1/11/2017.
 */
public class PuzzleDrawer extends JPanel {
  private Puzzle p;
  private final int OFFSET = 100;
  private final int TILE_SIZE = 150;

  PuzzleDrawer(Puzzle p) {
    this.p = p;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Tile t = p.tileAt(i, j);
        g.drawRect(OFFSET + TILE_SIZE * i, OFFSET + TILE_SIZE * j, TILE_SIZE, TILE_SIZE);

        Pic[] pics = {t.top}
        for (int k = 0; k < 4; k++) {

        }
      }
    }
  }
}
