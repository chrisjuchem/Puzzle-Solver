package evo.gui;

import java.awt.*;

import javax.swing.*;

import evo.puzzle.Puzzle;

/**
 * Created by Chris Juchem on 1/11/2017.
 */
public class Window extends JFrame {

  public Window() {
    //this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Evolution Sim");


    this.setSize(new Dimension(1200,  800));

    add(new PuzzleDrawer(Puzzle.makeNew()));
    this.setVisible(true);

  }

}
