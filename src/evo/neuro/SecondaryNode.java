package evo.neuro;

import java.util.Map;

public class SecondaryNode extends Node {
  Map<Node, Double> inputs;

  @Override
  void calcVal() {
    double sum = 0;
    double potential = 0;
    for (Node n : inputs.keySet()) {
      potential += inputs.get(n);
      sum += inputs.get(n) * n.getVal();
    }
    this.value = sum / potential;
  }
}
