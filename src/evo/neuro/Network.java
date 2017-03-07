package evo.neuro;

import evo.log.LogEvent;
import evo.log.Logger;
import evo.puzzle.Puzzle;
import evo.util.Rand;

public class Network {
  private final static double SECONDARY_INPUTS_AVG = 3.5;
  private final static double SECONDARY_INPUTS_SD =  1.0;

  private final static int STEP_CAP = 18;

  //final static double MUTATION_RATE


  private final static int[] SHAPE = {216, 255, 128, 64, 32, 14};
  private Node[][] nodes;

  private Network(Node[][] nodes) {
    this.nodes = nodes;
  }

  public Network() {
    //initialize the node arrays
    nodes = new Node[SHAPE.length][];
    for (int i = 0; i < SHAPE.length; i++) {
      nodes[i] = new Node[SHAPE[i]];
    }

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

  /**
   * Attempts to solve the given puzzle, altering it directly.
   * @param p A copy of the puzzle to be solved.
   */
  public void run(Puzzle p) {
    int steps = 0;
    while (true){
      Node[] ins =  nodes[0];
      for (Node n : ins) {
        ((InNode) n).setPuzzle(p);
      }

      for (Node[] level : nodes) {
        for (Node n : level) {
          n.calcVal();
        }
      }

      steps++;

      if(applyResult(p)) {
        Logger.log("Network determined completion.", LogEvent.SIM_DONE);
        break;
      } else if (steps >= STEP_CAP) {
        Logger.log(String.format("Step cap of %d reached", STEP_CAP), LogEvent.SIM_DONE);
        break;
      }
    }
  }

  /**
   * Applies a move on the given puzzle based on the current values of the nodes in the last level.
   * @return true if result is completion of puzzle, false if a move was made
   *
   * 0-8 = tile indexes
   * 9 swap highest two  tiles if > rest of 9-13
   * 10-12 rotate highest tile 1-3 times respectively if > rest of 9-13
   * 13 puzzle complete if > rest of 9-13
   */
  private boolean applyResult(Puzzle p) {
    //TODO Clean up

    //find the behavior node with the highest value
    double maxBehaviorVal = nodes[nodes.length - 1][9].getVal();
    int behavior = 9;
    for (int i = 10; i < 14; i++) {
      double val = nodes[nodes.length - 1][i].getVal();
      if (val > maxBehaviorVal) {
        maxBehaviorVal = val;
        behavior = i;
      }
    }

    //find the two tiles with the highest value
    double maxTileVal1 = nodes[nodes.length - 1][0].getVal();
    double maxTileVal2 = nodes[nodes.length - 1][1].getVal();
    int maxTile1 = 0; //highest
    int maxTile2 = 1; //2nd highest
    if (maxTileVal2 > maxTileVal1) {
      maxTileVal1 = nodes[nodes.length - 1][1].getVal(); //not standard swap operation since getVal
      maxTileVal2 = nodes[nodes.length - 1][2].getVal(); //is constant time due to caching
      maxTile1 = 1;
      maxTile2 = 0;
    }

    for (int i = 2; i < 9; i++) {
      double val = nodes[nodes.length - 1][i].getVal();
      if (val > maxTileVal1) {
        maxTileVal2 = maxTileVal1;
        maxTileVal1 = val;
        maxTile2 = maxTile1;
        maxTile1 = i;
      } else if (val > maxTileVal2) {
        maxTileVal2 = val;
        maxTile2 = i;
      }
    }

    switch (behavior) {
      case 9:
        Logger.log(String.format("Swap tiles %d & %d", maxTile1, maxTile2), LogEvent.SIM_STEM);
        p.swap(maxTile1, maxTile2);
        return false;
      case 10:
      case 11:
      case 12:
        Logger.log(String.format("Rot%d tile %d",  behavior - 9, maxTile1), LogEvent.SIM_STEM);
        p.rotate(maxTile1, behavior - 9);
        return false;
      case 13:
        return true;
      default:
        throw new RuntimeException("Undefined behavior called for by network");
    }
  }

  /**
   * Naively breed two networks by randomly choosing a node from each parent.
   * TODO: Each node takes connections randomly from each parent
   * TODO: mutations
   *
   * @param father Network to breed with
   * @return
   */
  public Network breed(Network father) {
    Node[][] newNodes = new Node[SHAPE.length][];
    for (int i = 0; i < SHAPE.length; i++) {
      newNodes[i] = new Node[SHAPE[i]];
    }

    for (int i = 0; i < SHAPE.length; i++) {
      for (int j = 0; j < SHAPE[i]; j++) {
        newNodes[i][j] = (Rand.rand.nextInt(2) == 0 ? this.nodes[i][j] : father.nodes[i][j]).copy();
      }
    }

    return new Network(newNodes);
  }
}