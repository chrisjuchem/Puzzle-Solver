package evo.gui;

import java.awt.*;

import javax.swing.*;

import evo.puzzle.Puzzle;

public class Window extends JFrame {

  PuzzleDrawer panel;

  public  Window() {
    this(Puzzle.makeNew());
  }

  public Window(Puzzle p) {
    //this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Evolution Sim");


    this.setSize(new Dimension(1200,  800));

    panel = new PuzzleDrawer();
    panel.setPuzzle(p);

    this.add(panel);

    this.setVisible(true);

  }

}
