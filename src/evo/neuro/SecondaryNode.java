package evo.neuro;

import java.util.Map;

public class SecondaryNode extends Node {

  Map<Node, Double> inputs;
  double selfWeight;
  double selfVal;

  SecondaryNode(double selfVal,double selfWeight){
    this.selfVal = selfVal;
    this.selfWeight = selfWeight;
  }

  @Override
  void calcVal() {
    double sum = selfVal * selfWeight;
    double potential = selfWeight;
    for (Node n : inputs.keySet()) {
      potential += inputs.get(n);
      sum += inputs.get(n) * n.getVal();
    }
    this.value = sum / potential;
  }
}
