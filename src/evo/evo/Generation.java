package evo.evo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import evo.neuro.Network;
import evo.puzzle.Puzzle;

public class Generation {

  private final static int GEN_SIZE = 100;

  private Network[] networks;
  private Map<Network, Integer> scores;

  public Generation() {
    networks = new Network[GEN_SIZE];
    scores = new HashMap<>();
    for (int i = 0; i < GEN_SIZE; i++) {
      networks[i] = new Network();
      scores.put(networks[i], 0);
    }
  }

  /**
   * All networks attempt to solve the given puzzle, adding their scores to any previous score
   * @param p the puzlle to solve
   */
  public void run(Puzzle p) {
    for (int i = 0; i < GEN_SIZE; i++) {
      Puzzle copy = Puzzle.copy(p);
      networks[i].run(copy);
      scores.put(networks[i], scores.get(networks[i]) + copy.score());
    }
  }

  /**
   * Sorts networks in descending order;
   */
  private void sort() {
    Arrays.sort(networks, (Network a, Network b) -> scores.get(b) - scores.get(a));
  }
}
