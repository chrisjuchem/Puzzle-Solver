package evo.gui;

import java.awt.*;
import java.util.Arrays;

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
  private final int TRIANGLE_SCALE = 10;

  PuzzleDrawer(Puzzle p) {
    this.p = p;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int j = 0; j < 3; j++) {
      for (int i = 0; i < 3; i++) {
        Tile t = p.tileAt(i, j);

        g.setColor(Color.BLACK);
        g.drawRect(OFFSET + TILE_SIZE * i, OFFSET + TILE_SIZE * j, TILE_SIZE, TILE_SIZE);

        //as drawn on bottom, with middle of edge as origin
        final int[] topXs = {-2, 0, 2};
        final int[] topYs = {0, 3, 0};
        final int[] botXs = {-2, -4, 4, 2};
        final int[] botYs = {0, 3, 3, 0};

        drawShape(g, OFFSET + TILE_SIZE * i + TILE_SIZE / 2, OFFSET + TILE_SIZE * (j + 1),
                t.bot, topXs, topYs, botXs, botYs, 1, -1);
        drawShape(g, OFFSET + TILE_SIZE * i + TILE_SIZE / 2, OFFSET + TILE_SIZE * j,
                t.top, topXs, topYs, botXs, botYs, 1, 1);
        drawShape(g, OFFSET + TILE_SIZE * (i + 1), OFFSET + TILE_SIZE * j + TILE_SIZE / 2,
                t.right, topYs, topXs, botYs, botXs, -1, 1);
        drawShape(g, OFFSET + TILE_SIZE * i, OFFSET + TILE_SIZE * j + TILE_SIZE / 2,
                t.left, topYs, topXs, botYs, botXs, 1, 1);
      }
    }
  }

  private void drawShape(Graphics g, int x, int y, Pic p,
                         int[] topXs, int[] topYs, int[] botXs, int[] botYs, int hFlip, int vFlip) {
    int[] xs;
    int[] ys;
    switch (p.half) {
      case x:
        xs = Arrays.copyOf(topXs, topXs.length);
        ys = Arrays.copyOf(topYs, topYs.length);
        break;
      case y:
        xs = Arrays.copyOf(botXs, botXs.length);
        ys = Arrays.copyOf(botYs, botYs.length);
        break;
      default:
        xs = new int[0];
        ys = new int[0];

    }

    for (int i = 0; i < xs.length; i++) {
      xs[i] = x + xs[i] * TRIANGLE_SCALE * hFlip;
    }

    for (int i = 0; i < ys.length; i++) {
      ys[i] = y + ys[i] * TRIANGLE_SCALE * vFlip;
    }

    g.setColor(p.pattern.color());

    g.fillPolygon(xs, ys, Math.min(xs.length, ys.length));

    System.out.println(xs.toString() + " " + ys.toString() + " "+ Math.min(xs.length, ys.length));
  }
}
