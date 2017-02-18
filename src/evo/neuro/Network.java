package evo.neuro;

import evo.puzzle.Puzzle;
import evo.util.Rand;

public class Network {
  private final static double SECONDARY_INPUTS_AVG = 3.5;
  private final static double SECONDARY_INPUTS_SD =  1.0;

  //final static double MUTATION_RATE


  private final int[] SHAPE = {216, 255, 128, 64, 32, 14};
  Node[][] nodes;

  public Network() {
    //initialize the node arrays
    nodes = new Node[SHAPE.length][];
    for (int i = 1; i < SHAPE.length; i++) {
      nodes[i] = new Node[SHAPE[i]];
    }

    InNode[] inNodes = new InNode[SHAPE[0]];
    //initialize InNodes
    int count = 0;
    for (int tile = 0; tile < 9; tile++) {
      for (int edge = 0; edge < 4; edge++) {
        for (int pattern = 0; pattern < 4; pattern++) {
          nodes[0][count] = new PatternInNode(tile, edge, pattern);
          count++;
        }
        for (int half = 0; half < 2; half++) {
          nodes[0][count] = new HalfInNode(tile, edge, half);
          count++;
        }
      }
    }
    nodes[0] = inNodes;

    //initialize middle nodes
    for (int level = 1; level < SHAPE.length; level++) {
      for (int node = 0; node < SHAPE[level]; node++) {
        SecondaryNode sn = new SecondaryNode();
        int numInputs = (int) Math.round(SECONDARY_INPUTS_AVG +
                (Rand.rand.nextGaussian() * SECONDARY_INPUTS_SD));
        for (int i = 0; i < numInputs; i++) {
          sn.connect(nodes[level - 1][Rand.rand.nextInt(SHAPE[level - 1])], Rand.rand.nextDouble());
        }
        nodes[level][node] = sn;
      }
    }
  }

  public void run(Puzzle p) {
    while (true){
      InNode[] ins = (InNode[]) nodes[0];
      for (InNode n : ins) {
        n.setPuzzle(p);
      }

      for (Node[] level : nodes) {
        for (Node n : level) {
          n.calcVal();
        }
      }

      if(applyResult(p)) {
        break;
      }

    }
  }

  /**
   * Applies a move on the given puzzle based on the current values of the nodes in the last level.
   * @return true if result is completion of puzzle, false if a move was made
   */
  private boolean applyResult(Puzzle p) {
    //TODO
    return true;
  }
}
