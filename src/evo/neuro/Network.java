package evo.neuro;

import java.util.Arrays;

public class Network {
  /*final static int INIT_IN_NODES_MIN = 3;
  final static int INIT_IN_NODES_MAX = 10;
  final static double SECONDARY_INPUTS_AVG = 3.5;
  final static double SECONDARY_INPUTS_SD =  1.0;

  //final static double MUTATION_RATE*/


  final int[] SHAPE = {216, 255, 128, 64, 32, 14};
  InNode[] inNodes;
  Node[][] middleNodes;

  public Network() {
    //initialize the node arrays
    inNodes = new InNode[SHAPE[0]];
    middleNodes = new Node[SHAPE.length -1][];
    for (int i = 1; i < SHAPE.length; i++) {
      middleNodes[i - 1] = new Node[SHAPE[i]];
    }

    //initialize InNodes
    int count = 0;
    for (int tile = 0; tile < 9; tile++) {
      for (int edge = 0; edge < 4; edge++) {
        for (int pattern = 0; pattern < 4; pattern++) {
          middleNodes[0][count] = new PatternInNode(tile, edge, pattern);
          count++;
        }
        for (int half = 0; half < 2; half++) {
          middleNodes[0][count] = new HalfInNode(tile, edge, half);
          count++;
        }
      }
    }

    //initialize middle nodes
    for (int i = 1; i < SHAPE.length -1; i++) {
          //TODO continue here
    }
  }
}
