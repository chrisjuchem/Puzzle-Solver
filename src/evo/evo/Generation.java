package evo.evo;

import sun.nio.ch.Net;
import sun.security.action.GetBooleanAction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import evo.neuro.Network;
import evo.puzzle.Puzzle;
import evo.util.Rand;

public class Generation {

  private final static int GEN_SIZE = 100;
  private final static int ELITE = 10;

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

  private Generation(Network[] networks){
    this.networks = networks;
    scores = new HashMap<>();
    for (int i = 0; i < GEN_SIZE; i++) {
      scores.put(networks[i], 0);
    }
  }

  /**
   * All networks attempt to solve the given puzzle, adding their scores to any previous score
   * @param p the puzlle to solve
   */
  public void run(Puzzle p) {
    for (int i = 0; i < GEN_SIZE; i++) {
      Puzzle copy = p.copyOf();
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

  /**
   * Produces the next generation of networks using rank selection as described here:
   * http://watchmaker.uncommons.org/manual/ch03s03.html
   * and triangle numbers/roots
   * @return The next generation
   */
  public Generation next() {
    sort();

    Network[] newNets = new Network[GEN_SIZE];

    for (int i = 0; i < ELITE; i++) {
      newNets[i] = networks[i];
    }

    for (int i = ELITE; i < GEN_SIZE; i++) {
      int x = Rand.rand.nextInt(GEN_SIZE * (GEN_SIZE + 1)); //GEN_SIZE th triangle number and its
      int n1 = GEN_SIZE - 1 - (int) (Math.sqrt(8*x +1)-1)/2; //triangle root, flipped so 0 is favored
      x = Rand.rand.nextInt(GEN_SIZE * (GEN_SIZE + 1));
      int n2 = GEN_SIZE - 1 - (int) (Math.sqrt(8*x +1)-1)/2;

      Network mother = networks[n1];
      Network father = networks[n2];

      newNets[i] = mother.breed(father);
    }

    return new Generation(newNets);
  }
}
